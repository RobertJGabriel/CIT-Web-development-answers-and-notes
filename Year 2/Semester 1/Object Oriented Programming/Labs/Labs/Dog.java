public class Dog {
	private String name;
	private int age;
	private Person2 owner;

	public Dog(String name){
		setName(name);//Sets Dogs Name
	}
	public Dog(String name, int age){
		setName(name);//Sets Name
		setAge(age);//Sets age
	}
	public void setName(String name){
		this.name = name;//Sets name
	}
	public void setAge(int age){
		this.age = age;//sets age
	}
	public void setOwner(Person2 owner){
		this.owner = owner;//sets owner localy
	}
	public String getName(){
		return name;//Gets name
	}
	public int getAge(){
		return age;//Gets Age
}
	public String getOwner(){
		return owner.toString();//Gets owner
	}
	public String toString(){
		return "Dog's name: " + getName() + " \nAge: " + getAge() ;//Prints stringe 
	}
	public void print(){
		System.out.print(toString());//Print the to String address
	}
}
