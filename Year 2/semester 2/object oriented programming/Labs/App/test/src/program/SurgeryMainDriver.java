package program;

import gui.MainWindow;

import java.util.*;

//**************replace filename for dateStampedFileName*************

/**
 * Surgery main driver Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 25/03/2014
 */
public class SurgeryMainDriver implements java.io.Serializable {
	
	static ArrayList<Doctor> doctorList = new ArrayList<Doctor>();	//arrayList of doctors
	static ArrayList<Patient> patientList = new ArrayList<Patient>();
	
	static boolean loggedIn;	//boolean whether a doctor is logged in
	static int doctorNum = 0;	//keeps track of what doctor is logged in
	static final String fileName = "doctorList.ser";	//filename where all data is stored
	
	public static void main(String[] args) {

		 		
		doctorList.add(new Doctor("Libor", "eee"));
		//doctorList.add(new Doctor("Peter", "eee"));
		//doctorList.add(new Doctor("Seamus", "eee"));
		//restore system from a file
		restoreSystemFromFile();
	
		//create main window
		//System.out.println(Doctor.patientList.get(0).getName());
		MainWindow mainWindowInstance = new MainWindow();
		
		
	}
	//methods
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
			doctorList.add(tempDoctorList.get(i));
			/*doctorList.get(i).print();
			for (int k = 0; k < doctorList.get(i).getPatientList().size();k++){
				doctorList.get(i).getPatientList().get(k).print();
			}*/
		}
		//must be done once to set the correct counter after restart of the program, otherwise the counter starts always from 0
		Doctor.instanceCounter = doctorList.size();
		
		ArrayList<Patient> tempPatientList = WriteRead.getRecoveredPatientList();
		patientList.clear();
		for (int i = 0; i < tempPatientList.size(); i++){
			patientList.add(tempPatientList.get(i));
		}
		Doctor.setPatientList(patientList);
    }
	
	public static void addPatient(String patientName, String patientAddress, String patientPhone, Date patientDOB ){
		doctorList.get(doctorNum).addPatient(patientName, patientAddress, patientPhone, patientDOB);
	}
	
	public static void listPatients(){
		String []  patients = new String [Doctor.getPatientList().size()];
		for (int i = 0; i < Doctor.getPatientList().size(); i++){
			patients[i] = Doctor.getPatientList().get(i).getName(); 
		}
		
		MainWindow.setPatientListModel(patients);
    }
	
    public static void updateDoctor(){
    	//need to fill up
    }
    
    public static void saveSystemToFile(){
    	String dateStampedFileName = "";
    	
    	//create Calendar instance
	    Calendar now = Calendar.getInstance();
		dateStampedFileName += "W" + now.get(Calendar.WEEK_OF_YEAR) + "Y" + now.get(Calendar.YEAR) + fileName;
		System.out.println(dateStampedFileName);
		
    	WriteRead writeread = new WriteRead();		//instance of created WriteRead class	
    	writeread.writeFile(fileName, doctorList, Doctor.getPatientList());
    }
	
    public static boolean login(int doctorId, String doctorPassword){	//method validates doctors ID and password
	    doctorNum = 0;
	    loggedIn = false;
	    
	    while(doctorNum< doctorList.size() && !loggedIn){
	    	if (doctorList.get(doctorNum).getId() == doctorId && doctorList.get(doctorNum).getPassword().equals(doctorPassword)){
	    		loggedIn = true;
	    		return true;
	    	}
	    	doctorNum++;
	    }
	    return false;
    }
    
    public static void logOut(){
    	loggedIn = false;
    }
    
    public static boolean getLoginStatus(){
    	return loggedIn;
    }
    
    public static Doctor getDoctor(){
    	return doctorList.get(doctorNum);
    }
    
    public static int getDoctorNum(){
    	return doctorNum;
    }
}
