package program;

import gui.MainWindow;

import java.util.*;


/**
 * Surgery main driver Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 25/03/2014
 */
public class SurgeryMainDriver implements java.io.Serializable {
	
	static ArrayList<Doctor> doctorList = new ArrayList<Doctor>();	//arrayList of doctors
	static boolean loggedIn;	//boolean whether a doctor is logged in
	static int doctorNum = 0;	//keeps track of what doctor is logged in
	static final String fileName = "doctorList.ser";	//filename where all data is stored
	
	public static void main(String[] args) {

		
		
		
		//restore system from a file
		restoreSystemFromFile();
		
		//create main window
		MainWindow mainWindowInstance = new MainWindow();
		
		
	}
	//methods
	public static void restoreSystemFromFile(){
		ArrayList<Doctor> tempDoctorList = new ArrayList<Doctor>();		//shallow copy of doctors
		WriteRead writeread = new WriteRead();		//instance of created WriteRead class					
		
    	tempDoctorList = writeread.readFile(fileName);
		
		//doctor addition to deep copy and print out
		for (int i = 0; i < tempDoctorList.size();i++){
			doctorList.add(tempDoctorList.get(i));
			doctorList.get(i).print();
			for (int k = 0; k < doctorList.get(i).getPatientList().size();k++){
				doctorList.get(i).getPatientList().get(k).print();
			}
		}
		//must be done once to set the correct counter after restart of the program, otherwise the counter starts always from 0
		Doctor.instanceCounter = doctorList.size();
    }
	
	public static void addPatient(String patientName, String patientAddress, String patientPhone, Date patientDOB ){
		doctorList.get(doctorNum).addPatient(patientName, patientAddress, patientPhone, patientDOB);
	}
	
	public static void listPatients(){
		String []  patients = new String [doctorList.get(doctorNum).getPatientList().size()];
		
		for (int i = 0; i < doctorList.get(doctorNum).getPatientList().size(); i++){
			patients[i] = doctorList.get(doctorNum).getPatientList().get(i).getName(); 
		}
		MainWindow.setPatientListModel(patients);
    }
	
    public static void updateDoctor(){
    	//need to fill up
    }
    
    public static void saveSystemToFile(){
    	WriteRead writeread = new WriteRead();		//instance of created WriteRead class	
    	writeread.writeFile(fileName, doctorList);
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
}
