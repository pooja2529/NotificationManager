package com.notification.util;

import org.springframework.stereotype.Component;

@Component
public class ConstantFieldVerification 
{

public static  final String product= "sk|hfc|cfl";
public static  final String type="email|sms|pn";


static String prod[]=product.split("\\|");
static String t[]=type.split("\\|");

public static boolean getType(String status)
{
	
	for(int i=0;i<t.length;i++)
	{
		if(t[i]==status)
		{
			return true;
		}
	}
	return false;
	
	
}
public static boolean getProduct(String status)
{
	
	for(int i=0;i<prod.length;i++)
	{
		if(prod[i]==status)
		{
			return true;
		}
	}
	return false;
	
	
}

}
