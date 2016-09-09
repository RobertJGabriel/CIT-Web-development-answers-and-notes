
/**
 * Write a description of class Lab_Four here.
 * 
 * @author Robert Gabriel
 * @version 1.1
 * Name : Lab 4 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Lab_Four
{

    static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.
    static ArrayList<Person2> person = new ArrayList<Person2>();// Objects of Person Owner
    static ArrayList<Dog> dog = new ArrayList<Dog>();// Objects of Dogs
    public static void main(String[] args) {

        int menu;
        boolean exit=false;
        while(exit!=true){// Start of While Loop for Menu
            System.out.print("\nPlease Pick a Menu Option  \n[1] Add a Owner and Dog \n[2] Display All Owners and Dogs\n[3] Exit \n:");
            menu = keyboard.nextInt();
           
            switch (menu) { //Start of Switch Statement
            case 1: 
                addOwner();
                break;
            case 2: 
                display() ;
                break;
            case 3:
                System.out.println("Exiting Progamme..... Good Bye Dave :'(");
                exit = true;// Turns Boolean Exit to true, Ends loop.
                break;
            default: System.out.print("Not Option allowed");
            break;
            }// End of Switch Statement
        }
    }

    private static void addOwner() {// Add Actor and Movies to Database
        String name,address;
        String dogName = "";
        int  age,dogAge =0,numberOfDog=0,limit=20;

        do{// Sta   rt of Do while Loop 
            keyboard.nextLine();
            System.out.print("Please Enters Owner's Name : ");
            name = keyboard.nextLine();
        }while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.


        do{// Start of Do while Loop
            System.out.print("Please Enter "+ name + "'s address : ");
            address = keyboard.nextLine();
        }while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.


        do {//Start of Do Loop
            System.out.print("Please Enter "+ name +"'s Age : ");
            while (!keyboard.hasNextInt()) {// Check if has int.
                System.out.print("Please enter a "+ name +" Age : ");
                keyboard.next(); // this is important!
            }
            age= keyboard.nextInt();//Stores Input age
        } while (age <= 0); //End of do while loop


        person.add(new Person2(name,address,age));// Creates Person
        int x = person.size();//Gets actors number


        do {//Start of Do Loop

            System.out.print("Amount of Dogs to add : ");
            while (!keyboard.hasNextInt()) {// Check if has int.
                System.out.print("Amount of Dogs to Add : ");
                keyboard.next(); // this is important!
            }
            numberOfDog = keyboard.nextInt();//Stores Amount of Dogs
            keyboard.nextLine();
        } while (numberOfDog<=0 || numberOfDog>limit); //End of do while loop


        for (int i = 0 ; i <=   numberOfDog-1 ; i++){// Dogs Name, Start of For Loop
            System.out.print("Enter Dog's name : ");
            dogName = keyboard.nextLine();
            do {//Start of Do Loop
                System.out.print("Please Enter "+ dogName +"'s Age : ");
                while (!keyboard.hasNextInt()) {// Check if has int.
                    System.out.print("Please enter a "+ dogAge +" Age : ");
                    keyboard.next(); // this is important!
                }
                dogAge= keyboard.nextInt();//Stores Dogs Age    
            } while (age <= 0); //End of do while loop     
            dog.add(new Dog(dogName,dogAge));//Adds Dogs information
            keyboard.nextLine();
        }
        person.get(x-1).setDog(dog);//Add sets the dog to the owner.
        dog.clear();// clears Array list            
    }

    private static void display() {
         int x = person.size();
        for (int i =0;i <= x-1;i++) // Start of For Loop
        {
			person.get(i).print();// Gets Person Information
			System.out.print("\n----- Dogs -----\n");
			for (Dog s : person.get(i).getPets()) {  
				System.out.println(s);  //Display Persons and Dogs information
			}  
		} 
	}  
}
