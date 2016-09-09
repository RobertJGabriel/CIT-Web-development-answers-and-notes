package javaProject;

import java.util.Date;
import java.util.ArrayList;

public class Patient {

	private int pId;
	private String pName,pAddress,pPhone;
	private Date pDOB;
	private ArrayList<PatientHistory> myHistory = new ArrayList<PatientHistory>();
	private static int index = 1;


	public Patient()
	{
		index =  index++;
	}

	public Patient(String pName,String pAddress, String pPhone,Date pDOB)//Constructor :
	{
		this.pId= index++;
		setPName(pName);//Setting doctors name 
		setPAddress(pAddress);//Setting Mark.
		setPPhone( pPhone);//Setting Last Name.
		setpDOB( pDOB);//Setting Last Name.
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
	

	private static void doctorsVisit() {

	}
}