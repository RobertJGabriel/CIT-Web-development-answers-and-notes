
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

import program.Doctor;
import program.Patient;
import program.WriteRead;





public class surgey implements java.io.Serializable {

	private String surgeryAdd; 
	static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	static ArrayList<Patient> patientList = new ArrayList<Patient>();
	private static String holder;
	private String password;
	DefaultListModel<String> teste;
	public static int id = -1,Patientid;
	static final String fileName = "doctorList.ser";	//filename where all data is stored

	public static void main(String[] args) { 

		//restore system from a file
		restoreSystemFromFile();
		//docList.add(new Doctor("Rob","s"));
		//create main window

	surgey Surgey = new surgey();
		
	}

	public  surgey() { 
		SurgeyDesigner Surgey = new SurgeyDesigner();
	
	}


	private void addList(){
		for(Patient s : docList.get(id).getPatient())  
		{  
			teste.addElement(s.getPName() + "\t" +  s.getPPhone());
		}
	}
	public static void addDoctor(String nameText, String passwordField1) { 
		docList.add(new Doctor(nameText,passwordField1));
	} 

	private static void updateDoctor() { 

	} 

	public  static void saveSystemToFile() { 

		WriteRead writeread = new WriteRead();		//instance of created WriteRead class	
		writeread.writeFile(fileName, docList);

	} 

	public static boolean loginCheck(String name,String password) {
		int x =  docList.size();
		for (int i = 0 ; i <= x-1; i++){
			if (docList.get(i).getDoctorName().toString().equals(name) && (docList.get(i).getDocPasswd().toString().equals(password))){
				System.out.println("Login Ok");
				id = i;

				System.out.print("Login details " + id);
				return true;
			}
		}
		System.out.println("Something is wrong");
		return false;
	} 

	public static void listPatients(){

		String []  patients = new String [docList.get(id).getPatient().size()];

		for (int i = 0; i < docList.get(id).getPatient().size(); i++){
			patients[i] = docList.get(id).getPatient().get(i).getPName(); 
		}
		mainWindow.setPatientListModel(patients);
	}

	public static void restoreSystemFromFile(){
		ArrayList<Doctor> tempDoctorList = new ArrayList<Doctor>();		//shallow copy of doctors
		WriteRead writeread = new WriteRead();		//instance of created WriteRead class					
		
		String dateStampedFileName = "";
		//create Calendar instance
	    Calendar now = Calendar.getInstance();
		dateStampedFileName += "W" + now.get(Calendar.WEEK_OF_YEAR) + "Y" + now.get(Calendar.YEAR) + fileName;
		
    	tempDoctorList = writeread.readFile(fileName);			//replace fileName with dateStampedFileName to open current week backup
		
		//doctor addition to deep copy and print out
		for (int i = 0; i < tempDoctorList.size();i++){
			docList.add(tempDoctorList.get(i));
			/*doctorList.get(i).print();
			for (int k = 0; k < doctorList.get(i).getPatientList().size();k++){
				doctorList.get(i).getPatientList().get(k).print();
			}*/
		}
		//must be done once to set the correct counter after restart of the program, otherwise the counter starts always from 0
		Doctor.instanceCounter = docList.size();
		
		ArrayList<Patient> tempPatientList = WriteRead.getRecoveredPatientList();
		patientList.clear();
		for (int i = 0; i < tempPatientList.size(); i++){
			patientList.add(tempPatientList.get(i));
		}
		Doctor.setPatientList(patientList);
	}
	public static void addPatient(String nameText, String addressText,String  phoneText ,String  dobText ){
		System.out.println("Id number " + id);
		docList.get(id).addPatient(nameText, addressText, phoneText , dobText);
	}













}
