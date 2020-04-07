package com.notification.model;
// Generated Feb 26, 2020 12:48:49 PM by Hibernate Tools 5.2.12.Final

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@SuppressWarnings("serial")
@Entity
@Table(name = "NOTIFICATION_MASTER")
public class NotificationMaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int notificationId;
	private String notificationType;
	private String product;
	private String content;
	private int templateid;
	private Date creationDate;
	private String modifiedBy;
	private Date modifiedDate;

	public NotificationMaster(int notificationId,
			String notificationType,
			String product, String content, int createdBy,
			Date creationDate, String modifiedBy, Date modifiedDate) {
		super();
		this.notificationId = notificationId;
		this.notificationType = notificationType;
		this.product = product;
		this.content = content;
		this.setTemplateid(createdBy);
		this.creationDate = creationDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}




	public NotificationMaster() {
	}



	public int getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}


	public String getNotificationType() {
		return this.notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}


	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}




	public int getTemplateid() {
		return templateid;
	}




	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}

	
	


}
