package com.java.employeesystem;

import java.sql.Date;

public class Statusreport {
	private int complianceid,statusrptid,empid,department_id  ;
	private String comments;
	private Date createDate;
	
	public Statusreport() {}
	public Statusreport(int complianceid , int statusrptid,int empid,int department_id,String comments,Date createDate) {
		super();
		this.complianceid = complianceid;
		this.statusrptid = statusrptid;
		this.empid = empid;
		this.department_id = department_id;
		this.comments =comments ;
		this.createDate = createDate;
	}
	public int getComplianceid() {
		return complianceid;
	}
	public void setComplianceid(int complianceid ) {
		this.complianceid = complianceid;
	}
	public int getStatusrptid() {
		return statusrptid;
	}
	public void setStatusrptid(int statusrptid ) {
		this.statusrptid = statusrptid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid ) {
		this.empid = empid;
	}
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id ) {
		this.department_id = department_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments=comments;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate=createDate;
	}
}
	