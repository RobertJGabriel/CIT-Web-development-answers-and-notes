import java.util.ArrayList;

/**
 * This is for lab Two .
 * @author Robert Gabriel 
 * @version 1.0
 */

public class ActorClass //Start of Class
{
	private String  name,address;// Private Strings for storing ,Name and Address
	private int age;// Private Int for storing Age
	private ArrayList<FlimClass> flims = new ArrayList<FlimClass>();// Object for Flims array


	public ActorClass(String name,int age, String address)//Constructor : All Details.
	{
		setName(name);//Setting first Name.
		setAge(age);//Setting age.
		setAddress(address);//Setting Address.
	}

	// Mutatours
	public void setName(String name)
	{
		this.name = name;// String Name setting name of Actor
	}

	public void  setAddress(String address)
	{
		this.address = address;// String address for address.
	}

	public void setAge(int age)
	{
		this.age = age; // int age of age  as age. 
	}

	public void setFilm(ArrayList<FlimClass> flims){
		for(int i = 0; i < flims.size(); i++){
			this.flims.add(new FlimClass(flims.get(i).getName()));
		}
	}

	// Attribute
	public String getName()// Get Information stored from Private String firstname
	{
		return name;
	}

	public String  getAddress()// Get Information stored from Private String lastName
	{
		return address;
	}

	public int getAge()// Get Information stored from Private Int age
	{
		return age;
	}
	public ArrayList<FlimClass>  getFlim()// Get Information stored from Private String lastName
	{
		return flims;
	}

	//Methods
	public  String toString()//Tostring for displaying results.
	{
		return "\n" +  getName() +" who’s "+ getAge() +" and and lives in " +  getAddress() + ""   ;         
	}

	public void print()// Printing Results from the toString Method.
	{
		System.out.println(toString());
	}
}//End of class
