package com.zhangzhiqiang.android.journal.model;

import java.io.Serializable;

public class Journal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	String title;
	String content;
	String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
