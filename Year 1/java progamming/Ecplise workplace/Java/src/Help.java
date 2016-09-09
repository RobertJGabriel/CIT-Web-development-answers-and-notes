                                                                     
                                                                     
                                                                     
                                             
import java.util.Scanner;

// 
public class Help {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String studentInput = " ";
		int i = 0;
		int w = 0;
		intro(i, studentInput, w, keyboard);
		System.out.print("Thank You for making a decision.");
	}
	
	
	//An array for Class Module Names

	static void intro(int i, String studentInput, int w, Scanner keyboard) {
		String[] moduleNames;
		moduleNames = new String[10];
		moduleNames[0] = "Problem Solving&Programming 1";
		moduleNames[1] = "Computer Hardware";
		moduleNames[2] = "Networking Fundamentals 1";
		moduleNames[3] = "Web Development Fundamentals ";
		moduleNames[4] = "Essential Mathematical Skills ";
		moduleNames[5] = "Creativity,Innovation&Teamwork ";
		moduleNames[6] = "Statistics & Probability";
		moduleNames[7] = "Introduction to HCI";
		moduleNames[8] = "Operating Systems Fundamental";
		moduleNames[9] = "Web Publishing\n";

	//An Array for Class Modules Number
		String[] studentModulesPicks = new String[6];
		studentModulesPicks[0] = "1";
		studentModulesPicks[1] = "2";
		studentModulesPicks[2] = "3";
		studentModulesPicks[3] = "4";
		studentModulesPicks[4] = "5";
		studentModulesPicks[5] = "6";
		
		
		//An Array for Class Module Codes

		String[] moduleNumbers = new String[10];
		moduleNumbers[0] = "SOFT6008";
		moduleNumbers[1] = "COMH6003";
		moduleNumbers[2] = "COMP6026";
		moduleNumbers[3] = "SOFT6007";
		moduleNumbers[4] = "MATH6000";
		moduleNumbers[5] = "CMOD6001";
		moduleNumbers[6] = "STAT6000";
		moduleNumbers[7] = "SOFT6002";
		moduleNumbers[8] = "SOFT6003";
		moduleNumbers[9] = "COMP6023";

	
		
		//Welcome Menu
		System.out.println("Modules Available:");
		lists(i, moduleNumbers, moduleNames, studentModulesPicks);
		System.out.println("You must choose 6 modules from the list above. At any time, you can enter \n'C' to list your current selection or 'A' to list the available modules. \n");
		input(i, studentInput, w, keyboard, moduleNumbers, moduleNames, studentModulesPicks);
	}

		//Method to input all the values
	static void input(int i, String studentInput, int w, Scanner keyboard, String[] moduleNumbers,
					String[] moduleNames, String[] studentModulesPicks) {
		while(w <= 5) {
		System.out.print("Please, enter a module code:");
		studentInput = keyboard.nextLine().toUpperCase();
			if (studentInput.equalsIgnoreCase("a")) {
				lists(i, moduleNumbers, moduleNames, studentModulesPicks);
				input(i, studentInput, w, keyboard, moduleNumbers, moduleNames, studentModulesPicks);
			} else if (studentInput.equalsIgnoreCase("c")) {
				moduleNumbersPick(i, studentModulesPicks, w);
				input(i, studentInput, w, keyboard, moduleNumbers, moduleNames, studentModulesPicks);
			} else {
				boolean isPicked = false;
				while(!isPicked){
					for(int j=0; j<=9; j++){
						if(studentInput.equals(moduleNumbers[j])) {
							if(!isAlreadyPicked(studentModulesPicks, studentInput)){
								studentModulesPicks[w] = studentInput;
								System.out.println(moduleNames[j] + " is added to the list of Modules");
								w++;
								j = 10;
								isPicked = true;
							} else {
								System.out.println("This Module is already picked");
								j = 10;
								isPicked = true;
							}
							
						} else if (j == 9){
							System.out.println("Sorry, this is an invalid input");
									isPicked = true;
								}
					}
				}
				
		}
		}
	}

	//This method checks if the module is already picked or not.
	static boolean isAlreadyPicked(String[] studentModulesPicks, String studentInput){
		boolean isPicked = false;
		int j = 0;
		while(j < 5){
			if(studentInput.toUpperCase().equals(studentModulesPicks[j])){
				isPicked = true;
				return isPicked;
			} else if(j==5){
			isPicked = false;
					}
			j++;
		}
		return isPicked;
	}
	//Method to print out the module picks.
	static void moduleNumbersPick(int i, String[] studentModulesPicks, int w) {
		for (i = 0; i <= w; i++) {
			System.out.println(studentModulesPicks[i]);
		}
	}
	//Method to print out module names. 
	static void lists(int i, String[] moduleNumbers, String[] moduleNames,
			String[] studentModulesPicks) {
		for (i = 0; i < 10; i++) {
			System.out.println(moduleNumbers[i] + moduleNames[i]);
		}
	}
}