package javaProject;


import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends JFrame implements ActionListener //Start of Class
{


	private String doctorName, docPasswd;
	private int doctorId, surgeryId;
	private static  ArrayList<Patient> pList = new ArrayList<Patient>();
	private static int index = 1;


	public Doctor()
	{
		doctorId =  index++;
	}

	public Doctor(String doctorName, String docPasswd)// to make the auto asign ids
	{
	
		this.doctorId= index++;
		setDoctorName(doctorName);
		setDoctorId(doctorId);
		setDocPasswd(docPasswd);
	}

	public static  void addPatient(Patient pLists) {
		JFrame frame;
		//Conpunents 
		final JTextField name ;
		final JTextField address ;
		final JTextField phone ;
		final JTextField DOB ;

		JPanel middle;
		JPanel center;

		final	JLabel title;
		JLabel labelAddress;
		JLabel labelPhone;
		JLabel labelDob;

		JButton button;
		JLabel labelName;

		//Label

		labelName = new JLabel();
		labelAddress = new JLabel();
		labelPhone = new JLabel();
		labelDob = new JLabel();

		labelName.setText("Patients Name");
		labelAddress.setText("Patient Address");
		labelPhone.setText("Patients Phone");
		labelDob.setText("Patient Date of Birth");
		//Frame 
		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		//Center
		center  = new JPanel();
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 

		//Input Field
		name =  new JTextField(10);
		name.setText("Patients Name");
		address =  new JTextField(10);
		address.setText("Patients Address");
		phone =  new JTextField(10);
		phone.setText("Patients Phone");
		DOB =  new JTextField(10);
		DOB.setText("Patients Birth");

		//Button
		button =  new JButton();
		button.setText("Add Patient");

		title = new JLabel();
		title.setText("Add Patient");

		//Middle Container
		middle  = new JPanel();
		middle.setPreferredSize(new Dimension (200, 400) );
		middle.add(title , BorderLayout.NORTH);
		middle.add(labelName,BorderLayout.NORTH);
		middle.add(name , BorderLayout.NORTH);
		middle.add(labelAddress,BorderLayout.NORTH);
		middle.add(address, BorderLayout.NORTH);
		middle.add(labelPhone,BorderLayout.NORTH);
		middle.add(phone, BorderLayout.NORTH);
		middle.add(labelDob , BorderLayout.NORTH);
		middle.add(DOB, BorderLayout.NORTH);
		middle.add(button , BorderLayout.NORTH);

		center.add(middle);	

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = name.getText();
				String addressText = address.getText();
				String phoneText = phone.getText();
				String dobText = DOB.getText();
				title.setText("Patient was added ");
				pList.add(new Patient(nameText, addressText,phoneText , null));
				System.out.print("Patient Added");
			}
		}
				);		
		frame.add(center);
		frame.setSize(300, 500);
		frame.setTitle("Add Patient");
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public static void updatePatient() {
		
		JFrame updateFrame;
		//Conpunents 
		final JTextField name ;
		final JTextField address ;
		final JTextField phone ;
		final JTextField DOB ;

		JPanel middle;
		JPanel center;

		final	JLabel title;
		JLabel labelAddress;
		JLabel labelPhone;
		JLabel labelDob;

		JButton button;
		JLabel labelName;

		//Label

		labelName = new JLabel();
		labelAddress = new JLabel();
		labelPhone = new JLabel();
		labelDob = new JLabel();

		labelName.setText("Patients Name");
		labelAddress.setText("Patient Address");
		labelPhone.setText("Patients Phone");
		labelDob.setText("Patient Date of Birth");
		//Frame 
		updateFrame = new JFrame();
		updateFrame.setLayout(new BorderLayout());

		//Center
		center  = new JPanel();
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 

		//Input Field
		name =  new JTextField(10);
		name.setText("Patients Name");
		address =  new JTextField(10);
		address.setText("Patients Address");
		phone =  new JTextField(10);
		phone.setText("Patients Phone");
		DOB =  new JTextField(10);
		DOB.setText("Patients Birth");

		//Button
		button =  new JButton();
		button.setText("Add Patient");

		title = new JLabel();
		title.setText("Update Patient");

		//Middle Container
		middle  = new JPanel();
		middle.setPreferredSize(new Dimension (200, 400) );
		middle.add(title , BorderLayout.NORTH);
		middle.add(labelName,BorderLayout.NORTH);
		middle.add(name , BorderLayout.NORTH);
		middle.add(labelAddress,BorderLayout.NORTH);
		middle.add(address, BorderLayout.NORTH);
		middle.add(labelPhone,BorderLayout.NORTH);
		middle.add(phone, BorderLayout.NORTH);
		middle.add(labelDob , BorderLayout.NORTH);
		middle.add(DOB, BorderLayout.NORTH);
		middle.add(button , BorderLayout.NORTH);

		center.add(middle);	

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = name.getText();
				String addressText = address.getText();
				String phoneText = phone.getText();
				String dobText = DOB.getText();
				title.setText("Patient was Updated ");
				System.out.print("Patient Update");

			}
		}
				);		
		updateFrame.add(center);
		updateFrame.setSize(300, 400);
		updateFrame.setTitle("Update Patient");
		updateFrame.setVisible(true);	
		updateFrame.setLocationRelativeTo(null);  
		updateFrame.setResizable(false);

		
	}

	private static void findPatient() {
		
		
		
		
	}

	private static void patientReport() {
		
		
		
		
		
	}

	public void setSurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}

	public int getSurgeryId() {
		return surgeryId;
	}

	public void setDocPasswd(String docPasswd) {
		this.docPasswd = docPasswd;
	}

	public String getDocPasswd() {
		return docPasswd;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getDoctorId() {
		return doctorId;
	}
	public ArrayList<Patient> getPatient() {
		return pList;
	}
	public String toString()//Tostring for displaying results.
	{
		return getDoctorName() ;
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}