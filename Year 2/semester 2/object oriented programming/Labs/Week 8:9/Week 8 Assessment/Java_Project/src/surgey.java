
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;


public class surgey implements java.io.Serializable {

	private String surgeryAdd; 
	static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	private static String holder;
	private String password;
	DefaultListModel<String> teste;
	public static int id = -1,Patientid;
	static final String fileName = "doctorList.ser";	//filename where all data is stored

	public static void main(String[] args) { 

	//	docList.add(new Doctor("Rob","s"));
		restoreSystemFromFile();
		//create main window
		if (id == -1){
		surgey Surgey = new surgey();
		}
	}

	public  surgey() { 
		SurgeyDesigner Surgey = new SurgeyDesigner();
	}

	public static void addDoctor(String nameText, String passwordField1) { 
		docList.add(new Doctor(nameText,passwordField1));
	} 

	private static void updateDoctor() { 
// Needs to be done
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
				return true;
			}
		}
		System.out.println("Something is wrong");
		return false;
	} 


	public static void restoreSystemFromFile(){

		ArrayList<Doctor> tempDoctorList = new ArrayList<Doctor>();		//shallow copy of doctors
		WriteRead writeread = new WriteRead();		//instance of created WriteRead class					
		docList.clear();
    	tempDoctorList = writeread.readFile(fileName);
	
		//doctor addition to deep copy and print out
		for (int i = 0; i < tempDoctorList.size();i++){
			docList.add(tempDoctorList.get(i));
			docList.get(i).print();
			System.out.println( docList.get(i).getPatient().size());
			for (int k = 0; k < docList.get(i).getPatient().size();k++){
	
				docList.get(i).getPatient().get(k).print();
			}
		}
		//must be done once to set the correct counter after restart of the program, otherwise the counter starts always from 0
		Doctor.instanceCounter = docList.size();
	
	}
	public static void addPatient(String nameText, String addressText,String  phoneText ,String  dobText ){
		docList.get(id).addPatient(nameText, addressText, phoneText , dobText);
	}
		
	public static void listPatients(){
		String []  patients = new String [docList.get(id).getPatient().size()];

		for (int i = 0; i < docList.get(id).getPatient().size(); i++){
			patients[i] = docList.get(id).getPatient().get(i).getPName(); 
		}
		mainWindow.setPatientListModel(patients);
	}
}
