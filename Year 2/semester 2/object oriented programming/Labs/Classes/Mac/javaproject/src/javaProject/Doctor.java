package javaProject;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor //Start of Class
{static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.


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

	public static  void addPatient() {
		String name,address,phone = "sssss";
		Date DATE = null;

		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.print("Please Enters Doctor's Name : ");
			name = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.


		do{// Start of Do while Loop
			System.out.print("Please Enter "+ name + "'s Password : ");
		address = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.
		pList.add(new Patient(name,address,phone,DATE));// Adds doctors to class
	}

	private static void updatePatient() {
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
		return getDoctorName() +"Id = " +  getDoctorId() +getDocPasswd();
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}
}