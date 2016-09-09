package javaProject;



import java.util.ArrayList;

public class Doctor //Start of Class
{

	private String doctorName, docPasswd;
	private int doctorId, surgeryId;
	private ArrayList<Patient> pList = new ArrayList<Patient>();
	private static int index = 1;


	public Doctor()
	{
		doctorId =  index++;
	}

	public Doctor(String doctorName, String docPasswd)// to make the auto asign ids
	{
		this.doctorId= index++;
		setDoctorName(doctorName);
		setDoctorId(doctorId);
		setDocPasswd(docPasswd);
	}

	private static void addPatient() {
	}

	private static void updatePatient() {
	}

	private static void findPatient() {
	}

	private static void patientReport() {
	}

	public void setSurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}

	public int getSurgeryId() {
		return surgeryId;
	}

	public void setDocPasswd(String docPasswd) {
		this.docPasswd = docPasswd;
	}

	public String getDocPasswd() {
		return docPasswd;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public String toString()//Tostring for displaying results.
	{
		return getDoctorName() +"Id = " +  getDoctorId() +getDocPasswd();
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}
}