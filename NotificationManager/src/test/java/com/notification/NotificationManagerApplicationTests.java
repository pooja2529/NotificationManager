package com.notification;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class NotificationManagerApplicationTests {

	@Test
	void contextLoads() throws URISyntaxException {
		 RestTemplate restTemplate = new RestTemplate();
	     
		    final String baseUrl = "http://localhost:" + 8087 + "/Notification/verifyUser/";
		    URI uri = new URI(baseUrl);
		 
		    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		     
		    //Verify request succeed
		    Assert.assertEquals(200, result.getStatusCodeValue());
		    Assert.assertEquals(true, result.getBody().contains("Notification"));
	}

}
