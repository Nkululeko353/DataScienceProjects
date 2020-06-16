package com.java.employeesystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewRegulations extends JFrame {
	static ViewRegulations frame;
	public ViewRegulations() {
		//Code to view data in JTable
		List<Compliance> list=ComplianceDao.view();
		int size=list.size();
		
		String data[][]=new String[size][5];
		int row=0;
		for(Compliance c:list){
			data[row][0]=String.valueOf(c.getCompliance_id());
			data[row][1]=c.getRlType();
			data[row][2]=c.getDetails();
			data[row][3]=String.valueOf(c.getCreateDate());
			data[row][4]=String.valueOf(c.getDepartment_id());
			row++;
		}
		String columnNames[]={"ComplianceID","RLType","Details","CreateDate","DepartmentID"};
		
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
					frame = new ViewRegulations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
