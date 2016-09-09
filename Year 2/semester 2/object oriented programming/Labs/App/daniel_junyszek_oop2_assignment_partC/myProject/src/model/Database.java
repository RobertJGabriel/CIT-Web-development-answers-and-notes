package model;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Database implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359324331219014444L;
	public LinkedList<Procedure> procList = new LinkedList<Procedure>();
	public LinkedList<Patient> patientList = new LinkedList<Patient>();
	private static int patientNum=0;
	private static int procNum=0;
	private static int histId=0;
	private static int invoiceId=0;
	private static int invProcedureId=0;
	public void addProcedure(String procName, double procCost)
	{
		procList.add(new Procedure(procNum,procName,procCost));
		procNum++;
	}
	
	public LinkedList<Procedure> getProcedureList()
	{
		
		return procList;
	}
	public Procedure getProcByName(String name)
	{
		for(int i = 0; i<11;i++)
		{
			if(procList.get(i).getProcName().equals(name))
				return procList.get(i);
		}
		return null;
	}
	public Procedure getProcByNum(int num)
	{
		for(int i = 0; i<11;i++)
		{
			if(procList.get(i).getProcNum() == num)
				return procList.get(i);			
		}
		return null;
	}
	
	public void addPatient(String patientName, String patientAddress, String patientPhone)
	{
		System.out.println("Database adding to patientList patient:"+patientName+patientAddress+patientPhone);
		Patient newPatient = new Patient(patientNum, patientName, patientAddress, patientPhone);
		patientList.add(patientNum, newPatient);
		
		patientNum++;
	}
	public void setPatientIndex(int i)
	{
		patientNum=i;
	}
	public LinkedList<Patient> getPatientList()
	{
		return patientList;
	}
	public int findPatient(String name, int id)
	{
		for(int i = 0 ; i < patientList.size() ; i++ )
		{
			if(name.equals(patientList.get(i).getPatientName()))
			{
				return i;
			}
			else if(id == patientList.get(i).getPatientNum())
			{
				return i;
			}
			
		}
		return -1;
	}
	public void addPatientHistory(int i, String conditionName, Date dateOccoured, String medicationRecieved)
	{
		patientList.get(i).addP_History(histId, conditionName, dateOccoured, medicationRecieved);
		histId++;
	}
	public boolean addPatientInvoice(int i,Invoice invoice){
		invoiceId++;
		return patientList.get(i).addP_Invoice(invoiceId-1, invoice.getInvoiceAmt(), invoice.getInvoiceDate(), invoice.getInvoicePaid(), invoice.getInvoiceProcList());
			
	}
	public int findProcedure(String name)
	{
		for(int i = 0; i<procList.size();i++)
		{
			if(procList.get(i).getProcName().equals(name))
				return i;
		}
		return -1;
	}
	public String getLastPatientString()
	{
		return patientList.get(0).getPatientString();
	}
	public LinkedList<Invoice> getPatientInvoices(int i)
	{
		return patientList.get(i).getP_Invoice();
	}
	public void setProceduresList(LinkedList<Procedure> proc)
	{
		this.procList=proc;
	}
	public void setIndexes(int[] index)
	{
		Database.patientNum=index[0];
		Database.procNum=index[1];
		Database.histId=index[2];
		Database.invoiceId=index[3];
		Database.invProcedureId=index[4];
	}
	public int[] getIndexes()
	{
		int[] ids= new int[5];
		ids[0]=patientNum;
		ids[1]=procNum;
		ids[2]=histId;
		ids[3]=invoiceId;
		ids[4]=invProcedureId;
		
		return ids;
	}
}
