package com.java.employeesystem;

public class Department {
	private int department_id ;
	private String department_nm ;
	
	public Department() {}
	public Department(int department_id, String department_nm ) {
		super();
		this.department_id = department_id;
		this.department_nm = department_nm;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id ) {
		this.department_id = department_id;
	}
	public String getDepartment_nm() {
		return department_nm;
	}
	public void setDepartment_nm(String department_nm) {
		this.department_nm=department_nm;
	}
}