package com.instacram.dto;

public class Course {

	private String name;
	private String instructor;
	private String creator;
	
	public Course(String name, String instructor, String creator) {
		this.name = name;
		this.instructor = instructor;
		this.creator = creator;
	}
	
	public Course(String name, String creator) {
		this.name = name;
		this.creator = creator;
		this.instructor = null;
	}
	
	public Course () {
		this.name = null;
		this.creator = null;
		this.instructor = null;
	}
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
