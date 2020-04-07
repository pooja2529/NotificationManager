package com.notification.model;

import java.util.List;

public class Notification 
{
private int masterid;
private String notificationtype;
private String product;
private List<String> mobile;
private List<String> email;
private List<Integer> userid;
public int getMasterid() {
	return masterid;
}
public void setMasterid(int masterid) {
	this.masterid = masterid;
}
public String getNotificationtype() {
	return notificationtype;
}
public void setNotificationtype(String notificationtype) {
	this.notificationtype = notificationtype;
}
public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}
public List<String> getMobile() {
	return mobile;
}
public void setMobile(List<String> mobile) {
	this.mobile = mobile;
}
public Notification() {
	super();
}
public Notification(int masterid, String notificationtype, String product, List<String> mobile, List<Integer> userid) {
	super();
	this.masterid = masterid;
	this.notificationtype = notificationtype;
	this.product = product;
	this.mobile = mobile;
	this.userid = userid;
}
@Override
public String toString() {
	return "Notification [masterid=" + masterid + ", notificationtype=" + notificationtype + ", product=" + product
			+ ", mobile=" + mobile + ", email=" + email + ", userid=" + userid + "]";
}
public List<Integer> getUserid() {
	return userid;
}
public void setUserid(List<Integer> userid) {
	this.userid = userid;
}
public List<String> getEmail() {
	return email;
}
public void setEmail(List<String> email) {
	this.email = email;
}
}
