import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ss {

	public static void main(String[] args) {
		ArrayList<Lecturer> LecList = new ArrayList<Lecturer>();
		Scanner kbd = new Scanner(System.in);
		int userInput;
		// int id=0;
		double price;
		int isbn;
		int id;

		while (true) {
			System.out.println("1. Add Lecturer" + "\n2. Find Lecturer"
					+ "\n3. Add Book" + "\n4. Remove Book"
					+ "\n5. Search for book" + "\n6. Calculate yearly payment"
					+ "\n7. Output to the file" + "\n8. Exit");
			userInput = kbd.nextInt();
			kbd.nextLine();
			switch (userInput) {
			case 1:
				System.out.println("Enter lecturer's name: ");
				String name = kbd.nextLine();
				System.out.println("Enter lecturer's id: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!");
					kbd.nextLine();
				} else {
					id = kbd.nextInt();
					kbd.nextLine();
					LecList.add(new Lecturer(name, id));
				}
				break;
			case 2:
				System.out.println("Enter lecturer ID: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!\n");
					kbd.nextLine();
				} else {
					id = kbd.nextInt();
					kbd.nextLine();
					int i = 0;
					while (i < LecList.size()) {
						if (id != LecList.get(i).getId()) {
							i++;
							if (i == LecList.size()) {
								System.out.println("Id not on the list.\n");
							}
						} else {
							LecList.get(i).print();
							break;
						}
					}
				}
				break;
			case 3:
				System.out.println("Enter ID of lecturer you want to add a book: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!\n");
					kbd.nextLine();
				} else {
					id = kbd.nextInt();
					kbd.nextLine();
					int i=0;
					while (i < LecList.size()) {
						if (id != LecList.get(i).getId()) {
							i++;
							if (i == LecList.size()) {
								System.out.println("Id not on the list.\n");
								break;
							}
						} 
					}
					
						if(LecList.get(i).getBookList().isFull()){
							System.out.println("Book list is full!");
						}else{
						System.out.println("Enter book's title: ");
						String title = kbd.nextLine();
						System.out.println("Enter book's ISBN: ");
						if (!kbd.hasNextInt()) {
							System.out.println("Enter numeric value!\n");
							kbd.nextLine();
						} else {
							isbn = kbd.nextInt();
							kbd.nextLine();
							System.out.println("Enter book's author: ");
							String author = kbd.nextLine();
							System.out.println("Enter book's price: ");
							if (!kbd.hasNextDouble()) {
								System.out
										.println("Enter real numeric value!\n");
								kbd.nextLine();
							} else {
								price = kbd.nextDouble();
								LecList.get(id).addBook(
										new Book(title, isbn, author, price));
							}
						}
					
				}}
				break;
			case 4:
				System.out
						.println("Enter ID of lecturer you want to remove a book: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!\n");
					kbd.nextLine();
				} else {
					id = kbd.nextInt();
					kbd.nextLine();
					if (id < 0 || id > LecList.size() - 1) {
						System.out.println("No such id exist!");
					} else {
						System.out.println("Enter ISBN of book to remove: ");
						if (!kbd.hasNextInt()) {
							System.out.println("Enter numeric value!\n");
							kbd.nextLine();
						} else {
							isbn = kbd.nextInt();
							kbd.nextLine();
							LecList.get(id).getBookList().removeBook(isbn);
						}
					}
				}
				break;
			case 5:
				System.out.println("Enter ID of lecturer you want to search book for: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!\n");
					kbd.nextLine();
				} else {
				id = kbd.nextInt();
				kbd.nextLine();
				System.out.println("Enter ISBN of book to search: ");
				isbn = kbd.nextInt();
				kbd.nextLine();
				LecList.get(id).getBookList().getBook(isbn).toString();
				}
				break;
			case 6:
				System.out
						.println("Enter ID of lecturer to calculate yearly payment for: ");
				if (!kbd.hasNextInt()) {
					System.out.println("Enter numeric value!\n");
					kbd.nextLine();
				} else {
					id = kbd.nextInt();
					kbd.nextLine();
					LecList.get(id).getBookList().calculateYearlyBookPayment();
				}
				break;
			case 7:
		
				break;
			case 8:
				System.out.println("Exiting Progamme..... Good Bye Dave :'(");
				//exit = true;// Turns Boolean Exit to true, Ends loop.
System.exit(0);
				break;
			default:
				System.out.println("Invalid input, enter (1 to 8)");
				break;

			}

		}

	}
}



