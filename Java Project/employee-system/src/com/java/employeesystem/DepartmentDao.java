package com.java.employeesystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class DepartmentDao {
	public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee-db","root","Nkululeko_93");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(String departmentname)
	{
		int status=0;
		try{
			Connection con=DepartmentDao.getCon();
			PreparedStatement ps=con.prepareStatement("insert into department(department_nm) values(?)");
			ps.setString(1,departmentname);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(Department d){
		int status=0;
		try{
			Connection con=DepartmentDao.getCon();
			PreparedStatement ps=con.prepareStatement("update department set department_nm=? where department_id=?");
			ps.setString(1,d.getDepartment_nm());
			ps.setInt(2,d.getDepartment_id());
			status=ps.executeUpdate();
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<Department> view(){
		List<Department> list=new ArrayList<Department>();
		try{
			Connection con=DepartmentDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from department");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Department d=new Department();
				d.setDepartment_id(rs.getInt("department_id"));
				d.setDepartment_nm(rs.getString("department_nm"));
				list.add(d);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static Department getDepartmentByid(int department_id){
		Department d=new Department();
		try{
			Connection con=DepartmentDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from department where department_id=?");
			ps.setInt(1,department_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				d.setDepartment_id(rs.getInt("department_id"));
				d.setDepartment_nm(rs.getString("department_nm"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return d;
	}
	}