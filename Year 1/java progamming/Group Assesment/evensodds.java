

import java.util.Scanner;

public class evensodds {

	public static void main(String[] args) {
		// an array of courses available for registration
		int[] arrays = { 28, 33, 108, 231, 73, 23, 83, 84, 1000, 823 };

		String[] numbers = { "01:", "02:", "03:", "04:", "05:", "06:", "07:",
				"08:", "09:", "10:" };

		String[] options = { "List the even numbers:", "List the odd numbers:",
				"Show the sum of all the numbers", "Show the smallest number",
				"Show the Biggest number",
				"Choose 2 numbers by index and show the largest",
				"Given a number, show whether the number is in the list",
				"Exit" };
		// Title print
		String title = "\nList of Integers";

		// spacing
		String space = "-";
		Menu(numbers, arrays, title, space);
		help(numbers, arrays, title, space, options);

	}

	/**
	 * To display the course menu for the registration
	 * 
	 * @param codes
	 *            the list of module codes
	 * @param names
	 *            the list of module names
	 */
	public static void Menu(String[] numbers, int[] arrays, String title,
			String space) {

		// prints title and list
		System.out.println(title);
		for (int print = 0; print <= 16; print++) {
			System.out.print(space);
		}
		System.out.println("\n");
		for (int print = 0; print <= 9; print++) {
			System.out.println(numbers[print] + "\t" + arrays[print]);
		}
	}

	public static void help(String[] numbers, int[] arrays, String title,
			String space, String[] options) {
		Scanner studentInput = new Scanner(System.in);
		String studentInput4 = "";
		System.out.print("\nWould you like to change one of these numbers?");
		studentInput4 = studentInput.nextLine();

		if (studentInput4.equals("y")) {
			System.out
					.print("\nPlease select the index of the number you want to change");
			int studentInput2 = studentInput.nextInt();
			int number1 = studentInput2;
			System.out.print("\nPlease enter a replacement for the number "
					+ arrays[number1 - 1]);
			int studentInput5 = studentInput.nextInt();
			arrays[number1 - 1] = studentInput5;

			Menu(numbers, arrays, title, space);

		} else if (studentInput4.equals("n")) {
			for (int print = 0; print <= 7; print++) {
				System.out.println(numbers[print] + options[print]);
			}
			// Takes input for user
			int studentInput5 = studentInput.nextInt();

			String optionsnumber;
			switch (studentInput5) {
			/*
			 * Author Matthew Cooney This selection displays the even numbers
			 * from the Arrays list
			 */
			case 1:
				optionsnumber = "1";
				for (int i = 0; i < arrays.length; i++) {
					if (arrays[i] % 2 == 0)
						System.out.println(arrays[i] + " is even number.");
				}
				break;
			/*
			 * Author Matthew Cooney This selection displays the odd numbers
			 * from the Arrays list
			 */
			case 2:
				optionsnumber = "2";
				for (int i = 0; i < arrays.length; i++) {
					if (arrays[i] % 2 != 0)

						System.out.println(arrays[i] + " is odd number.");
				}
				break;
			case 3:
				optionsnumber = "3";
			break;
			case 4:
				optionsnumber = "4";
				break;
			case 5:
				optionsnumber = "5";
				break;
			case 6:
				optionsnumber = "6";
				break;
			case 7:
				optionsnumber = "7";
				break;
			case 8:
				optionsnumber = "8";
				break;

			default:
				optionsnumber = "Invalid month";
				break;
			}
			System.out.println(optionsnumber);
		}
		Menu(numbers, arrays, title, space);
	}
}