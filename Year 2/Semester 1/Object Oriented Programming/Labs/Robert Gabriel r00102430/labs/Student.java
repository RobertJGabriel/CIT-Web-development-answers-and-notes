/*
 * Author : Robert Gabriel
 * Date : 29/9/2013
 * Part 2 and 3 for Lab 2
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Student {//Start of Class.
	static ArrayList<StudentClass> list = new ArrayList<StudentClass>();// Ceartes an Array of Objects.
	static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.
	static final int limit = 2;// Limits the Amount of Students to Add

	public static void main(String[] args)
	{
		int menu;
		boolean exit=false;
		while(exit!=true){// Start of While Loop for Menu
			System.out.print("\nPlease pick a menu option  \n[1] Add a Result\n[2] Remove Result \n[3] Display All Result\n[4] Exit \n:");
			menu = keyboard.nextInt();
			int i = list.size();
			switch (menu) { // Start of Switch Statment
			case 1: 
				add(i);
				break;
			case 2: 
				remove(i) ;
				break;
			case 3: 
				diplay(i);
				break;
			case 4:
				System.out.println("Exiting Progamme..... Good Bye Dave :'(");
				exit = true;// Turns Boolean Exit to true, Ends loop.
				break;
			default: System.out.print("Not Option allowed");
			break;
			}// End of Switch Statement
		}// end of do while 
	}//End of Main Method      



	private static void remove(int i) {// Removes Student form Object
	    
		int index =0;//Stores index
		if (i != 0){
		do {//Start of Do Loop
			System.out.println("Please enter a Record to remove between : 0 - "  + (i-1));
			while (!keyboard.hasNextInt()) {// Check if has int.
				System.out.print("Please Enter a index");
				keyboard.next(); // this is important!
			}
			index= keyboard.nextInt();//Stores Input Grade
		} while (index< 0);
			list.remove(index);
		}else {
			System.out.print("No One to delect yet");
		}
	}//End of Method

	public static void add(int i)// Adds Student to class and information.
	{
		if (i <= limit){// Start of If Statement
			String name ,nameLastInput ;
			int score =0;
			do{// Start of Do while Loop

				System.out.print("Please enter Student " + i  + " deatils \nStudent first name :");
				name = keyboard.next();
			}while(!name.matches("[a-zA-Z]+$"));// Validates input and checks that only string is input.


			do{// Start of Do while Loop
				System.out.print("Student's Last Name :");
				nameLastInput = keyboard.next();
			}while(!nameLastInput.matches("[a-zA-Z]+$"));// Validates input and checks that only string is input.


			do {//Start of Do Loop
				System.out.print("Student's mark :");//Input for Students Mark
				while (!keyboard.hasNextInt()) {// Check if has int.
					System.out.print("Please Enter a Students mark : ");
					keyboard.next(); // this is important!
				}
				score= keyboard.nextInt();//Stores Input Grade
			} while (score <= 0);
			System.out.println("Thank you! Students Details for " + name + " " + nameLastInput +  " was Entered"+"\n");
			
			list.add(new StudentClass (name, score, nameLastInput));  //Adds information to Class          
			
			if (i==limit){// Start of If Statement
				System.out.println("All full ");
			}// End of If Statement
		}// End of If Statement
	} // End of Method      

	public static void diplay(int i)// Display All Objects Data.
	{
		for (int x = 0; x <i; x++)// Start of for loop
		{
			System.out.println(x + " : " + list.get(x));
		} // End of for loop
	}// End of Method
}// End of Class

