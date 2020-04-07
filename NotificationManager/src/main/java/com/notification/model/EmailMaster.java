package com.notification.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "EMAIL_MASTER")
public class EmailMaster implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENTID")
private int eventid;
	@Column(name = "EMAIL_EVENT_NAME")
private String email_event_name;
	@Column(name = "EMAILFROM")
private String emailfrom;
@Transient 
@Column(name = "TO")
private List<String> to;
@Column(name="CC")
private String cc;
@Column(name = "BCC")
private String bcc;
@Column(name = "SUBJECT")
private String subject;
@Column(name = "BODY")
private String body;
@Column(name = "ATTACHMENT")
private String attachment;
@Column(name="INLINEIMAGE")
private String inlineimage;
@Column(name="CREATED_BY")
private int createdby;
@Column(name="CREATED_DATE_TIME")
private String createddatetime;
@Column(name="MODIFIED_BY")
private int modifiedby;
@Column(name="MODIFIED_DATE")
private String modifieddatetime;



public EmailMaster() {
	super();
}

public EmailMaster(int eventid, String email_event_name, String emailfrom, List<String> to, String cc, String bcc,
		String subject, String body, String attachment, String inlineimage, int createdby, String createddatetime,
		int modifedby, String modifieddatetime) {
	super();
	this.eventid = eventid;
	this.email_event_name = email_event_name;
	this.emailfrom = emailfrom;
	this.to = to;
	this.cc = cc;
	this.bcc = bcc;
	this.subject = subject;
	this.body = body;
	this.attachment = attachment;
	this.inlineimage = inlineimage;
	this.createdby = createdby;
	this.createddatetime = createddatetime;
	this.modifiedby = modifedby;
	this.modifieddatetime = modifieddatetime;
}
@Override
public String toString() {
	return "EmailMaster [eventid=" + eventid + ", email_event_name=" + email_event_name + ", emailfrom=" + emailfrom
			+ ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", body=" + body + ", attachment="
			+ attachment + ", inlineimage=" + inlineimage + ", createdby=" + createdby + ", createddatetime="
			+ createddatetime + ", modifedby=" + modifiedby + ", modifieddatetime=" + modifieddatetime + "]";
}

public String getEmailfrom() {
	return emailfrom;
}
public void setEmailfrom(String emailfrom) {
	this.emailfrom = emailfrom;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getAttachment() {
	return attachment;
}
public void setAttachment(String attachment) {
	this.attachment = attachment;
}
public String getEmail_event_name() {
	return email_event_name;
}

public void setEmail_event_name(String email_event_name) {
	this.email_event_name = email_event_name;
}
public List<String> getTo() {
	return to;
}



public void setTo(List<String> to) {
	this.to = to;
}
public String getCc() {
	return cc;
}



public void setCc(String cc) {
	this.cc = cc;
}
public String getBcc() {
	return bcc;
}



public void setBcc(String bcc) {
	this.bcc = bcc;
}
public String getBody() {
	return body;
}



public void setBody(String body) {
	this.body = body;
}
public String getInlineimage() {
	return inlineimage;
}
public void setInlineimage(String inlineimage) {
	this.inlineimage = inlineimage;
}
public int getCreatedby() {
	return createdby;
}
public int getEventid() {
	return eventid;
}
public void setEventid(int eventid) {
	this.eventid = eventid;
}
public int getModifiedby() {
	return modifiedby;
}
public void setModifiedby(int modifiedby) {
	this.modifiedby = modifiedby;
}
public void setCreatedby(int createdby) {
	this.createdby = createdby;
}
public String getCreateddatetime() {
	return createddatetime;
}
public void setCreateddatetime(String createddatetime) {
	this.createddatetime = createddatetime;
}

public String getModifieddatetime() {
	return modifieddatetime;
}
public void setModifieddatetime(String modifieddatetime) {
	this.modifieddatetime = modifieddatetime;
}


}
