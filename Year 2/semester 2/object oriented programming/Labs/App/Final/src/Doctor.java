import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor implements java.io.Serializable //Start of Class
{


	private String doctorName, docPasswd;
	private int doctorId, surgeryId;
	public static  ArrayList<Patient> pList = new ArrayList<Patient>();
	private static int index = 1;
	public static int instanceCounter = 0;


	

	public Doctor(String doctorName, String docPasswd)// to make the auto asign ids
	{

	setId();
		setDoctorName(doctorName);
		setDoctorId(doctorId);
		setDocPasswd(docPasswd);
	}

	private void setId(){
		this.doctorId = instanceCounter;
		instanceCounter++;
	}
	public int getId() {   //Get doctors id and return it
		return doctorId;
	}
	public   void addPatient(String nameText, String addressText, String phoneText, String dobText) {
		Patient.instanceCounter = pList.size();
		this.pList.add(new Patient(nameText, addressText,phoneText , null));
	
		
	}

	public static void updatePatient() {
		System.out.print("sss");	

	}

	public static void findPatient(int selectedIndex) {

		int testo = surgey.id;
		System.out.println(" Number Selected : " + testo);
		System.out.println("Size : " + pList.size());
		System.out.println(pList.get(selectedIndex).getPName().toString()); 



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
		
		return "Doctor name: " + getDoctorName() + "\t\tDoctor id: " + getId() + "\t\tDoctor Password: " + getDocPasswd();

	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}



}