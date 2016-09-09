/**
 * This is for lab Two .
 * @author Robert Gabriel 
 * @version 1.0
 */
public class FlimClass//Start of Class
{
	private String name,code;// Private Strings for storing Flim name and Code.

	public FlimClass(String name)//Constructor : All Details.
	{
		this.code = "";
		String p[] = name.split(" ");
		for (int i = 0 ;i<p.length;i++)
		{
			this.code += String.valueOf(p[i].charAt(0));
		}
		setName(name);//Setting Code
	}

	// Mutatours
	public void setName(String name)
	{
		this.name = name;// String flim name.
	}

	// Attribute
	public String getName()// Get Information stored from Private String firstname
	{
		return name;
	}
	public String getCode()// Get Information stored from Private String firstname
	{
		return code;
	}

	//Methods
	public  String toString()//Tostring for displaying results.
	{
		return "Films : " + getName() + " \n Code : " + getCode() + "\n" ;         
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}
}//End of class
