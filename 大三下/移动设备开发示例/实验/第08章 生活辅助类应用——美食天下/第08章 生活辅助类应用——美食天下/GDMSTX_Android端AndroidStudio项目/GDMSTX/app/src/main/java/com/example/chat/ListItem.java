package com.example.chat;

public class ListItem
{
	String id;
	String uid;
	String date;
	String picName;
	String content;
	String sculture;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDate() {
		int indexOf=date.lastIndexOf(".");
		return date.substring(0, indexOf);
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSculture() {
		return sculture;
	}
	public void setSculture(String sculture) {
		this.sculture = sculture;
	}
}