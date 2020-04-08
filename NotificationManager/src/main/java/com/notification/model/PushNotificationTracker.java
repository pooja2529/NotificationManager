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
@Table(name = "PUSH_NOTIFICATION_TRACKER")
public class PushNotificationTracker
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	public String getPn_type() {
		return pn_type;
	}

	public void setPn_type(String pn_type) {
		this.pn_type = pn_type;
	}
	 private String template;
	@Column(name = "PN_TYPE")
	private String pn_type;
	
	@Column(name = "MESSAGE")
	private String message;

	@Column(name="PN_TITLE")
	private String pn_title;

	@Column(name = "IMAGE")
	private String image;

	@Column(name="PN_TO")
	private String pn_to;

	@Column(name = "DEVICEID")
	private Long device_id;
	
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
	
	@Column(name="PN_SENT_STATUS")
	private String pn_sent_status;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name = "BITLY_URL")
	private String bitlyurl;
	
	public String getBitlyurl() {
		return bitlyurl;
	}

	public void setBitlyurl(String bitlyurl) {
		this.bitlyurl = bitlyurl;
	}

	@Column(name="NOTIFICATION_TYPE_ID")
	private Long notification_type_id;

	public PushNotificationTracker() {
		super();
	}

	

	

	@Override
	public String toString() {
		return "PushNotificationTracker [id=" + id + ", pn_type=" + pn_type + ", message=" + message + ", pn_title="
				+ pn_title + ", image=" + image + ", pn_to=" + pn_to + ", device_id=" + device_id + ", created_by="
				+ created_by + ", createddatetime=" + createddatetime + ", modifiedby=" + modifiedby
				+ ", modifieddatetime=" + modifieddatetime + ", pn_sent_status=" + pn_sent_status + ", remark=" + remark
				+ ", bitlyurl=" + bitlyurl + ", notification_type_id=" + notification_type_id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Long device_id) {
		this.device_id = device_id;
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

	public String getPn_sent_status() {
		return pn_sent_status;
	}

	public void setPn_sent_status(String pn_sent_status) {
		this.pn_sent_status = pn_sent_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getNotification_type_id() {
		return notification_type_id;
	}

	public void setNotification_type_id(Long notification_type_id) {
		this.notification_type_id = notification_type_id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
