
import java.util.List;


import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Patient implements java.io.Serializable ,Comparable <Patient>{

	public  ArrayList<PatientHistory> myHistory = new ArrayList<PatientHistory>();
	public static int instanceCounter = 0;
	private String pName,pAddress,pPhone;
	private Date pDOB;
	private int pId;

	public Patient(String pName,String pAddress, String pPhone,Date pDOB)//Constructor :
	{
		super();
		//Sets the Patient Information	
		setId();				//Setting Patient Id
		setPName(pName);		//Setting Patient Name 
		setPAddress(pAddress);	//Setting Patient Address
		setPPhone( pPhone);		//Setting Patient Phone
		setpDOB( pDOB);			//Setting Patient DOB

		//Connects to the Database and and adds Patient 
		DBConnect connect = new DBConnect(); 
		connect.insertPatientData(pId,pName, pAddress, pPhone,pDOB);

	}


	// Sets the Patient from Patient resetting
	public Patient(int id ,String pName,String pAddress, String pPhone,Date pDOB)//Constructor :
	{

		setId(id);				//Setting Patient Id 
		setPName(pName);		//Setting Patient Name 
		setPAddress(pAddress);	//Setting Patient Address
		setPPhone( pPhone);		//Setting Patient Phone
		setpDOB( pDOB);			//Setting Patient DOB

	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}


	//Sets Patient Id Information
	private void setId(int id){
		this.pId = id;
	}

	//Sets Patient Id Information
	private void setId(){
		this.pId = instanceCounter;
		instanceCounter++;
	}
	//Sets Patient Name
	public void setPName(String pName)
	{
		this.pName = pName;
	}

	//Sets Patient Address
	public  void setPAddress(String pAddress)
	{
		this.pAddress = pAddress;
	}   

	//Sets Patient Phone
	public void setPPhone(String pPhone)
	{
		this.pPhone= pPhone;
	}

	//Sets Patient DOB
	public void setpDOB(Date pDOB)
	{
		this.pDOB= pDOB;
	}


	public String getPName()// Get Information stored from Private String Name
	{
		return pName;
	}
	public String  getPAddress()// Get Information stored from Private String Address
	{
		return  pAddress;
	}
	public String  getPPhone()// Get Information stored from Private String Phone
	{
		return  pPhone;
	}
	public int getId(){
		return pId;

	}
	public Date  getPDOB()// Get Information stored from Private String DOB
	{
		return  pDOB;
	}


	  public ArrayList<PatientHistory> getPatientHistory(){
	    	return myHistory;
	    }       


	  
	  
	  
	//Places the information to sTRING
	public String toString(){
		return "Patient id: \t\n" + pId + "\n\tPatient name:\t" + getPName() + "\n\tPatient address:\t" + getPAddress() + "\n\tPatient phone: \t\t" + getPPhone() + "\n\tPatient DOB: \t\t" + getPDOB();
	}
	 
	//Prints it to the string
	public void print(){
		System.out.println(toString());//Print the to String address
	}

	//Sets the Patient History
	public  void doctorsVisit(int nameText, String patientMedText, String patientDesText, String patientProdText) {
		int doctorID =	surgey.id;		//Gets the Doctor Id for currently Logged in
		myHistory.add(new PatientHistory(doctorID,nameText ,patientMedText,patientDesText,patientProdText));		
	}


	
	@Override
	public int compareTo(Patient p) {
		int result;
		String name = ((Patient)p).getPName();
		result = getPName().compareTo(name);
		return result;
	}

	public ArrayList sortPatients(ArrayList allHistory){
    	List<PatientHistory> e = new ArrayList<PatientHistory>(allHistory);
    	Collections.sort(e);
    	allHistory = (ArrayList<PatientHistory>)e;
    	return allHistory;
    }







}