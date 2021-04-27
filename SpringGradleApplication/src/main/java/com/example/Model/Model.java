package com.example.Model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Model 
{
	@Id
	int id;
	String firstName;
	String lastName;
	String college;
	@CreatedDate
	String date=LocalDate.now().toString();


	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date=LocalDate.now().toString();
	}
	public int getId() {
		return id;
	}
	public Model(int id, String firstName, String lastName, String college,String Date) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.college = college;
		this.date=LocalDate.now().toString();
	}
	public Model() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}

}

