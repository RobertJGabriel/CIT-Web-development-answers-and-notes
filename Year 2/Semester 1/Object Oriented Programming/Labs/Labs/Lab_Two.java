/*
 * Author : Robert Gabriel
 * Date : 29/9/2013
 * Lab One Part 1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Lab_Two {
    
    //Array Lisit
	static ArrayList<String> firstName = new ArrayList<String>();
	static	ArrayList<String> lastName = new ArrayList<String>();
	static	ArrayList<Integer> mark = new ArrayList<Integer>();
	static	ArrayList<String> result = new ArrayList<String>();
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.
		final int LIMIT=2; // Amount of Students allowed
		
		int i = 0,marks  = 0; // Int's used 
		String firstNameInput,lastNames;// Strings used

		for( i = 0; i <= LIMIT; i++){// Start of for loop			

			do{// Start of Do while Loop
				System.out.print("Please enter Student " + i  + " deatils \nStudent first name :");//input for firstName
				firstNameInput = keyboard.next();
			}while(!firstNameInput.matches("[a-zA-Z]+$"));// Validates input and checks that only string is input.


			do{// Start of Do while Loop
				System.out.print("Student's Last Name :");
				lastNames= keyboard.next();
			}while(!lastNames.matches("[a-zA-Z]+$"));// Validates input and checks that only string is input.


			do {//Start of Do Loop
				System.out.print("Student's mark :");//Input for Students Mark
				while (!keyboard.hasNextInt()) {// Check if has int.
					System.out.print("Please Enter a Students mark : ");
					keyboard.next(); // this is important!
				}
				marks = keyboard.nextInt();//Stores Input Grade
			} while (marks <= 0);
			System.out.println("Students Details for " + firstNameInput+ " " + lastNames +  " was Entered"+"\n");

			String 	results = 	result(marks,i);// Stores Users Result

			firstName.add(firstNameInput);// Adds Students First name to ArrayList
			lastName.add(lastNames);// Adds Students last name to ArrayList
			mark.add(marks);// Adds Students mark to ArrayList
			result.add(results);// Adds Students result to ArrayList
		}
		Display(LIMIT);
	}//End Method


	public static void Display(int LIMIT) {// Sets the Results input for the Student

		for (int i = 0 ; i<= LIMIT;i++){// Start of For Loop
			System.out.println(firstName.get(i) + " " +  lastName.get(i) + " received a " + result.get(i) + " for his mark of " + mark.get(i) );// Displays all arraylist input once limit is reached
		}// End of For Loop
	}

	private static String result(int mark, int i ) {// Sets the Results input for the Student

		if (mark >= 85 && mark<= 100)// Start of If statement, Setting Results.
		{
			return "Distinction";// String Result is set as Distinction

		}else if (mark >= 65 && mark<= 84) {

			return    "Merit";// String Result is set as Merit

		}else if(mark >= 40 && mark<= 64){

			return   "Pass";// String Result is set as Pass

		}else if (mark<= 39) {

			return   "Fail";// String Result is set as Fall
		}//End of If Statement		
		return null;
	}//End Method
}//end Class
