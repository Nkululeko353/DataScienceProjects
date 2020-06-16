package com.java.employeesystem;

public class Login_master {
	private int userid    ;
	private String  password  ;
	private String  role;
	
	public Login_master() {}
	
	public Login_master(int userid, String password,String role ) {
		super();
		this.userid =userid ;
		this.password = password;
		this.role = role;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setDepartment_id(int userid ) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
}