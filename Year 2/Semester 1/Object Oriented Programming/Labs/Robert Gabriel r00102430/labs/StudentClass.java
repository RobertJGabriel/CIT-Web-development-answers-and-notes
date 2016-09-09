/**
 * This is for lab Two .
 * @author Robert Gabriel 
 * @version 1.0
 */
public class StudentClass //Start of Class
{
	private String  namesFirst,nameLast,results;// Private Strings for storing ,FirstName ,Last name and Result.
	private int mark;// Private Int for storing Mark.
	
	public StudentClass(String namesFirst,int mark, String nameLast)//Constructor : All Details.
	{
		setName(namesFirst);//Setting first Name.
		setMark(mark);//Setting Mark.
		setLastName(nameLast);//Setting Last Name.
		setResult(mark);//Setting Result.
	}

	// Mutatours
	public void setName(String namesFirst)
	{
		this.namesFirst = namesFirst;// String firstName of Students  as First Name.
	}

	public void  setLastName(String nameLast)
	{
		this.nameLast = nameLast;// String Lastname of Students Last Name.
	}

	public void setMark(int mark)
	{
		this.mark = mark; // int mark of mark  as mark. 
	}
	public String setResult(int mark)
	{
		int result = mark; 
		if (result >= 85 && result<= 100)// Start of If startment, Setting Results.
		{
			this.results = "Distinction";// String Result is set as Distinction

		}else if (result >= 65 && result<= 84) {

			this.results = "Merit";// String Result is set as Mert

		}else if(result >= 40 && result<= 64){

			this.results = "Pass";// String Result is set as Pass

		}else if (result<= 39) {

			this.results ="Fail";// String Result is set as Fall
		}//End of If Statement
		return "Unknow";	// String Result is set as Unknow
	}//End Method
	
	
	// Attribute
	public String getName()// Get Information stored from Private String firstname
	{
		return namesFirst;
	}

	public String  setLastName()// Get Information stored from Private String lastName
	{
		return nameLast;
	}

	public int getMark()// Get Information stored from Private Int mark
	{
		return mark;
	}
	public  String getResult()// Get Information stored from Private String Results
	{
		return results;
	}


	//Methods
	public  String toString()//Tostring for displaying results.
	{
	    return getName() +" "+ setLastName() +" received a " + getResult() + " for his mark of "+  getMark();         
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}
}//End of class
