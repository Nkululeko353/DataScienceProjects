package com.java.employeesystem;

import java.sql.Date;

public class Compliance {
	private int  compliance_id,department_id;
	private String rlType ,details;
	private Date  createDate; 
	
	public Compliance() {}
	public Compliance(int compliance_id,int department_id, String rlType,String details, Date createDate)
	{
	super();
	this.compliance_id=compliance_id;
	this.department_id = department_id;
	this. rlType =  rlType;
	this.details = details;
	this.createDate = createDate;
	}
	public int getCompliance_id()
	{
		return compliance_id;
	}
	public void setCompliance_id(int compliance_id)
	{
		this.compliance_id=compliance_id;
	}
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getRlType() {
		return rlType;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDetails() {
		return details;
	}
	public void setRlType(String rlType) {
		this.rlType = rlType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}