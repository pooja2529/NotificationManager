package com.notification.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "PUSH_NOTIFICATION_MASTER")
public class PushNotificationMaster 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "PN_ID")
private int pn_id;

@Column(name = "PN_TYPE")
private String pn_type;

@Column(name = "BITLY_URL")
private String bitlyurl;

@Column(name = "PN_FROM")
private String pn_from;

public String getPn_type() {
	return pn_type;
}

public void setPn_type(String pn_type) {
	this.pn_type = pn_type;
}

public String getBitlyurl() {
	return bitlyurl;
}

public void setBitlyurl(String bitlyurl) {
	this.bitlyurl = bitlyurl;
}

@Column(name = "MESSAGE",length = 6000,columnDefinition = "VARCHAR(6000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL")
private String message;

@Column(name="PN_TITLE")
private String pn_title;

@Column(name = "IMAGE")
private String image;

@Column(name="PN_TO")
private String pn_to;

@Column(name = "CREATED_BY")
private int created_by;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="CREATED_DATE_TIME")
private Date createddatetime = new java.sql.Date(new java.util.Date().getTime());

@Column(name = "MODIFIEDBY")
private int modifiedby;

@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name="MODIFIED_DATE_TIME")
private Date modifieddatetime = new java.sql.Date(new java.util.Date().getTime());

public PushNotificationMaster() {
	super();
}



public PushNotificationMaster(int pn_id, String pn_type, String bitlyurl, String pn_from, String message,
		String pn_title, String image, String pn_to, int created_by, Date createddatetime, int modifiedby,
		Date modifieddatetime) {
	super();
	this.pn_id = pn_id;
	this.pn_type = pn_type;
	this.bitlyurl = bitlyurl;
	this.pn_from = pn_from;
	this.message = message;
	this.pn_title = pn_title;
	this.image = image;
	this.pn_to = pn_to;
	this.created_by = created_by;
	this.createddatetime = createddatetime;
	this.modifiedby = modifiedby;
	this.modifieddatetime = modifieddatetime;
}

@Override
public String toString() {
	return "PushNotificationMaster [pn_id=" + pn_id + ", pn_type=" + pn_type + ", bitlyurl=" + bitlyurl + ", pn_from="
			+ pn_from + ", message=" + message + ", pn_title=" + pn_title + ", image=" + image + ", pn_to=" + pn_to
			+ ", created_by=" + created_by + ", createddatetime=" + createddatetime + ", modifiedby=" + modifiedby
			+ ", modifieddatetime=" + modifieddatetime + "]";
}

public int getPn_id() {
	return pn_id;
}

public void setPn_id(int pn_id) {
	this.pn_id = pn_id;
}

public String getPn_from() {
	return pn_from;
}

public void setPn_from(String pn_from) {
	this.pn_from = pn_from;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getPn_title() {
	return pn_title;
}

public void setPn_title(String pn_title) {
	this.pn_title = pn_title;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getPn_to() {
	return pn_to;
}

public void setPn_to(String pn_to) {
	this.pn_to = pn_to;
}

public int getCreated_by() {
	return created_by;
}

public void setCreated_by(int created_by) {
	this.created_by = created_by;
}

public Date getCreateddatetime() {
	return createddatetime;
}

public void setCreateddatetime(Date createddatetime) {
	this.createddatetime = createddatetime;
}

public int getModifiedby() {
	return modifiedby;
}

public void setModifiedby(int modifiedby) {
	this.modifiedby = modifiedby;
}

public Date getModifieddatetime() {
	return modifieddatetime;
}

public void setModifieddatetime(Date modifieddatetime) {
	this.modifieddatetime = modifieddatetime;
}

}
