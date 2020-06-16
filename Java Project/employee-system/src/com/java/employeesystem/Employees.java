package com.java.employeesystem;

import java.sql.Date;

public class Employees {
	private int empid,department_id;
	private String firstname,lastname ,email;
	private Date dob;
	public Employees() {}
	public Employees(int empid , String firstname,String lastname, String email,Date dob,int department_id)
	{
	super();
	this.empid = empid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.dob = dob;
	this.department_id=department_id;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname ) {
		this.firstname = firstname;
	}
	public String getLasttname() {
		return lastname;
	}
	public void setLasttname(String lastname ) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email ) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob ) {
		this.dob = dob;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id=department_id;
	}
}