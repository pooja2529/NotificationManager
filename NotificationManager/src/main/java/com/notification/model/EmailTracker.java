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
@Table(name = "EMAIL_TRACKER")
public class EmailTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="EMAILFROM")
	private String emailfrom;
	@Column(name = "EMAILTO")
	private String emailto;
	@Column(name="CC")
	private String cc;
	@Column(name="BCC")
	private String bcc;
	@Column(name="SUBJECT")
	private String subject;
	@Column(name="BODY")
	private String body;
	@Column(name="ATTACHMENT")
	private String attachment;
	@Column(name="INLINEIMAGE")
	private String inlineimage;
	@Column(name="SENT_STATUS")
	private String sentstatus;
	@Column(name="DATA_PIC_STATUS")
	private String datapicstatus;
	@Column(name="CREATEDBY")
	private int createdby;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE_TIME")
	private Date createddatetime = new java.sql.Date(new java.util.Date().getTime());
	@Column(name="MODIFIEDBY")
	private int modifiedby;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFIED_DATE_TIME")
	private Date modifieddatetime = new java.sql.Date(new java.util.Date().getTime());
	@Column(name="REMARK")
	private String remark;
	@Override
	public String toString() {
		return "EmailTracker [id=" + id + ", emailfrom=" + emailfrom + ", emailto=" + emailto + ", cc=" + cc + ", bcc="
				+ bcc + ", subject=" + subject + ", body=" + body + ", attachment=" + attachment + ", inlineimage="
				+ inlineimage + ", sentstatus=" + sentstatus + ", datapicstatus=" + datapicstatus + ", createdby="
				+ createdby + ", createddatetime=" + createddatetime + ", modifiedby=" + modifiedby
				+ ", modifieddatetime=" + modifieddatetime + ", remark=" + remark + "]";
	}
	public EmailTracker(int id, String emailfrom, String emailto, String cc, String bcc, String subject, String body,
			String attachment, String inlineimage, String sentstatus, String datapicstatus, int createdby,
			Date createddatetime, int modifiedby, Date modifieddatetime, String remark) {
		super();
		this.id = id;
		this.emailfrom = emailfrom;
		this.emailto = emailto;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		this.attachment = attachment;
		this.inlineimage = inlineimage;
		this.sentstatus = sentstatus;
		this.datapicstatus = datapicstatus;
		this.createdby = createdby;
		this.createddatetime = createddatetime;
		this.modifiedby = modifiedby;
		this.modifieddatetime = modifieddatetime;
		this.remark = remark;
	}
	public EmailTracker() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailfrom() {
		return emailfrom;
	}
	public void setEmailfrom(String emailfrom) {
		this.emailfrom = emailfrom;
	}
	public String getEmailto() {
		return emailto;
	}
	public void setEmailto(String emailto) {
		this.emailto = emailto;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getInlineimage() {
		return inlineimage;
	}
	public void setInlineimage(String inlineimage) {
		this.inlineimage = inlineimage;
	}
	public String getSentstatus() {
		return sentstatus;
	}
	public void setSentstatus(String sentstatus) {
		this.sentstatus = sentstatus;
	}
	public String getDatapicstatus() {
		return datapicstatus;
	}
	public void setDatapicstatus(String datapicstatus) {
		this.datapicstatus = datapicstatus;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
