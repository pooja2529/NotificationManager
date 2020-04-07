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
@Table(name = "SMS_TRACKER")
public class SmsTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int sms_id;
	@Column(name="SMS_TO")
	private String smsto;
	@Column(name="SMS_TEMPLATE")
	private String smstemplate;
	
	public Date getCreateddatetime() {
		return createddatetime;
	}
	public void setCreateddatetime(Date createddatetime) {
		this.createddatetime = createddatetime;
	}
	@Column(name="SENT_STATUS")
	private String smsstatus;
	@Column(name="DATA_PIC_STATUS")
	private String datapicstatus;
	@Column(name="USER_ID")
	private int userid;
	@Column(name="CREATEDBY")
	private int createdby;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE_TIME")
	private Date createddatetime = new java.sql.Date(new java.util.Date().getTime());
	@Column(name = "MODIFIEDBY")
	private int modifiedby;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFIED_DATE_TIME")
	private Date modifieddatetime = new java.sql.Date(new java.util.Date().getTime());
	@Column(name="REMARK")
	private String remark;
	@Column(name="SMS_EVENT_NAME")
	private String smseventname;
	@Override
	public String toString() {
		return "SmsTracker [sms_id=" + sms_id + ", smsto=" + smsto + ", smstemplate=" + smstemplate + ", smsstatus="
				+ smsstatus + ", datapicstatus=" + datapicstatus + ", userid=" + userid + ", createdby=" + createdby
				+ ", createddataetime=" + createddatetime + ", modifiedby=" + modifiedby + ", modifieddatetime="
				+ modifieddatetime + ", remark=" + remark + ", smseventname=" + smseventname + "]";
	}
	public SmsTracker() {
		super();
	}
	public SmsTracker(int sms_id, String smsto, String smstemplate, String smsstatus, String datapicstatus, int userid,
			int createdby, Date createddataetime, int modifiedby, Date modifieddatetime, String remark,
			String smseventname) {
		super();
		this.sms_id = sms_id;
		this.smsto = smsto;
		this.smstemplate = smstemplate;
		this.smsstatus = smsstatus;
		this.datapicstatus = datapicstatus;
		this.userid = userid;
		this.createdby = createdby;
		this.createddatetime = createddataetime;
		this.modifiedby = modifiedby;
		this.modifieddatetime = modifieddatetime;
		this.remark = remark;
		this.smseventname = smseventname;
	}
	public int getSms_id() {
		return sms_id;
	}
	public void setSms_id(int sms_id) {
		this.sms_id = sms_id;
	}
	public String getSmsto() {
		return smsto;
	}
	public void setSmsto(String smsto) {
		this.smsto = smsto;
	}
	public String getSmstemplate() {
		return smstemplate;
	}
	public void setSmstemplate(String smstemplate) {
		this.smstemplate = smstemplate;
	}
	public String getSmsstatus() {
		return smsstatus;
	}
	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}
	public String getDatapicstatus() {
		return datapicstatus;
	}
	public void setDatapicstatus(String datapicstatus) {
		this.datapicstatus = datapicstatus;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSmseventname() {
		return smseventname;
	}
	public void setSmseventname(String smseventname) {
		this.smseventname = smseventname;
	}

}
