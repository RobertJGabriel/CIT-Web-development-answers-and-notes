
/**
 * Write a description of class TestDog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestDog
{
   public static void main(String[] args)
   {
       
      //object is called d.
       Dog2 d = new Dog2();
       d.setName ("Bron");
       d.setAge(3);
       d.setType("Batman");
       
       
        Dog2 e = new Dog2();
       e.setName ("Terrior");
       e.setAge(23);
       e.setType("Germen Sheapard");
       
       Dog2 f = new Dog2 ("Lucky ",23,"Morgnal");
       
       d.print();
       e.print();
       f.print();
       
      // System.out.printlin("\n\n\nName of First dog created is :" + d.name);
       
    }
}
