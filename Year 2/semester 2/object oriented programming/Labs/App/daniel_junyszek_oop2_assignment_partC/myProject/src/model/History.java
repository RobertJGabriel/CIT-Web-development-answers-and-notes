package model;
import java.io.Serializable;
import java.util.Date;

public class History implements Serializable {
	private static final long serialVersionUID = 260805857655705683L;
	private int histID;
	private String conditionName;
	private Date dateOccoured;
	private String medicationRecieved;
	public History(int histID, String conditionName, Date dateOccoured, String medicationRecieved)
	{
		this.histID = histID;
		this.conditionName = conditionName;
		this.dateOccoured = dateOccoured;
		
		if (medicationRecieved.equals(null))
			this.medicationRecieved = "none";
		else
			this.medicationRecieved = medicationRecieved;
	}
	public int getHistID()
	{
		return histID;
	}
	public String getConditionName()
	{
		return conditionName;
	}
	public Date getDateOccoured()
	{
		return dateOccoured;
	}
	public String getMedicationRecieved()
	{
		return medicationRecieved;
	}
	public String getHistory()
	{
		String myHist = "Condition ID: " + histID;
		myHist = myHist + "\nCondition Name: " + conditionName;
		myHist = myHist + "\nDate Occoured: " + dateOccoured.toString();
		myHist = myHist + "\nMedication Recieved: " + medicationRecieved;
		return myHist;
	}

}
