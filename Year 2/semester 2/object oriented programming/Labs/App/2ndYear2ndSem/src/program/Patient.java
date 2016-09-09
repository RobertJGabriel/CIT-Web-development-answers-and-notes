package program;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


/**
 * Patient Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 20/02/2014
 */

public class Patient implements java.io.Serializable {
    
    //private variables
	public static int instanceCounter = 0;	//should be private
    private int patientId;
    private String patientName;
    private String patientAddress;
    private String patientPhone;
    private Date patientDOB;
    private ArrayList <PatientHistory>patientHistory;

    
    //Constructors
    public Patient(String patientName, String patientAddress, 
    		String patientPhone, Date patientDOB) 
    {
    	setId();
    	setName(patientName);
    	setAddress(patientAddress);
    	setPhone(patientPhone);
    	setDate(patientDOB);
    }
    
    //setters
    private void setId(){
    	this.patientId = instanceCounter;
    	instanceCounter++;
    }
    public void setName(String patientName) {
        this.patientName = patientName;
    }
    public void setAddress(String patientAddress){
    	this.patientAddress = patientAddress;
    }
    public void setPhone(String patientPhone){
    	this.patientPhone = patientPhone;
    }
    public void setDate(Date patientDOB){
    	this.patientDOB = patientDOB;
    }
    public void setPatientHistory(int patientId, int doctorId, String description, String medicine, String procedure){
    	PatientHistory.instanceCounter = patientHistory.size();
    	this.patientHistory.add(new PatientHistory(patientId, doctorId, description, medicine, procedure));
    }
    /*public void setInstanceCounter(int instanceCounter){
    	Patient.instanceCounter = instanceCounter;
    }*/
    //getters
    
    public int getId() {   //Get patients id and return it
        return patientId;
    }
    public String getName() {   //Get patients name and return it
        return patientName;
    }
    public String getAddress(){	//Get patients address and return it
    	return patientAddress;
    }
    public String getPhone(){	//Get patients phone and return it
    	return patientPhone;
    }
    public Date getDOB(){		//Get patients DOB and return it
    	return patientDOB;
    }
    public ArrayList<PatientHistory> getPatientHistory(){
    	return patientHistory;
    }
   
    //Public methods
  
    
    public String toString() {   //Convert into string
        return "\tPatient id: \t\t" + getId()+ "\n\tPatient name:\t" + getName() + "\n\tPatient address:\t" + getAddress() + "\n\tPatient phone: \t\t" + getPhone() + "\n\tPatient DOB: \t\t" + getDOB();
    }
    
    public void print() {    //Print from toString method
        System.out.println(toString());
    }
    
}
