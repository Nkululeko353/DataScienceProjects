package com.java.employeesystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewEmployees extends JFrame {
	static ViewEmployees frame;
	public ViewEmployees() {
		//Code to view data in JTable
		List<Employees> list=EmployeeDao.view();
		int size=list.size();
		
		String data[][]=new String[size][6];
		int row=0;
		for(Employees e1:list){
			data[row][0]=String.valueOf(e1.getEmpid());
			data[row][1]=e1.getFirstname();
			data[row][2]=e1.getLasttname();
			data[row][3]=String.valueOf(e1.getDob());
			data[row][4]=e1.getEmail();
			data[row][5]=String.valueOf(e1.getDepartment_id());
			row++;
		}
		String columnNames[]={"EmpID","FirstName","LastName","DOB","Email","DepartmentID"};
		
		JTable jt=new JTable(data,columnNames);
		JScrollPane sp=new JScrollPane(jt);
		add(sp);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewEmployees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
