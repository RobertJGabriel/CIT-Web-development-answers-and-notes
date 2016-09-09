
/**
 * This is for lab one .
 * 
 * @author Robert Gabriel 
 * @version 1.0
 */
public class Dog2
{
   private String name,type;
   private int age;
   
   // consturcor
   //MAKES DOG WITH NO DEATILS
   public Dog2()
   {
   }
      //MAKES DOG ASKING FOR NAME
   public Dog2(String name)
   {
       setName(name);
   }
   
      //MAKES DOG ASKING FOR ALL DEATILS.
      public Dog2(String name,int age, String type)
   {
       setName(name);
       setAge(age);
       setType(type);
    }
   
   
  // Mutatours
   public void setName(String name)
   {
       this.name = name;
    }
    
     public void setType(String type)
   {
       this.type = type;
    }
    
     public void setAge(int age)
   {
      this.age = age; 
    }
    
    
    
    // Assessors
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getAge()
    {
        return age;
    }
    
    //Methos
    public  String toString()
    {
        return "Dog Details : \tName" + getName() + " \tAge" + getAge() + " \tType" + getType();         
    }
    
    
    
    public void print()
    {
        System.out.println(toString());
    }
    
    
    
    
}
