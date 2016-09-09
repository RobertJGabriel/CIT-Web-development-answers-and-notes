
/**
 * Write a description of class TestPerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestPerson
{

   public static void main(String[] args)
   {
       
      //object is called d.
       Person1 d = new Person1();
       d.setName ("Bron");
       d.setAge(3);
       d.setHeight(252);
         d.setGender("male");
       
        Person1 e = new Person1();
      d.setName ("Bron");
       d.setAge(3);
       d.setHeight(252);
         d.setGender("male");
       
       Person1 f = new Person1 ("Batnam ",23,"caterman",932);
       
       d.print();
       e.print();
       f.print();
       
      // System.out.printlin("\n\n\nName of First dog created is :" + d.name);
       
    }

}
