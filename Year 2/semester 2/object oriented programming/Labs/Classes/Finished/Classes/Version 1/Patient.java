package test;
import java.util.Date;
import java.util.ArrayList;

public class Patient {

	private int patientId;
	private String patientName,patientAddress,patientPhone;
	private Date patientDOB;
	private ArrayList<PatientHistory> History = new ArrayList<PatientHistory>();


	public Patient(String patientName,String patientAddress, String patientPhone)//Constructor :
	{
		setPatientName(patientName);//Setting doctors name 
		setPatientAddress(patientAddress);//Setting Mark.
		setPatientPhone( patientPhone);//Setting Last Name.

	}


	public void setPatientName(String patientName)
	{
		this.patientName = patientName;// String flim name.
	}
	public void setPatientAddress(String patientAddress)
	{
		this.patientAddress = patientAddress;// String flim name.
	}	public void setPatientPhone(String patientPhone)
	{
		this.patientPhone= patientPhone;// String flim name.
	}
	
	
	public String PatientName()// Get Information stored from Private String firstname
	{
		return patientName;
	}
	public String  PatientAddress()// Get Information stored from Private String firstname
	{
		return  patientAddress;
	}
	public String  PatientPhone()// Get Information stored from Private String firstname
	{
		return  patientPhone;
	}
	private static void doctorsVisit() {

	}
}
