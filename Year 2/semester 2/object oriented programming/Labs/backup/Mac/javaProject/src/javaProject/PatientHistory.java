package javaProject;
import java.util.Date;
public class PatientHistory {
 
 
    private int historyId,patientId,doctorId;
    private Date visitDate;
    private String description , medicine,procedure;
 
    public PatientHistory(int patientId ,int doctorId,String description,String medicine,String procedure)//Constructor :
    {
        setPatientId(patientId);//Setting doctors name 
        setDoctorId(doctorId);//Setting doctors name 
        setDescription(description);
        setMedicine(medicine);
        setProcedure(procedure);
 
    }
    public void setPatientId(int patientId)
    {
        this.patientId = patientId;// String flim name.
    }
 
    public void setDoctorId(int DoctorId)
    {
        this.doctorId = doctorId;// String flim name.
    }
 
    public void setDescription(String description)
    {
        this.description = description;// String flim name.
    }
    public void setMedicine(String medicine)
    {
        this.medicine = medicine;// String flim name.
    }
    public void setProcedure(String procedure)
    {
        this.procedure = procedure;// String flim name.
    }
 
     
     
     
     
     
    public int getPatientId()// Get Information stored from Private String firstname
    {
        return patientId ;
    }   
    public int getDoctorId()// Get Information stored from Private String firstname
    {
        return doctorId;
    }
    public String getDescription()// Get Information stored from Private String firstname
    {
        return description;
    }
    public String getMedicine()// Get Information stored from Private String firstname
    {
        return medicine;
    }
    public String getProcedure()// Get Information stored from Private String firstname
    {
        return procedure;
    }
    public  String toString()//Tostring for displaying results.
    {
        return "Films : " ;         
    }
 
    public void print()// Printing Results from the toString Method.
    {
        System.out.println(toString());
    }
     
     
     
}