package surgey;
import java.util.Date;
import java.util.ArrayList;

public class Patient {

	private int pId;
	private String pName,pAddress,pPhone;
	private Date pDOB;
	private ArrayList<PatientHistory> myHistory = new ArrayList<PatientHistory>();


	public Patient(String pName,String pAddress, String pPhone,Date pDOB)//Constructor :
	{
		setPName(pName);//Setting doctors name 
		setPAddress(pAddress);//Setting Mark.
		setPPhone( pPhone);//Setting Last Name.
		setpDOB( pDOB);//Setting Last Name.
	}


	
	public void setPName(String pName)
	{
		this.pName = pName;
	}
	
	public void setPAddress(String pAddress)
	{
		this.pAddress = pAddress;
	}	
	
	public void setPPhone(String pPhone)
	{
		this.pPhone= pPhone;
	}
	public void setpDOB(Date pDOB)
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
