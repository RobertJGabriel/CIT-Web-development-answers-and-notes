import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class Doctor implements java.io.Serializable  //Start of Class
{

	public  ArrayList<Patient> pList = new ArrayList<Patient>();
	public static int instanceCounter = 0;
	private String doctorName, docPasswd;
	private int doctorId, surgeryId;

	public Doctor(String doctorName, String docPasswd)
	{

		//Sets the Doctor Information	
		setId();						//Setting Doctor Id
		setDoctorName(doctorName);		//Setting Doctor Name
		setDocPasswd(docPasswd);		//Setting Doctor Password
	}
	
	
	public Doctor(int id , String doctorName, String docPasswd)
	{

		//Sets the Doctor Information	
		setId(id);						//Setting Doctor Id
		setDoctorName(doctorName);		//Setting Doctor Name
		setDocPasswd(docPasswd);		//Setting Doctor Password
	}

	public Doctor() {}

	//Sets Doctor Id Information
	private void setId(){
		this.doctorId = instanceCounter;
		instanceCounter++;
	}
	
	//Sets Patient Id Information from database
	private  void setId(int i){
		this.doctorId = i;
	}
	
	private  int getId() {   //Get doctors id and return it
		
		return doctorId;
	}
	
	public  void addPatient(String nameText, String addressText, String phoneText, java.util.Date patientDOB) {
		
		Patient.instanceCounter = pList.size();
		this.pList.add(new Patient(nameText, addressText,phoneText , patientDOB));
	}

	public static void updatePatient() {
//In main Window
	}

	//Search Patient
	public static void findPatient(int selectedIndex) {
		
		//In main Window
	}

	private static void patientReport() {	//main window
		}
	

	
	//public so you can be able to update the patients
	
	private  void setSurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}

	public int getSurgeryId() {
		return surgeryId;
	}

	private  void setDocPasswd(String docPasswd) {
		this.docPasswd = docPasswd;
	}

	public String getDocPasswd() {
		return docPasswd;
	}

	private void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	private void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getDoctorId() {
		return doctorId;
	}
	
	
	public ArrayList<Patient> getPatient() {
		
		return pList;
	}
    //find patient by Name
    public Patient findPatient(String patientName){
    	for (int i = 0; i < pList.size();i++){
    		if (pList.get(i).getPName().equals(patientName)){
    			return pList.get(i);
    		}
    	}   
    	return null;	//check for null in main program
    }
    public static ArrayList sortPatients(ArrayList allPatients){
    	List<Patient> e = new ArrayList<Patient>(allPatients);
    	Collections.sort(e);
    	allPatients = (ArrayList<Patient>)e;
    	return allPatients;
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