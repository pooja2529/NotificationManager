package com.notification.service;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationService 
{
	public final static String AUTH_KEY_FCM = "xyxyxyxxyxyxyxyxyxyxyxy";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	// userDeviceIdKey is the device id you will query from your database

	@SuppressWarnings("unchecked")
	public static void pushFCMNotification(List<String> userDeviceIdKey) throws Exception{
		try {
			String authKey = AUTH_KEY_FCM; // You FCM AUTH key
			String FMCurl = API_URL_FCM; 

			URL url = new URL(FMCurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization","key="+authKey);
			conn.setRequestProperty("Content-Type","application/json");

			JSONObject json = new JSONObject();
			for (String devicekey : userDeviceIdKey) {
				json.put("to",devicekey.trim());
			}

			JSONObject info = new JSONObject();
			info.put("title", "Notificatoin Title"); // Notification title
			info.put("body", "Hello Test notification"); // Notification body
			json.put("notification", info);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			conn.getInputStream();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
