package program;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;

import java.io.Serializable;

/**
 * PatientHistory Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 20/02/2014
 */

public class PatientHistory implements java.io.Serializable {
   // Calendar cal = Calendar.getInstance();
    //private variables
	private static int instanceCounter = 0;	
    private int historyId;
    private int patientId;
    private int doctorId;
    private String description;
    private String procedure;
    private String medicine;
    private Date visitDate;

    
    //Constructors
    public PatientHistory(int patientId, int doctorId, String description, String procedure, String medicine) 
    {
    	setHistoryId();
    	setPatientId(patientId);
    	setDoctorId(doctorId);
    	setDescription(description);
    	setMedicine(medicine);
    	setProcedure(procedure);
    	setVisitDate();

    }
    
    //setters
    public void setPatientId(int patientId){
    	this.patientId = patientId;
    }
    public void setDoctorId(int doctorId){
    	this.doctorId = doctorId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setMedicine(String medicine){
    	this.medicine = medicine;
    }
    public void setProcedure(String procedure){
    	this.procedure = procedure;
    }
   public void setVisitDate(){//make private
    	//this.visitDate = cal.get(Calendar.DAY_OF_MONTH);
	   this.visitDate = new Date();
	   System.out.print(visitDate);
    }
   private void setHistoryId(){
	   this.historyId = instanceCounter;
	   instanceCounter++;
   }
   public static void setInstanceCounter(int counter){
	   instanceCounter = counter;
   }
   /*public void setInstanceCounter(int instanceCounter){
   	PatientHistory.instanceCounter = instanceCounter;
   }*/
    
    //getters
    
   public int getPatientId(){
   	return patientId;
   }
   public int getDoctorId(){
	   return doctorId;
   }
   public String getDescription() {
	   return description;
   }
   public String getMedicine(){
	   return medicine;
   }
   public String getProcedure(){
	   return procedure;
   }
  public Date getVisitDate(){
	  return visitDate;
   }
   
    
    //Public methods
  
    /*
    public String toString() {   //Convert into string
        return "";
    }
    
    public void print() {    //Print Films and Codes from toString method
        System.out.println(toString());
    }
    */
}
