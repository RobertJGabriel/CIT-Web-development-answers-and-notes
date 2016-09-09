package model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Date;

public class Invoice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730826080320057664L;
	private int invoiceNum;
	private double invoiceAmt;
	private Date invoiceDate;
	private boolean invoicePaid;
	private LinkedList<Procedure> invoiceProcList;

	public Invoice(int invoiceNum, double invoiceAmt, Date invoiceDate, LinkedList<Procedure> invoiceProcList, boolean invoicePaid)
	{
		this.invoiceNum = invoiceNum;
		this.invoiceAmt = invoiceAmt;
		this.invoiceDate = invoiceDate;
		this.invoicePaid = invoicePaid;
		this.invoiceProcList = invoiceProcList;
		
	}
	public void addProcedure(Procedure addingProcedure)
	{
		invoiceProcList.addLast(addingProcedure);
	}
	public int getInvoiceNum()
	{
		return invoiceNum;
	}
	public double getInvoiceAmt()
	{
		return invoiceAmt;
	}
	public Date getInvoiceDate()
	{
		return invoiceDate;
	}
	public boolean getInvoicePaid()
	{
		return invoicePaid;
	}
	public LinkedList<Procedure> getInvoiceProcList()
	{
		return invoiceProcList;
	}
	public String getInvoice()
	{
		String myString = ("Invoice Number: " + invoiceNum);
		myString = myString + ("\nInvoice Amount: €" + invoiceAmt);
		myString = myString + ("\nInvoice Date : " + invoiceDate.toString());
		myString = myString + ("\nInvoice Paid: " + checkPay());
		for(int i =0; i<invoiceProcList.size();i++)
			myString = myString + "\n\t" + invoiceProcList.get(i).getProc();
		
		return myString;
		
	}
	private String checkPay()
	{
		if(invoicePaid == true)
			return "Yes";
		else
			return "No";
	}
	public String getProceduresString(){
		String x = "";
		for(int i =0; i<invoiceProcList.size();i++)
			x += "\n" + invoiceProcList.get(i).getProc();
		
		return x;
	}
	public void setProcedures(LinkedList<Procedure> proc)
	{
		this.invoiceProcList=proc;
	}
}
