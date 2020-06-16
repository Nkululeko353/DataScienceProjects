package com.java.employeesystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewComments extends JFrame {
	static ViewComments frame;
	public ViewComments() {
		//Code to view data in JTable
		List<Statusreport> list=StatusReportDao.view();
		int size=list.size();
		
		String data[][]=new String[size][6];
		int row=0;
		for(Statusreport s:list){
			data[row][0]=String.valueOf(s.getStatusrptid());
			data[row][1]=String.valueOf(s.getComplianceid());
			data[row][2]=String.valueOf(s.getEmpid());
			data[row][3]=s.getComments();
			data[row][4]=String.valueOf(s.getCreateDate());
			data[row][5]=String.valueOf(s.getDepartment_id());
			row++;
		}
		String columnNames[]={"StatusRPTID","ComplianceID","EMPID","Comments","CreateDate","DepartmentID"};
		
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
					frame = new ViewComments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
