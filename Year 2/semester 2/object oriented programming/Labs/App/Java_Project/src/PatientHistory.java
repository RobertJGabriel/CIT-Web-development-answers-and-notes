import java.util.Date;
@SuppressWarnings("serial")
public class PatientHistory implements Comparable<PatientHistory>, java.io.Serializable {
  
  
    @SuppressWarnings("unused")
	private int historyId,patientId,doctorId;
    private Date visitDate;
    public  int instanceCounter = 0;      //should be private
    private String description , medicine,procedure;
  
    public PatientHistory(int patientId ,int doctorId,String description,String medicine,String procedure)//Constructor :
    {
    	setDate();
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
        this.doctorId = DoctorId;// String flim name.
    }
    public void setDate(){
    	Date d = new Date();
    	this.visitDate = d;
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
    @SuppressWarnings("unused")
	private void setHistoryId(){
       this.historyId = instanceCounter;
       instanceCounter++;
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

    public Date getDate()// Get Information stored from Private String firstname
    {
        return visitDate;
    }
    
    
    public  String toString()//Tostring for displaying results.
    {
        return  "\n " + getDescription() + "\n" + getMedicine()+ "\n" +getProcedure() + "\n"+ getDate() +"\n-----------" ;         
    }
  
    public void print()// Printing Results from the toString Method.
    {
        System.out.println(toString());
    }
    public Date getVisitDate(){
  	  return visitDate;
     }
     
    @Override
	public int compareTo(PatientHistory p) {
		int result;
		Date visitDate = ((PatientHistory)p).getDate();
		result = getDate().compareTo(visitDate);
		return result;
	}
}