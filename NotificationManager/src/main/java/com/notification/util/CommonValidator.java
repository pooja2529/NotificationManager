package com.notification.util;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator 
{

	//it  checks whether input mobile number contains String or null data

	public boolean isValidMobileNumber(List<String> mobilenumber )//true
	{
		String regex = "(0/91)?[7-9][0-9]{9}";
		
		try
		{
			for (String mob : mobilenumber) {
				if (mob!=null  && mob.length()>0 && mob.matches(regex)) //&& mobilenumber.matches(regex)
				{
					return true;
				}
			}
			

		}
		catch (Exception e) {
			System.out.println("If NullPointer Exception occured");
		}
		return false;

	}


	//it's chceks for whether input String contains null or integer values.
	public boolean isValidString(String notificationType)
	{

		try {
			if(notificationType!=null && notificationType.length()>0 && notificationType.matches("[0-9]")==false )
			{
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("Please enter valid notification type");
		}
		return false;


	}

	public boolean getStringBetweenTwoChars(String input, String startChar, String endChar) {
	    try {
	        int start = input.indexOf(startChar);
	        if (start != -1) {
	            int end = input.indexOf(endChar, start + startChar.length());
	            if (end != -1) {
	                return true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false; // return null; || return "" ;
	}


}
