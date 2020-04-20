package com.notification.controller;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.notification.model.PushNotificationTracker;
import com.notification.service.NotificationService;
import com.notification.service.PushNotificationMasterService;
import com.notification.service.PushNotificationService;
import com.notification.service.PushNotificationTrackerService;
import com.notification.service.UserService;

@RestController
@RequestMapping("/Communication")
public class PushNotificationController {
	@Autowired
	PushNotificationMasterService notificationService;

	@Autowired
	PushNotificationService pushService;

	@Autowired
	PushNotificationTrackerService notificationTrackService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	NotificationService masterService;

	@Autowired
	PushController pushNotificationService;

	@Autowired
	UserService userservice;
	// private static String UPLOADED_FOLDER = "D:\\spring\\video\\";

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("image", "template", "pn_to", "selected");
		// dataBinder.setDisallowedFields("template");
	}

	@RequestMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("index");
		model.addAttribute("notification", new PushNotificationTracker());
		List<String> moblist = userservice.searchMob();
		System.out.println("mobile list is" + moblist);
		mv.addObject("moblist", moblist);
		return mv;
	}

	@RequestMapping(value = "downloadfile")
	public void downloadExcel(HttpServletResponse response) {

		String fileName = "LangList.csv";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv; charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate, private");
		response.setHeader("Expires", "0");
		CSVPrinter csvFilePrinter = null;
		try {

			csvFilePrinter = new CSVPrinter(response.getWriter(),
					CSVFormat.DEFAULT.withHeader("Sr No.", "Template").withIgnoreHeaderCase());
			csvFilePrinter.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "download")
	public void downloadExcelById(Model model, String paramCode, HttpServletResponse response) {

		String fileName = "ContactList.csv";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv; charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate, private");
		response.setHeader("Expires", "0");
		CSVPrinter csvFilePrinter = null;
		try {
			csvFilePrinter = new CSVPrinter(response.getWriter(),
					CSVFormat.DEFAULT.withHeader("Sr No.", "Mobile").withIgnoreHeaderCase());
			csvFilePrinter.flush();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@RequestMapping("/addNotification")
	public ModelAndView addNotification(@ModelAttribute("notification") PushNotificationTracker notification,
			@RequestParam(required = false, name = "image") MultipartFile image) {
		System.out.println("inside addnotification");
		ModelAndView mv = new ModelAndView("redirect:/Communication/");
		try {

			String imagefilename = image.getOriginalFilename();
			byte[] bytes = image.getBytes();
			String imageurl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/content/").path(imagefilename)
					.toUriString();
			ServletContext servletContext1 = request.getServletContext();
			String contextlogopath = servletContext1.getRealPath("/");
			Path conimgpath = Paths.get(contextlogopath + "/content/" + imagefilename);
			Files.write(conimgpath, bytes);
			notification.setImage(imageurl);
			notificationTrackService.addNotification(notification);

		} catch (IOException e) {
			notification.setImage(null);
			notificationTrackService.addNotification(notification);
		}
		return mv;
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	public void sendSchedular() throws IOException {
		System.out.println("in schedular");
		/*
		 * List<String> SendAfterdata=notificationTrackService.getAll();
		 * System.out.println(SendAfterdata);
		 */
		List<String> moblist = userservice.searchMob();
		PushNotificationTracker push = new PushNotificationTracker();
		List<String> userDeviceIdKey = userservice.getDeviceId(moblist);
		System.out.println(userDeviceIdKey);
		if (!moblist.isEmpty()) {
			for (String p : moblist) {
				if (push.getPn_sent_status().equalsIgnoreCase("P")) {
					try {
						PushNotificationService.pushFCMNotification(userDeviceIdKey);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("data is not in send after");
		}
	}

	@PostMapping("/addBulkNotification")
	public ModelAndView sendNotification(@ModelAttribute("notification") PushNotificationTracker notification,
			@RequestParam(required = false, name = "selected") String[] selected,
			@RequestParam(required = false, name = "pn_to") MultipartFile file,
			@RequestParam("template") MultipartFile template) throws IOException {
		ModelAndView mv = new ModelAndView("redirect:/Communication/");
		try {
			String contactfile = file.getOriginalFilename();
			String contactfileext = com.google.common.io.Files.getFileExtension(contactfile);

			String templatefile = template.getOriginalFilename();
			String templatefileext = com.google.common.io.Files.getFileExtension(templatefile);

			String splitBy = ",";

			ArrayList<String> msglist = new ArrayList<String>();
			ArrayList<String> contactlist = new ArrayList<String>();

			if (contactfileext.equalsIgnoreCase("csv") && templatefileext.equalsIgnoreCase("csv")) {
				if (!file.isEmpty() && !template.isEmpty()) {
					BufferedReader br = new BufferedReader(
							new FileReader("C:\\Users\\Dambhare\\Downloads\\" + contactfile));
					BufferedReader br1 = new BufferedReader(
							new FileReader("C:\\Users\\Dambhare\\Downloads\\" + templatefile));

					String line = br.readLine();
					String line1 = br1.readLine();

					while ((line1 = br1.readLine()) != null && (line = br.readLine()) != null) {
						String[] b = line.split(splitBy);
						String[] b1 = line1.split(splitBy);
						if (b.length != 2 && b1.length != 2) { // Skip any "weird" (e.g., empty) line
							continue;
						}
						contactlist.add(b[1]);
						msglist.add(b1[1]);
					}

					List<String> moblist = userservice.searchMob();
					mv.addObject("moblist", moblist);
					List<String> contentlist = masterService.searchContent();

					if (moblist.containsAll(contactlist)) {
						System.out.println("match");
						if (contentlist.containsAll(msglist)) {
							System.out.println("content match");
							for (String contact : contentlist) {
								for (String msg : msglist) {

									PushNotificationTracker track = new PushNotificationTracker();
									track.setPn_to(contact);
									track.setMessage(msg);
									track.setPn_sent_status("P");
									notificationTrackService.addNotification(track);

								}
							}

						} else {
							System.out.println("not match content");
							mv.addObject("conmsg", "message template not matched with database data");
						}
					} else {
						System.out.println("not match");
						mv.addObject("mobmsg", "contact list not matched with database data  ");
					}

				} else {
					BufferedReader br1 = new BufferedReader(
							new FileReader("C:\\Users\\Dambhare\\Downloads\\" + templatefile));

					String line1 = br1.readLine();
					while ((line1 = br1.readLine()) != null) {
						String[] b1 = line1.split(splitBy);
						if (b1.length != 2) { // Skip any "weird" (e.g., empty) line
							continue;
						}

						msglist.add(b1[1]);
					}

					List<String> templist = Arrays.asList(selected);
					List<String> userDeviceIdKey = userservice.getDeviceId(templist);
					System.out.println(userDeviceIdKey);
					if (!templist.isEmpty()) {
						for (String p : templist) {
							for (String msg : msglist) {
								try {
									PushNotificationService.pushFCMNotification(userDeviceIdKey);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					} else {
						System.out.println("data is not in send after");
					}
					/*
					 * for(String numbers:templist ) { for(String msg:msglist) {
					 * 
					 * PushNotificationTracker track=new PushNotificationTracker();
					 * track.setPn_to(numbers); track.setMessage(msg);
					 * notificationTrackService.addNotification(track);
					 * 
					 * 
					 * } }
					 */

				}

			} else if (contactfileext.equalsIgnoreCase("xls") && templatefileext.equalsIgnoreCase("xls")) {

			} else if (contactfileext.equalsIgnoreCase("xlsx") && templatefileext.equalsIgnoreCase("xlsx")) {

			} else {
				mv.addObject("exterror", "please enter proper excel file..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;

	}

}
