package com.notification.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsmpp.bean.OptionalParameter.Int;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.notification.model.NotificationMaster;
import com.notification.model.PushNotificationTracker;
import com.notification.model.User;
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

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("image", "template", "pn_to", "selected","sendafter");
	}

	@RequestMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("index");
		model.addAttribute("notification", new PushNotificationTracker());
		  List<String> moblist = userservice.searchMob();
		  mv.addObject("moblist",moblist);
		 
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

		List<String> SendAfterdata=notificationTrackService.getAll();
		System.out.println(SendAfterdata);
		PushNotificationTracker push = new PushNotificationTracker();
		List<String> moblist=userservice.searchMob();
		List<String> userDeviceIdKey = userservice.getDeviceId(moblist);
		Timestamp currentdatetime = new Timestamp(new Date().getTime());

		System.out.println(userDeviceIdKey);
		if (!SendAfterdata.isEmpty()) 
		{
			for (String p : SendAfterdata) 
			{
				if ( currentdatetime.equals(push.getSendafter())) 
				{
					try {
						pushNotificationService.sendSimpleSms(masterService.getNotification(2).getNotificationId(),userDeviceIdKey,push);
						push.setPn_sent_status("Y");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else 
		{
			System.out.println("data is not in send after");
		}
	}

	@PostMapping("/addBulkNotification")
	public ModelAndView sendNotification(@ModelAttribute("notification") PushNotificationTracker notification,
			@RequestParam(required = false, name = "selected") String[] selected,
			@RequestParam(required = false, name = "pn_to") MultipartFile file,
			@RequestParam(required = false,name = "template") MultipartFile template,RedirectAttributes rd) throws IOException {
		ModelAndView mv = new ModelAndView("index");
		try {
			String contactfile = file.getOriginalFilename();
			String contactfileext = com.google.common.io.Files.getFileExtension(contactfile);

			String templatefile = template.getOriginalFilename();
			String templatefileext = com.google.common.io.Files.getFileExtension(templatefile);

			String splitBy = ",";

			ArrayList<String> msglist = new ArrayList<String>();
			ArrayList<String> contactlist = new ArrayList<String>();
			
			List<User> moblist = userservice.getAllUser();
			//List<String> contentlist = masterService.searchContent();
			System.out.println(moblist);
			//System.out.println(contentlist);
			if (contactfileext.equalsIgnoreCase("csv") && templatefileext.equalsIgnoreCase("csv")) {
				if (!file.isEmpty() && !template.isEmpty()) 
				{
					BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dambhare\\Downloads\\" + contactfile));
					BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Dambhare\\Downloads\\" + templatefile));

					String line = br.readLine();
					String line1 = br1.readLine();

					while ((line1 = br1.readLine()) != null && (line = br.readLine()) != null) 
					{
						String[] b = line.split(splitBy);
						String[] b1 = line1.split(splitBy);
						if (b.length != 2 && b1.length != 2) 
						{ // Skip any "weird" (e.g., empty) line
							continue;
						}
						contactlist.add(b[1]);
						msglist.add(b1[1]);
					}
					for (User u:moblist)
					{
						int id=Integer.parseInt(u.getLang());
						String templist=masterService.findSingleContent(id);
						
							if(contactlist.contains(u.getMobilenumber()))
								{
								if(msglist.contains(templist))
										{
													PushNotificationTracker track = new PushNotificationTracker();
														track.setPn_to(u.getMobilenumber()); 
														track.setMessage(templist); 
														track.setPn_sent_status("P");
														track.setSendafter(null);
														notificationTrackService.addNotification(track);
														rd.addFlashAttribute("success", "Bulk Notification has been sent succesfully!!!!!!!!!!!!!! ");
										}
														
										
										
								}
								else
								{ 
								    FileWriter writer = new FileWriter("C:\\Users\\Dambhare\\Downloads\\Invalid.csv" );
								    
									List<String> temp=new ArrayList<>();
									temp.add(u.getMobilenumber());
								    String collect = temp.stream().collect(Collectors.joining(","));
								    writer.write(collect);
								    rd.addFlashAttribute("mob"," is not registered");
								    writer.close();
								    temp.clear();
								}	
							
					}
					br.close();
					br1.close();
					}
			
				} 
			else if (contactfileext.equalsIgnoreCase("xls") && templatefileext.equalsIgnoreCase("xls")) 
			{
				
				FileInputStream fis = new FileInputStream("C:\\Users\\Dambhare\\Downloads\\" + contactfile);
				FileInputStream fis1 = new FileInputStream("C:\\Users\\Dambhare\\Downloads\\" + templatefile);

		        HSSFWorkbook workbook = new HSSFWorkbook(fis);
		        HSSFWorkbook workbook1 = new HSSFWorkbook(fis1);

		        HSSFSheet sheet = workbook.getSheetAt(0);
		        HSSFSheet sheet1 = workbook1.getSheetAt(0);

                Integer columnNo = 1;
                
                //output all not null values to the list
                List<Cell> mobcells = new ArrayList<Cell>();
                List<Cell> msgcells = new ArrayList<Cell>();
                if (!file.isEmpty() && !template.isEmpty()) 
				{
                if (columnNo != null)
                {
                    for (Row row : sheet) {
                    	Cell c = row.getCell(columnNo);
                       if(c.getStringCellValue().equalsIgnoreCase("Mobile"))
                       {
                    	   continue;
                       } 
                       else {
                          mobcells.add(c);
                       }
                    }
                    for (Row row : sheet1) {
                    	Cell c = row.getCell(columnNo);
                       if(c.getStringCellValue().equalsIgnoreCase("Template"))
                       {
                    	   continue;
                       } 
                       else {
                          msgcells.add(c);
                       }
                    }
                }
                for (Cell contact : mobcells)
				{
					for (Cell msg : msgcells) 
						{
						if(moblist.contains(contact.getStringCellValue()))
							{
							 PushNotificationTracker track = new PushNotificationTracker();
							  track.setPn_to(contact.getStringCellValue()); 
							  track.setMessage(msg.getStringCellValue()); 
							  track.setPn_sent_status("P");
							  notificationTrackService.addNotification(track);
							  rd.addFlashAttribute("success", "Bulk Notification has been sent succesfully!!!!!!!!!!!!!! ");

							}
							else
							{
								rd.addFlashAttribute(contact.getStringCellValue() );
							}
						}
				}
				}
               
                workbook.close();
                workbook1.close();
			} else if (contactfileext.equalsIgnoreCase("xlsx") && templatefileext.equalsIgnoreCase("xlsx")) {
				FileInputStream fis = new FileInputStream("C:\\Users\\Dambhare\\Downloads\\" + contactfile);
				FileInputStream fis1 = new FileInputStream("C:\\Users\\Dambhare\\Downloads\\" + templatefile);

		        XSSFWorkbook workbook = new XSSFWorkbook(fis);
		        XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);

		        XSSFSheet sheet = workbook.getSheetAt(0);
		        XSSFSheet sheet1 = workbook1.getSheetAt(0);

                Integer columnNo = 1;
                
                //output all not null values to the list
                List<Cell> mobcells = new ArrayList<Cell>();
                List<Cell> msgcells = new ArrayList<Cell>();
                
                if (!file.isEmpty() && !template.isEmpty()) 
				{
                if (columnNo != null)
                {
                    for (Row row : sheet) {
                    	Cell c = row.getCell(columnNo);
                       if(c.getStringCellValue().equalsIgnoreCase("Mobile"))
                       {
                    	   continue;
                       } 
                       else {
                          mobcells.add(c);
                       }
                    }
                    for (Row row : sheet1) {
                    	Cell c = row.getCell(columnNo);
                       if(c.getStringCellValue().equalsIgnoreCase("Template"))
                       {
                    	   continue;
                       } 
                       else {
                          msgcells.add(c);
                       }
                    }
                }
                for (Cell contact : mobcells)
				{
					for (Cell msg : msgcells) 
						{
						if(moblist.contains(contact.getStringCellValue()))
							{
							 PushNotificationTracker track = new PushNotificationTracker();
							  track.setPn_to(contact.getStringCellValue()); 
							  track.setMessage(msg.getStringCellValue()); 
							  track.setPn_sent_status("P");
							  notificationTrackService.addNotification(track);
							  rd.addFlashAttribute("success", "Bulk Notification has been sent succesfully!!!!!!!!!!!!!! ");

							}
							else
							{
								rd.addFlashAttribute(contact.getStringCellValue() );
							}
						}
				}
				}
                
                workbook.close();
                workbook1.close();
			} else {
				if(!template.isEmpty())
				{
					List<String> moblist1=userservice.searchMob();
				BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Dambhare\\Downloads\\" + templatefile));
				String line1 = br1.readLine();
				while ((line1 = br1.readLine()) != null) 
				{
										
				String[] b1 = line1.split(splitBy);
					if (b1.length != 2) 
					{ // Skip any "weird" (e.g., empty) line
					continue;
				}msglist.add(b1[1]);
				}
				if (!moblist1.isEmpty()) {
					for (String p : moblist1) {
						for (String msg:msglist) 
						{
							  PushNotificationTracker track=new PushNotificationTracker();
							  track.setPn_to(p); 
							  track.setMessage(msg);
							  track.setPn_sent_status("P");
							  notificationTrackService.addNotification(track);
							  rd.addFlashAttribute("success", "Bulk Notification has been sent succesfully!!!!!!!!!!!!!! ");
						}
					}
				}
				br1.close();
				}
				else
				{
					rd.addFlashAttribute("emptyerr", "Please select atleast one option");
				}
			}
		        
		} 
		 catch (Exception e) {
			e.printStackTrace();
		}

		return mv;

	}

}
