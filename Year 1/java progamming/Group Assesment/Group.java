import java.util.Scanner;
public class lab1 {

	public static void main(String[] args)
	{  
		//an array of courses available for registration
		int[]arrays = {28,33, 108, 231, 73,23, 83, 84, 1000,823};

		String[]numbers = {"01:","02:", "03:", "04:", "05:","06:", "07:", "08:", "09:","10:"};

		String[]options = {"List the even numbers:", 
				"List the odd numbers:", "Show the sum of all the numbers", "Show the smallest number", "Show the Biggest number",
				"Choose 2 numbers by index and show the largest", "Given a number, show whether the number is in the list", "Exit"};
		//Title print
		String title = "\nList of Integers"; 

		// spacing 
		String space = "-"; 

		Menu(numbers,arrays,title,space);
		Option(numbers,arrays ,title,space, options);

	} 
	/**
	 * To display the course menu for the registration
	 * @param codes the list of module codes
	 * @param names the list of module names
	 */
	public static void Menu(String[] numbers,int[] arrays ,String title,String space)
	{    
		//prints title and list
		System.out.println(title);
		for(int print =0; print <= 16; print++ ){
			System.out.print(space);
		}
		System.out.println("\n");
		for(int print =0; print <= 9; print++ ){
			System.out.println(numbers[print] + "\t"+ arrays[print]);
		}
	}


	// Asks user for to edit a number or not
	public static void Option(String[] numbers,int[] arrays ,String title,String space,String[]options)
	{    
		Scanner studentInput = new Scanner(System.in);  
		String studentInput4 = "y";
		do {
			System.out.print("\nWould you like to change one of these numbers(y/n)?");
			studentInput4 = studentInput.nextLine();
		}
		while (!studentInput4.equals("y") && !studentInput4.equals("n"));

		if (studentInput4.equals("y")){
			yes(numbers,arrays ,title,space,studentInput );
		}
		if (studentInput4.equals("n")){
			no(numbers,arrays ,title,space,studentInput, options );
		}
	}



	// If user Picks yes
	public static void yes(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{    
		int studentInput2 = 0;
		int	studentInput5 = 0;
		do{
			System.out.print("\nPlease select the index of the number you want to change");
			studentInput2 = studentInput.nextInt();
		}
		while (!(studentInput2 <= 10));
		
		System.out.print("\nPlease enter a replacement for the number " );
		studentInput5 = studentInput.nextInt();
		arrays[studentInput2-1] = studentInput5 ;
		Menu(numbers,arrays,title,space);
	}

	
	// if user picks no
	public static void no(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput,String[]options)
	{    
		for(int print =0; print <= 7; print++ ){
			System.out.println(numbers[print] + options[print]);
		}
		
		//Takes input for user
		int	studentInput5 = studentInput.nextInt();

		String optionsnumber;
		switch (studentInput5) {
		case 1:  optionsnumber  ="1";
		Number1(numbers,arrays ,title,space, studentInput);
		break;
		case 2:  optionsnumber = "2";
		Number2(numbers,arrays ,title,space, studentInput);
		break;
		case 3:  optionsnumber = "3";
		Number3(numbers,arrays ,title,space, studentInput);
		break;
		case 4:  optionsnumber = "4";
		Number4(numbers,arrays ,title,space, studentInput);
		break;
		case 5:  optionsnumber = "5";
		Number5(numbers,arrays ,title,space, studentInput);
		break;
		case 6:  optionsnumber = "6";
		Number6(numbers,arrays ,title,space, studentInput);
		break;
		case 7:  optionsnumber = "7";
		Number7(numbers,arrays ,title,space, studentInput);
		break;
		case 8:  optionsnumber = "8";
		Number8(numbers,arrays ,title,space, studentInput);
		break;
		default: optionsnumber = "Invalid month";
		break;
		}
		System.out.println(optionsnumber);
	}
	
	public static void Number1(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number2(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	
	public static void Number3(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number4(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number5(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number6(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number7(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	public static void Number8(String[] numbers,int[] arrays ,String title,String space, Scanner studentInput)
	{ 
	}
	}




