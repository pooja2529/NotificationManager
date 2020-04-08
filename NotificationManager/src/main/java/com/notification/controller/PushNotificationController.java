package com.notification.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.notification.model.PushNotificationMaster;
import com.notification.model.PushNotificationTracker;
import com.notification.service.CustomErrorType;
import com.notification.service.PushNotificationMasterService;
import com.notification.service.PushNotificationService;
import com.notification.service.PushNotificationTrackerService;

@RestController
@RequestMapping("/Communication")
public class PushNotificationController 
{
	@Autowired 
	PushNotificationMasterService notificationService;

	@Autowired
	PushNotificationService pushService;

	@Autowired
	PushNotificationTrackerService notificationTrackService;

	@Autowired
	HttpServletRequest request;

	private static String UPLOADED_FOLDER = "D:\\spring\\video\\";

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("image");
	}

	@RequestMapping("/")
	public ModelAndView index(Model model) {
		model.addAttribute("notification", new PushNotificationTracker());
		return new ModelAndView("index");
	}



	@RequestMapping(value = "downloadfile")
	public void downloadExcel(HttpServletResponse response) 
	{

		String fileName = "LangList.csv";
		response.setHeader("Content-type", "application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate, private");
		response.setHeader("Expires", "0");
		CSVPrinter csvFilePrinter = null;
		try {
			csvFilePrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader("Sr No.", "Template").withIgnoreHeaderCase());

			csvFilePrinter.flush();

		}
		catch (Exception e) {
			e.printStackTrace();
			//logger.error(e+"At downloading excel by id ");
		} 

	}
	@RequestMapping(value = "download")
	public void downloadExcelById(Model model,String paramCode, HttpServletResponse response) 
	{

		String fileName = "ContactList.csv";
		response.setHeader("Content-type", "application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate, private");
		response.setHeader("Expires", "0");
		CSVPrinter csvFilePrinter = null;
		try {
			csvFilePrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader("Sr No.", "Mobile").withIgnoreHeaderCase());

			csvFilePrinter.flush();

		}
		catch (Exception e) {
			e.printStackTrace();
			//logger.error(e+"At downloading excel by id ");
		} 

	}
	@RequestMapping("/addBulkNotification")
	public ModelAndView sendNotification(@ModelAttribute("notification") PushNotificationTracker notification,@RequestParam("image") MultipartFile file,Model model) throws IOException
	{

		ModelAndView mv=new ModelAndView("index");
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			String splitBy = ",";
			String imagefilename = file.getOriginalFilename();
			System.out.println(imagefilename);
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dambhare\\Downloads\\"+imagefilename));
			String line = br.readLine();
			while((line = br.readLine()) != null){
				String[] b = line.split(splitBy);
				// System.out.println(b[0]);
				notification.setPn_to(b[1]);
				notificationTrackService.addNotification(notification);

			}
		}
			return mv;

		}

		@RequestMapping("/addNotification")
		public ModelAndView addNotification(@ModelAttribute("notification") PushNotificationTracker notification,
				@RequestParam("image") MultipartFile image,@RequestParam("template") MultipartFile template)
		{
			System.out.println("inside addnotification");
			ModelAndView mv=new ModelAndView("index");
			try {
				String imagefilename = image.getOriginalFilename();
				//Path logopath = Paths.get(imagefilename);
				byte[] bytes = image.getBytes();
				String imageurl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/content/").path(imagefilename)
						.toUriString();
				ServletContext servletContext1 = request.getServletContext();
				String contextlogopath = servletContext1.getRealPath("/");
				//String imgresult = URLDecoder.decode(imagefilename, "UTF-8");
				Path conimgpath = Paths.get(contextlogopath + "/content/" + imagefilename);
				Files.write(conimgpath, bytes);
				notification.setImage(imageurl);
				
				String templatefilename = template.getOriginalFilename();
				//Path logopath = Paths.get(imagefilename);
				byte[] bytes11 = template.getBytes();
				String imageurl1 = ServletUriComponentsBuilder.fromCurrentContextPath().path("/content/").path(imagefilename)
						.toUriString();
				ServletContext servletContext = request.getServletContext();
				String contextlogopath1 = servletContext.getRealPath("/");
				//String imgresult = URLDecoder.decode(imagefilename, "UTF-8");
				Path conimgpath1 = Paths.get(contextlogopath1 + "/content/" + templatefilename);
				Files.write(conimgpath1, bytes11);
				notification.setImage(imageurl1);
				mv.addObject("msg","success");
				notificationTrackService.addNotification(notification);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return mv;

		}

		@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
		public @ResponseBody ResponseEntity sendSimpleSms(@PathVariable("pn_id")int pn_id,List<String> userDeviceIdKey,PushNotificationTracker track)
		{
			try {
				PushNotificationMaster master=notificationService.getPushNotification(pn_id);
				track.setPn_title(master.getPn_title());
				track.setMessage(master.getMessage());
				track.setImage(master.getImage());
				track.setPn_sent_status("pn");
				notificationTrackService.addNotification(track);
				pushService.pushFCMNotification(userDeviceIdKey);
			} catch (Exception e) {
				//e.printStackTrace();
				return new ResponseEntity(new CustomErrorType("Fail","Error....please check your code.."+e),HttpStatus.OK);
			}
			return new ResponseEntity(new CustomErrorType("Success","Push Notifications sent!!"),HttpStatus.OK);

		}
	}
