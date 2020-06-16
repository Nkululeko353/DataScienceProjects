package com.java.employeesystem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDao {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee-db","root","Nkululeko_93");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(int departmentid,String firstname,String lastname,String email,Date dob){
		int status=0;
		try{
			Connection con=EmployeeDao.getCon();
			PreparedStatement ps=con.prepareStatement("insert into employees(department_id,firstname,lastname,email,dob) values(?,?,?,?,?)");
			ps.setInt(1,departmentid);
			ps.setString(2,firstname);
			ps.setString(3, lastname);
			ps.setString(4,email);
			ps.setDate(5,dob);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	public static int delete(int empid){
		int status=0;
		try{
			Connection con=EmployeeDao.getCon();
			PreparedStatement ps=con.prepareStatement("delete from employees where empid=?");
			ps.setInt(1,empid);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(Employees em){
		int status=0;
		try{
			Connection con=EmployeeDao.getCon();
			PreparedStatement ps=con.prepareStatement("update employees set firstname=?,lastname=?,dob=?,email=?,department_id=? where empid=?");
			ps.setString(1,em.getFirstname());
			ps.setString(2, em.getLasttname());
			ps.setDate(3, new java.sql.Date(em.getDob().getTime()));
			ps.setString(4, em.getEmail());
			ps.setInt(5, em.getDepartment_id());
			ps.setInt(6,em.getEmpid());
			status=ps.executeUpdate();
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<Employees> view(){
		List<Employees> list=new ArrayList<Employees>();
		try{
			Connection con=EmployeeDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from employees");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Employees em=new Employees();
				em.setEmpid(rs.getInt("empid"));
				em.setFirstname(rs.getString("firstname"));
				em.setLasttname(rs.getString("lastname"));
				em.setDob(rs.getDate("dob"));
				em.setEmail(rs.getString("email"));
				em.setDepartment_id(rs.getInt("department_id"));
				list.add(em);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static Employees getEmployeeByid(int empid){
		Employees em=new Employees();
		try{
			Connection con=EmployeeDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from employees where empid=?");
			ps.setInt(1,empid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				em.setEmpid(rs.getInt("empid"));
				em.setFirstname(rs.getString("firstname"));
			    em.setLasttname(rs.getString("lastname"));
				em.setDob(rs.getDate("dob"));
				em.setEmail(rs.getString("email"));
				em.setDepartment_id(rs.getInt("department_id"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return em;
	}
}