package com.instacram.dto;

import java.util.Date;
import java.util.Random;

public class Message {
	private int id;
	private String content;
	private Date date;
	private String username;
	private String coursename;
	
	public Message(){
		
	}
	
	public Message(String content, Date date, String username, String coursename) {
		Random r = new Random();
		this.id = r.nextInt(2048);
		this.content = content;
		this.date = date;
		this.username = username;
		this.coursename = coursename;
	}
	
	
	
	// Getters and Setters Below
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
}
