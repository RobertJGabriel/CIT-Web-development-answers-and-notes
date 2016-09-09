/**
 * @author Robert Gabriel
 * @version 11/10/2013
 * Lab 3
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Lab_Three {

	static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.
	static ArrayList<ActorClass> actors = new ArrayList<ActorClass>();// Objects of Actors
	static ArrayList<FlimClass> films = new ArrayList<FlimClass>();// Objects of Films

	public static void main(String[] args) {

		int menu;
		boolean exit=false;
		while(exit!=true){// Start of While Loop for Menu
			System.out.print("\nPlease Pick a Menu Option  \n[1] Add a Actor and Flims \n[2] Display All Actors \n[3] Exit \n:");
			menu = keyboard.nextInt();
			int i = actors.size();
			switch (menu) { //Start of Switch Statement
			case 1: 
				addActor();
				break;
			case 2: 
				listActor(i) ;
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


	private static void addActor() {// Add Actor and Movies to Database

		String name,address;
		int number, age;

		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.print("Please Enters Actors Name : ");
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
			age= keyboard.nextInt();//Stores Input Grade
		} while (age <= 0); //End of do while loop


		actors.add(new ActorClass(name,age,address));// Creates Actor
		int x = actors.size();//Gets actors number


		do {//Start of Do Loop
			System.out.print("Amount of Movies to Add : ");
			while (!keyboard.hasNextInt()) {// Check if has int.
				System.out.print("Amount of Movies to Add : ");
				keyboard.next(); // this is important!
			}
			number = keyboard.nextInt();//Stores Input Grade
			keyboard.nextLine();
		} while (number <= 0); //End of do while loop


		for (int i = 0 ; i <= number-1 ; i++){// Movie Titles, Start of For Loop
			System.out.print("Enter Movie title : ");
			String movieTitle = keyboard.nextLine();
			films.add(new FlimClass(movieTitle));//Add films to array films
		}

		actors.get(x-1).setFilm(films);//Add films to array
		films.clear();// clears Array list
	}

	private static void listActor(int x) {// Display all Actors from Database
		for (int i =0;i <= x-1;i++) // Start of For Loop
		{
		   
			actors.get(i).print();// Gets Actors Information

			for (FlimClass s : actors.get(i).getFlim()) {  
				System.out.print(s);  //Display Flims for actor
			}  
		}  //End of For Loop
	}
}









