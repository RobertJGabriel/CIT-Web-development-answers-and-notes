import java.util.Scanner;

public class test {


	//an array of courses available for registration
	final static String[] moduleNumbers = {"SOFT6018", 
		"COMH6003", "COMP6026", "SOFT6007", "MATH6000",
		"CMOD6001", "STAT6000", "SOFT6002", "SOFT6003",
	"COMP6023"};

	//an array of courses name available for registration
	final static String[] moduleNames = {"Problem Solving&Programming 1",
		"Computer Hardware", "Networking Fundamentals 1", 
		"Web Development Fundamentals", "Essential Mathematical Skills",
		"Creativity,Innovation&Teamwork", "Statistics & Probability",
		"Introduction to HCI", "Operating Systems Fundamentals",
	"Web Publishing"};


	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		//Students Modules and Names 
		String[] modulesChoosen = {"", "", "", "", "","","","","",""};
		String[] moduleNumbersChoosen = {"", "", "", "", "","","","","",""};

		//Counters for Module
		int moduleCounter = 5;	
		int chooseCounter = 0;

		//Setting off Modules
		courseMenu(moduleNumbers,moduleNames);
		Input(keyboard,modulesChoosen,moduleNumbersChoosen,chooseCounter,moduleCounter);

	} 


	public static void courseMenu(String [] codes,String []names)
	{    
		System.out.println("Modules Available");
		for (int icourse = 0; icourse < codes.length; icourse++)
			System.out.println(codes[icourse] + " " + names[icourse]);
		System.out.println("\nYou must choose 6 modules from the list above. At any time, you\ncan enter C to list your current selection or A to list the available modules. \n");
	}

	public static void Input(Scanner keyboard,String[] modulesChoosen,String[] moduleNumbersChoosen,int chooseCounter, int moduleCounter )
	{ 

		String userInput = "";
		System.out.print("\nEnter module code:");
		userInput = keyboard.nextLine();

		// Users the back end boolean in cause they dont pick a or c
		valadation(userInput);

		// If Statement depending on what the user enters.
		if(userInput.equalsIgnoreCase("a"))

			lists(modulesChoosen,moduleNames,moduleNumbers);

		else if(userInput.equalsIgnoreCase("c"))

			userChooses(modulesChoosen, moduleNumbersChoosen, chooseCounter, moduleCounter); 

		else if (valadation(userInput)==true)

			adding(keyboard,modulesChoosen, moduleNumbersChoosen, userInput,chooseCounter, moduleCounter ) ;

		else 
			System.out.print("Invalid module!");

		Input(keyboard,modulesChoosen, moduleNumbersChoosen,chooseCounter, moduleCounter );
	}



	// Displays the students picks so far 
	static void adding(Scanner keyboard,String[] modulesChoosen, String[] moduleNumbersChoosen, String userInput,int chooseCounter,int moduleCounter) {
		if (moduleCounter!=0){
			for( int i=0; i < moduleNumbers.length;i++ ){

				boolean Flag= alreadyThere(moduleNumbersChoosen, i, userInput);
				if(Flag==true){
					System.out.print("\nYou have already chosen that module. Please make an alternative choice.\n");
					Input(keyboard,modulesChoosen, moduleNumbersChoosen,chooseCounter, moduleCounter );
				}
				else{ 
					//check if string array contains the string
					if(moduleNumbers[i].equals(userInput)){
						//string found
						System.out.println("\n"+moduleNames[i] +" added to list of modules." + moduleCounter +" more  required.\n");
						String finalName = Addingclasses(chooseCounter,i,moduleNumbersChoosen,modulesChoosen);
						chooseCounter++;moduleCounter--;	
						Input(keyboard,modulesChoosen, moduleNumbersChoosen,chooseCounter, moduleCounter );
					}
				}
			}
		}else {
			//When the studnet has picked 6 or more classes.
			System.out.println("\nYou have entered your 6 choices for this semester. Modules  chosen displayCounterere:");
			Input(keyboard,modulesChoosen, moduleNumbersChoosen,chooseCounter, moduleCounter);
		}
	} 



	static String Addingclasses(int chooseCounter,int i,String[] moduleNumbersChoosen,String[] modulesChoosen) {
		modulesChoosen[chooseCounter]=moduleNames[i] ; 
		moduleNumbersChoosen[chooseCounter]=moduleNumbers[i] ; 
		return moduleNumbersChoosen[chooseCounter] + " " + modulesChoosen[chooseCounter];
	}



	public static boolean alreadyThere(String[] moduleNumbersChoosen,int i,String userInput)
	{  
		if (moduleNumbersChoosen[i].equals(userInput))
		{
			return true;
		}
		return false;
	}


	static void lists(String[] modulesChoosen,String[] moduleNames,String[] moduleNumbers) {
		for (int chooseCounter = 0; chooseCounter < 10; chooseCounter++) {
			System.out.println(moduleNumbers[chooseCounter] + " " +moduleNames[chooseCounter]);
		}
	}

	// Displays the students picks so far 
	static void userChooses(String[] modulesChoosen,String[] moduleNumbersChoosen, int chooseCounter,int moduleCounter) {
		for (int i = 0; i <=  chooseCounter-1 ; i++) {
			System.out.println(moduleNumbersChoosen[i] + " " + modulesChoosen[i]);
		}
	}


	//The boolean for checking i
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



