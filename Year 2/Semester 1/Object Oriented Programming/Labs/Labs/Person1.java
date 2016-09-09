
/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person1
{
    private String name,gender;
    private int age;
    private double height;
    
    public Person1()
    {
    }
    public Person1(String name,int age,String gender,double height)
    {
       setName(name);
       setAge(age);
       setGender(gender);
       setHeight(height);
    }
    
     public void setName(String name)
   {
       this.name = name;
    }
     public void setGender(String gender)
   {
       this.gender = gender;
       
    }

   public void setAge(int age)
   {
       this.age = age;
    }
    
   public void setHeight(double height)
   {
       this.height = height;
   }
    
    //Sets 
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    
    
    
    
     //Methos
    public  String toString()
    {
        return "Person Details : \tName" + getName() + " \tAge" + getAge() + " \tHeight" + getHeight() + "\tGender" + getGender();         
    }
    
    
    
    public void print()
    {
        System.out.println(toString());
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
