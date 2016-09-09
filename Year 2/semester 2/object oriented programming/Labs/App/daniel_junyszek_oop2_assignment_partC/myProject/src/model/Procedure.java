package model;

import java.io.Serializable;

public class Procedure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -595339763173139387L;
	private int procNum;
	private String procName;
	private double procCost;
	
	public Procedure(int procNum, String procName, double procCost){
		
		this.procName = procName;
		this.procCost = procCost;
		this.procNum = procNum;
	}
	public int getProcNum()
	{
		return procNum;
	}
	public String getProcName()
	{
		return procName;
	}
	public double getProcCost()
	{
		return procCost;
	}
	public String getProc()
	{
		String myString = (procNum + ". ");
		myString = myString + (procName + ": ");
		myString = myString + ("€" + procCost);
		return myString;
	}

}
