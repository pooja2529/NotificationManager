package com.notification.controller;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class Practice {
	
		    
		 public static void main(String[] args) {
             try {
                     String recipient = "9136578317";
                     String message = "Hello World";
                     String username = "admin";
                     String password = "abc123";
                     String originator = "9702247950";

                     String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
         "username=" + URLEncoder.encode(username, "UTF-8") +
         "&password=" + URLEncoder.encode(password, "UTF-8") +
         "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
         "&messagetype=SMS:TEXT" +
         "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
         "&originator=" + URLEncoder.encode(originator, "UTF-8") +
         "&serviceprovider=GSMModem1" +
         "&responseformat=html";



                     URL url = new URL(requestUrl);
                     System.out.println(requestUrl);
                     HttpURLConnection uc = (HttpURLConnection)url.openConnection();

                     System.out.println(uc.getResponseMessage());

                     uc.disconnect();

             } catch(Exception ex) {
                     System.out.println(ex.getMessage());

             }
     }
		 
}
	/*
	 * public static void main(String[] args) { B p=B.getInstance();
	 * System.out.println(p);
	 * 
	 * String path="src/main/resources/81H.jpg"; File name=new File(path);
	 * System.out.println(name.getAbsolutePath());
	 * 
	 * }
	 */
	


/*
 * class B {
 * 
 * private static B single_instance = null;
 * 
 * private B() { System.out.println("This is singleton"); }
 * 
 * public static B getInstance() { if(single_instance==null) {
 * single_instance=new B(); } return single_instance;
 * 
 * } }
 */