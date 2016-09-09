import java.util.Scanner;

public class Student_Modules {


	//Courses available for registration
	final static String[] moduleNumbers = {"SOFT6018", 
		"COMH6003", "COMP6026", "SOFT6007", "MATH6000",
		"CMOD6001", "STAT6000", "SOFT6002", "SOFT6003",
	"COMP6023"};

	//Courses name available for registration
	final static String[] moduleNames = {"Problem Solving&Programming 1",
		"Computer Hardware", "Networking Fundamentals 1", 
		"Web Development Fundamentals", "Essential Mathematical Skills",
		"Creativity,Innovation&Teamwork", "Statistics & Probability",
		"Introduction to HCI", "Operating Systems Fundamentals",
	"Web Publishing"};


	public static void main(String[] args) {

		//Students Modules and Names 
		String[] modulesChoosen = {"", "", "", "", "","","","","",""};
		String[] moduleNumbersChoosen = {"", "", "", "", "","","","","",""};

		//Counters for Module
		int moduleCounter = 0;	

		//Calling Methods
		courseMenu(moduleNumbers,moduleNames);
		System.out.println("\nYou must choose 6 modules from the list above. At any time, you\ncan enter C to list your current selection or A to list the available modules. \n");
		Input(modulesChoosen,moduleNumbersChoosen,moduleCounter);

	} 

	//Displays Menu
	public static void courseMenu(String [] codes,String []names)
	{    
		System.out.println("Modules Available");
		for (int icourse = 0; icourse < codes.length; icourse++)
			System.out.println(codes[icourse] + " " + names[icourse]);
	}


	public static void Input(String[] modulesChoosen,String[] moduleNumbersChoosen,int moduleCounter )
	{ 
		Scanner keyboard = new Scanner(System.in);
		String userInput = "";
		System.out.print("\nEnter module code:");
		userInput = keyboard.nextLine();

		// Validates if they input a course number or not.
		valadation(userInput);

		/* If Statement depending on what the user enters.
		If user input a , it prints a list of all modules.
		If user inputs c, it prints all modules the user has enter.
		If a course number it adds it to the List.
		If anything else it validation.
		 */
		if(userInput.equalsIgnoreCase("a"))
			courseMenu(moduleNumbers,moduleNames);
		else if(userInput.equalsIgnoreCase("c"))
			userChooses(modulesChoosen, moduleNumbersChoosen, moduleCounter); 
		else if (valadation(userInput)==true)
			adding(modulesChoosen, moduleNumbersChoosen, userInput, moduleCounter ) ;
		else 
			System.out.print("Invalid module!");
			Input(modulesChoosen, moduleNumbersChoosen, moduleCounter );
	}



	// Adding Classes to The Students List

	static void adding(String[] modulesChoosen, String[] moduleNumbersChoosen, String userInput,int moduleCounter) {
		if (moduleCounter!=5){
			for( int i=0; i < moduleNumbers.length;i++ ){
				/* 
				 * Checks if the Course Number was already input
				 */
				boolean Flag= alreadyThere(moduleNumbersChoosen, i, userInput);
				if(Flag==true){
					System.out.print("\nYou have already chosen that module. Please make an alternative choice.\n");
					Input(modulesChoosen, moduleNumbersChoosen, moduleCounter );
				}else{ 
					/* 
					 * It adds the course the user picks to the array.
					 * Adds the String of course numbers and course names.
					 * Takes 1 away from the module counter until 0
					 */
					if(moduleNumbers[i].equals(userInput)){
						int CountDown= 5-moduleCounter;
						System.out.println("\n"+moduleNames[i] +" added to list of modules." + CountDown+" more  required.\n");
						String finalName = Addingclasses(moduleCounter ,i,moduleNumbersChoosen,modulesChoosen);
						moduleCounter++;	
						Input(modulesChoosen, moduleNumbersChoosen,moduleCounter );
					}
				}
			}
		}else{
			//When the student has picked 6 or more classes.
			System.out.println("\nYou have entered your 6 choices for this semester. Modules  chosen displayCounterere:");
			Input(modulesChoosen, moduleNumbersChoosen, moduleCounter);
		}
	} 



	/* 
	 * Adds the Two modules together and start them.
	 */

	static String Addingclasses(int chooseCounter,int i,String[] moduleNumbersChoosen,String[] modulesChoosen) {
		modulesChoosen[chooseCounter]=moduleNames[i] ; 
		moduleNumbersChoosen[chooseCounter]=moduleNumbers[i] ; 
		return moduleNumbersChoosen[chooseCounter] + " " + modulesChoosen[chooseCounter];
	}


	//Checking if the course they enter in is already entered before
	public static boolean alreadyThere(String[] moduleNumbersChoosen,int i,String userInput)
	{  
		if (moduleNumbersChoosen[i].equals(userInput)){
			return true;
		}return false;
	}


	/*
	 * Displays the list of what the user has picked.
	 */
	static void userChooses(String[] modulesChoosen,String[] moduleNumbersChoosen,int moduleCounter) {
		for (int i = 0; i <=  moduleCounter ; i++) {
			System.out.println(moduleNumbersChoosen[i] + " " + modulesChoosen[i]);
		}
	}

	/*
	 * Validation if its course number or not.
	 */
	public static boolean valadation(String userInput)
	{  
		for ( int chooseCounter =0 ; chooseCounter <= 9;chooseCounter++) {
			if(moduleNumbers[chooseCounter].equals(userInput)){
				return true;
			}
		}
		return false;
	}
}



