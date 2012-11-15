package com.instacram.dto;

import java.util.Date;
import java.util.Random;

public class UserCourse {
	private int id;
	private String username;
	private String coursename;
	
	public UserCourse(){
		
	}
	
	public UserCourse(String username, String coursename) {
		Random r = new Random();
		this.id = r.nextInt(2048);
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
