package com.notification.service;

public class CustomErrorType 
{
	private String Status;
	private String Message;
	 
    public CustomErrorType(String Status,String Message){
        this.Message = Message;
        this.Status=Status;
    }
 
  

	public String getMessage() {
        return Message;
    }



	public String getStatus() {
		return Status;
	}

}
