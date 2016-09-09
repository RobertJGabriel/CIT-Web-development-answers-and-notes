/*
 *  Author  : Robert Gabriel
 *  Date    : 25/9/2013
 *  About   : To count 99 bottles of beer.
 */
import java.util.Scanner;
public class Lab_One {

    public static void main (String[] args) {  
        int bottleNum = 0;  
        Scanner keyboard = new Scanner (System.in);

        do {
            System.out.print("Please enter the Amount of Beer, between 1-100:");
            bottleNum = keyboard.nextInt(); //Input for Bottles
        }while(!(bottleNum>0) && !(bottleNum < 100) ); // end of do while 
        loop(bottleNum);  // calls method
    } // end main method  

    public static void loop(int bottleNum){
        String bottles = "bottles";     //Bottles
        while (bottleNum > 0) {  
            System.out.println("\n"+bottleNum + " " + bottles + " of beer on the wall");  
            System.out.println(bottleNum + " " + bottles + " of beer.");  
            System.out.println("Take one down.");  
            System.out.println("Pass it around." +"\n");  
            bottleNum = bottleNum - 1;  

            if (bottleNum == 1) {  
                bottles = "bottle"; //Changes bottles to bottle
            }                          

            if (bottleNum > 0) {  
                System.out.println(bottleNum + " " + bottles + " of beer on the wall");  
            } else {  
                System.out.println("No more bottles of beer on the wall");  
            }  // end else  
        } // end while loop  
    }  // end of method
}  // end class 
