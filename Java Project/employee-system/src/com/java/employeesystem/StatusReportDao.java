package com.java.employeesystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
public class StatusReportDao {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee-db","root","Nkululeko_93");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(int complianceid,int empid,String comments,Date createdate,int departmentid){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into statusreport(complianceid,empid,comments,createDate,department_id) values(?,?,?,?,?)");
			ps.setInt(1,complianceid);
			ps.setInt(2, empid);
			ps.setString(3,comments);
			ps.setDate(4, createdate);
			ps.setInt(5,departmentid);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(Statusreport s){
		int status=0;
		try{
			Connection con=StatusReportDao.getCon();
			PreparedStatement ps=con.prepareStatement("update statusreport set complianceid=?,empid=?,comments=?,createDate=?,department_id=? where statusrptid=?");
			ps.setInt(1,s.getComplianceid());
			ps.setInt(2, s.getEmpid());
			ps.setString(3,s.getComments());
			ps.setDate(4, new java.sql.Date(s.getCreateDate().getTime()));
			ps.setInt(5,s.getDepartment_id());
			ps.setInt(6,s.getStatusrptid());
			status=ps.executeUpdate();
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<Statusreport> view(){
		List<Statusreport> list=new ArrayList<Statusreport>();
		try{
			Connection con=StatusReportDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from statusreport");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Statusreport s=new Statusreport();
				s.setComplianceid(rs.getInt("complianceid"));
				s.setStatusrptid(rs.getInt("statusrptid"));
				s.setEmpid(rs.getInt("empid"));
				s.setComments(rs.getString("comments"));
				s.setCreateDate(rs.getDate("createDate"));
				s.setDepartment_id(rs.getInt("department_id"));
				list.add(s);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static Statusreport getStatusreportByid(int statusrptid){
		Statusreport s=new Statusreport();
		try{
			Connection con=StatusReportDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from statusreport where statusrptid=?");
			ps.setInt(1,statusrptid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				s.setComplianceid(rs.getInt("complianceid"));
				s.setStatusrptid(rs.getInt("statusrptid"));
				s.setEmpid(rs.getInt("empid"));
				s.setComments(rs.getString("comments"));
				s.setCreateDate(rs.getDate("createDate"));
				s.setDepartment_id(rs.getInt("department_id"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return s;
	}
}