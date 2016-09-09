package model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Date;

public class Patient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5433999279983812763L;
	private int patientNum;
	private String patientName, patientAddress, patientPhone;
	private LinkedList<History> p_History= new LinkedList<History>();
	private LinkedList<Invoice> p_Invoice= new LinkedList<Invoice>();
	
	public Patient(int patientNum, String patientName, String patientAddress, String patientPhone)
	{
		this.patientNum=patientNum;
		this.patientName=patientName;
		this.patientAddress=patientAddress;
		this.patientPhone=patientPhone;
		System.out.println("Patient created:"+patientNum+" "+patientName+" "+patientAddress+" "+patientPhone);
	}
	public void addP_History(int histID, String conditionName, Date dateOccoured, String medicationRecieved)
	{
		p_History.add(new History(histID, conditionName, dateOccoured, medicationRecieved));
		histID++;
	}
	public boolean addP_Invoice(int invoiceID, double invoiceAmt, Date invoiceDate, boolean invoicePaid, LinkedList<Procedure> invoiceProcList)
	{
		if(p_Invoice.add(new Invoice(invoiceID, invoiceAmt, invoiceDate, invoiceProcList,  invoicePaid))==true){
			invoiceID++;
			return true;
		}
		else
			return false;
	}
	public int getPatientNum()
	{
		return patientNum;
	}
	public String getPatientName()
	{
		return patientName;
	}
	public String getPatientAddress()
	{
		return patientAddress;
	}
	public String getPatientPhone()
	{
		return patientPhone;
	}
	public LinkedList<History> getP_History()
	{
		return p_History;
	}
	public LinkedList<Invoice> getP_Invoice()
	{
		return p_Invoice;
	}
	public String getPatientString()
	{
		String myString = "";
		myString+=("Patient Number: " + patientNum);
		myString+=("\nPatient Name: " + patientName);
		myString+=("\nPatient Address: " + patientAddress);
		myString+=("\nPatient Phone: " + patientPhone);
		return myString;
		
	}
	public void setAddress(String ad)
	{
		this.patientAddress=ad;
	}
	public void setPhone(String ph)
	{
		this.patientPhone=ph;
	}
	public void setHistory(LinkedList<History> h)
	{
		this.p_History=h;
	}
	public void setInvoices(LinkedList<Invoice> i)
	{
		this.p_Invoice=i;
	}
	
	
}
