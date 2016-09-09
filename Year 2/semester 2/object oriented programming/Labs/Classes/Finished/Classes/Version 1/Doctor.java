package test;

public class Doctor //Start of Class
{
	private String doctorName,doctorPasswd;// Private Strings for storing ,FirstNam,Last name and Result.
	private int doctorId,surgeyId;// Private Int for storing Mark.
	
	public Doctor(String doctorName,int doctorId, String doctorPasswd)//Constructor :
	{
		setdoctorName(doctorName);//Setting doctors name 
		setdoctorId(doctorId);//Setting Mark.
		setdoctorPasswd(doctorPasswd);//Setting Last Name.
	}
	// Mutatours
	public void setdoctorName(String doctorName)
	{
		this.doctorName= doctorName;// String firstName of Students as First Name.
	}
	public String doctorName()// Get Information stored from Private String firstname
	{
		return doctorName;
	}
	public void setdoctorPasswd(String doctorPasswd)
	{
		this.doctorPasswd = doctorPasswd;// String firstName of Students as First Name.
	}
	public String doctorPasswd()// Get Information stored from Private String firstname
	{
		return doctorPasswd;
	}
	public void setdoctorId(int doctorId)
	{
		this.doctorId= doctorId;
	}
	public int doctorId()
	{
		return doctorId;
	}
	public String toString()//Tostring for displaying results.
	{
		return doctorName() ; 
	}
	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());}
}