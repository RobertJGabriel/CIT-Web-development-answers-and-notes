

import java.util.Date;
import java.util.ArrayList;


public class Patient implements java.io.Serializable  {

	private int pId;
	public static int instanceCounter = 0;	//should be private
	private String pName,pAddress,pPhone;
	private Date pDOB;
	private static ArrayList<PatientHistory> myHistory = new ArrayList<PatientHistory>();
	private static int index = 1;




	public Patient(String pName,String pAddress, String pPhone,Date pDOB)//Constructor :
	{
	
	 	setId();
		setPName(pName);//Setting doctors name 
		setPAddress(pAddress);//Setting Mark.
		setPPhone( pPhone);//Setting Last Name.
		setpDOB( pDOB);//Setting Last Name.
	}
    private void setId(){
    	this.pId = instanceCounter;
    	instanceCounter++;
    }
	private void setPName(String pName)
	{
		this.pName = pName;
	}

	private  void setPAddress(String pAddress)
	{
		this.pAddress = pAddress;
	}   

	private void setPPhone(String pPhone)
	{
		this.pPhone= pPhone;
	}
	private void setpDOB(Date pDOB)
	{
		this.pDOB= pDOB;
	}


	public String getPName()// Get Information stored from Private String firstname
	{
		return pName;
	}
	public String  getPAddress()// Get Information stored from Private String firstname
	{
		return  pAddress;
	}
	public String  getPPhone()// Get Information stored from Private String firstname
	{
		return  pPhone;
	}

	public Date  getPDOB()// Get Information stored from Private String firstname
	{
		return  pDOB;
	}
	public String toString(){
		  return "\tPatient id: \t\t" + pId + "\n\tPatient name:\t" + getPName() + "\n\tPatient address:\t" + getPAddress() + "\n\tPatient phone: \t\t" + getPPhone() + "\n\tPatient DOB: \t\t" + getPDOB();

	}

	public void print(){
		System.out.println(  toString());//Print the to String address
	}

	public void doctorsVisit(int nameText, String patientMedText, String patientDesText, String patientProdText) {

		
		System.out.println("Patient History");
	int doctorID =	surgey.id;
	this.myHistory.add(new PatientHistory(doctorID,nameText ,patientMedText,patientDesText,patientProdText));		
	}
}