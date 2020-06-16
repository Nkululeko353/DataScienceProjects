package com.java.employeesystem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ComplianceDao {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee-db","root","Nkululeko_93");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save( int department_id,String rltype,String details,Date createdate){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into compliance(department_id,rlType,details,createDate) values(?,?,?,?)");
			ps.setInt(1,department_id);
			ps.setString(2,rltype);
			ps.setString(3,details);
			ps.setDate(4,createdate);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(Compliance c){
		int status=0;
		try{
			Connection con=ComplianceDao.getCon();
			PreparedStatement ps=con.prepareStatement("update compliance set rlType=?,details=?,createDate=?,department_id=? where complianceid=?");
			ps.setString(1,c.getRlType());
			ps.setString(2, c.getDetails());
			ps.setDate(3,new java.sql.Date(c.getCreateDate().getTime()));
			ps.setInt(4, c.getDepartment_id());
			ps.setInt(5,c.getCompliance_id());
			status=ps.executeUpdate();
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<Compliance> view(){
		List<Compliance> list=new ArrayList<Compliance>();
		try{
			Connection con=ComplianceDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from compliance");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Compliance c=new Compliance();
				c.setCompliance_id(rs.getInt("complianceid"));
				c.setRlType(rs.getString("rlType"));
				c.setDetails(rs.getString("details"));
				c.setCreateDate(rs.getDate("createDate"));
				c.setDepartment_id(rs.getInt("department_id"));
				list.add(c);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static Compliance getComplianceByid(int complianceid){
		Compliance c=new Compliance();
		try{
			Connection con=ComplianceDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from compliance where complianceid=?");
			ps.setInt(1,complianceid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				c.setCompliance_id(rs.getInt("complianceid"));
				c.setRlType(rs.getString("rlType"));
				c.setDetails(rs.getString("details"));
				c.setCreateDate(rs.getDate("createDate"));
				c.setDepartment_id(rs.getInt("department_id"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return c;
	}
}