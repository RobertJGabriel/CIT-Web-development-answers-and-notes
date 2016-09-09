package program;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * Doctor Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 20/02/2014
 */

public class Doctor implements java.io.Serializable {

	//private variables
	//	private static final long serialVersionUID = 1L;
	public static int instanceCounter = 0;	//should be static
	private int doctorId;
	private String doctorName;
	private String doctorPassword;
	private ArrayList<Patient> patientList = new ArrayList<Patient>();

	//Constructors
	public Doctor(String doctorName, String password) 
	{
		setId();
		setName(doctorName);
		setPassword(password);
	}

	//setters
	private void setId(){
		this.doctorId = instanceCounter;
		instanceCounter++;
	}

	public void setName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void setPassword (String password){
		this.doctorPassword = password;
	}

	public void addPatient(String patientName, String patientAddress, String patientPhone, Date patientDOB) {
		Patient.instanceCounter = patientList.size();
		this.patientList.add(new Patient(patientName, patientAddress, patientPhone, patientDOB));
	}
	/* public void setInstanceCounter(int instanceCounter){
    	Doctor.instanceCounter = instanceCounter;
    }*/
	//getters

	public String getName() {   //Get doctors name and return it
		return doctorName;
	}

	public int getId() {   //Get doctors id and return it
		return doctorId;
	}

	public String getPassword(){
		return doctorPassword;
	}
	public ArrayList<Patient> getPatientList(){
		return patientList;
	}
	//Public methods

	//find patient by ID
	public Patient findPatient(int patientId){
		for (int i = 0; i < patientList.size();i++){
			if (patientList.get(i).getId() == patientId){
				return patientList.get(i);
			}
		}
		return null;	//check for null in main program
	}

	//find patient by Name
	public Patient findPatient(String patientName){
		for (int i = 0; i < patientList.size();i++){
			if (patientList.get(i).getName() == patientName){
				return patientList.get(i);
			}
		}
		return null;	//check for null in main program
	}

	public String toString() {   //Convert into string
		return "Doctor name: " + getName() + "\t\tDoctor id: " + getId() + "\t\tDoctor Password: " + getPassword();
	}

	public void print() {    //Print ..... from toString method
		System.out.println(toString());
	}

}
