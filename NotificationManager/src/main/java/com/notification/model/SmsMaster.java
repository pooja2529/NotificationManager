package com.notification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SMS_MASTER")
public class SmsMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SMSEVENTID")
	private int sms_event_id;
	public SmsMaster() {
		super();
	}
	public SmsMaster(int sms_event_id, String sms_event_name, String sms_template, String sms_template_ml,
			String sms_template_hi, String sms_template_mr, String sms_template_ta, String sms_template_kn,
			String sms_template_tr, String sms_to, int createdby, String createddatetime, int modifiedby,
			String modifieddatetime) {
		super();
		this.sms_event_id = sms_event_id;
		this.sms_event_name = sms_event_name;
		this.sms_template = sms_template;
		this.sms_template_ml = sms_template_ml;
		this.sms_template_hi = sms_template_hi;
		this.sms_template_mr = sms_template_mr;
		this.sms_template_ta = sms_template_ta;
		this.sms_template_kn = sms_template_kn;
		this.sms_template_te = sms_template_tr;
		this.sms_to = sms_to;
		this.createdby = createdby;
		this.createddatetime = createddatetime;
		this.modifiedby = modifiedby;
		this.modifieddatetime = modifieddatetime;
	}
	@Override
	public String toString() {
		return "SmsMaster [sms_event_id=" + sms_event_id + ", sms_event_name=" + sms_event_name + ", sms_template="
				+ sms_template + ", sms_template_ml=" + sms_template_ml + ", sms_template_hi=" + sms_template_hi
				+ ", sms_template_mr=" + sms_template_mr + ", sms_template_ta=" + sms_template_ta + ", sms_template_kn="
				+ sms_template_kn + ", sms_template_tr=" + sms_template_te + ", sms_to=" + sms_to + ", createdby="
				+ createdby + ", createddatetime=" + createddatetime + ", modifiedby=" + modifiedby
				+ ", modifieddatetime=" + modifieddatetime + "]";
	}
	@Column(name="SMS_EVENT_NAME")
	private String sms_event_name;
	@Column(name="SMS_TEMPLATE")
	private String sms_template;
	@Column(name="SMS_TEMPLATE_ML")
	private String sms_template_ml;
	@Column(name="SMS_TEMPLATE_HI")
	private String sms_template_hi;
	@Column(name="SMS_TEMPLATE_MR")
	private String sms_template_mr;
	@Column(name="SMS_TEMPLATE_TA")
	private String sms_template_ta;
	@Column(name="SMS_TEMPLATE_KN")
	private String sms_template_kn;
	@Column(name = "SMS_TEMPLATE_TE")
	private String sms_template_te;
	@Column(name = "SMS_TO")
	private String sms_to;
	@Column(name = "CREATED_BY")
	private int createdby;
	@Column(name = "CREATED_DATE_TIME")
	private String createddatetime;
	@Column(name = "MODIFIED_BY")
	private int modifiedby;
	@Column(name = "MODIFIED_DATE_TIME")
	private String modifieddatetime;
	
	public int getSms_event_id() {
		return sms_event_id;
	}
	public void setSms_event_id(int sms_event_id) {
		this.sms_event_id = sms_event_id;
	}
	public String getSms_event_name() {
		return sms_event_name;
	}
	public void setSms_event_name(String sms_event_name) {
		this.sms_event_name = sms_event_name;
	}
	public String getSms_template() {
		return sms_template;
	}
	public void setSms_template(String sms_template) {
		this.sms_template = sms_template;
	}
	public String getSms_template_ml() {
		return sms_template_ml;
	}
	public void setSms_template_ml(String sms_template_ml) {
		this.sms_template_ml = sms_template_ml;
	}
	public String getSms_template_hi() {
		return sms_template_hi;
	}
	public void setSms_template_hi(String sms_template_hi) {
		this.sms_template_hi = sms_template_hi;
	}
	public String getSms_template_mr() {
		return sms_template_mr;
	}
	public void setSms_template_mr(String sms_template_mr) {
		this.sms_template_mr = sms_template_mr;
	}
	public String getSms_template_ta() {
		return sms_template_ta;
	}
	public void setSms_template_ta(String sms_template_ta) {
		this.sms_template_ta = sms_template_ta;
	}
	public String getSms_template_kn() {
		return sms_template_kn;
	}
	public void setSms_template_kn(String sms_template_kn) {
		this.sms_template_kn = sms_template_kn;
	}
	
	public String getSms_template_te() {
		return sms_template_te;
	}
	public void setSms_template_te(String sms_template_te) {
		this.sms_template_te = sms_template_te;
	}
	public String getSms_to() {
		return sms_to;
	}
	public void setSms_to(String sms_to) {
		this.sms_to = sms_to;
	}
	public int getCreatedby() {
		return createdby;
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
	public int getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getModifieddatetime() {
		return modifieddatetime;
	}
	public void setModifieddatetime(String modifieddatetime) {
		this.modifieddatetime = modifieddatetime;
	}
	
	

}
