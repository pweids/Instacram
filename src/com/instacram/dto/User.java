package com.instacram.dto;

import java.util.ArrayList;
import java.util.Arrays;

import org.mybeans.dao.DAOException;

import com.instacram.dao.UserCourseDAO;

public class User {
	
	private String username;
	private String password;
	private String email;
	private ArrayList<Course> courses;
	
	public User(){
		this.courses = new ArrayList<Course>();
	}
	
	public User(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
		this.courses = new ArrayList<Course>();
	}
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username;}
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email;}
	
	public ArrayList<Course> getCourses() { 
		try {
			UserCourseDAO ucdao = new UserCourseDAO();
			courses = ucdao.getCourse(this.username);
			return courses;
		} catch (DAOException e) {
			
		}
		return null;
	}
	
	public void addCourse(Course course) {
		try {
			UserCourseDAO ucdao = new UserCourseDAO();
			ucdao.addUserCourse(this.username, course.getName());
		} catch (DAOException e) {
			
		}
	}
}