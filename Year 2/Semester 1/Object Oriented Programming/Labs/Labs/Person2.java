import java.util.ArrayList;

public class Person2 {
	private String name;
	private String address;
	private int age;
	private ArrayList<Dog> pets = new ArrayList<Dog>();

	
	public Person2(String name, String address, int age){
		setName(name);//Sets Owners Name
		setAddress(address);//Sets Owners Address
		setAge(age);// Sets Owners Age 
	}
	public void setName(String name){
		this.name = name;//Sets name as name
	}
	public void setAddress(String address){
		this.address = address;//Sets age as Age
	}
	public void setAge(int age){
		this.age = age;//Sets age as age
	}
	public void setDog(ArrayList<Dog> myDog){
		if(!pets.isEmpty()){
			this.pets.clear();//Clear the pet array list
		}
		for(int i=0; i<myDog.size(); i++){
			this.pets.add(new Dog(myDog.get(i).getName(), myDog.get(i).getAge()));//Add the dogs to the match
			pets.get(i).setOwner(this);//Sets the owner to the dogs
		}
	}
	public String getName(){
		return name;//Gets the name
	}
	public String getAddress(){
		return address;//Gets the address
	}
	public int getAge(){
		return age;//Gets the age
	}
	public ArrayList<Dog> getPets(){
		return pets; //returns the pets
	}
	public String toString(){
		return "\nOwner's name: " + getName() + " \nAge: " + getAge() + "\nAddress: " + getAddress();
	}

	public void print(){
		System.out.print(  toString());//Print the to String address
	}
}
