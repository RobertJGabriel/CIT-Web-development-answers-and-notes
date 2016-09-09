import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Lab5 {

	static ArrayList<Lecturer> lecturerList = new ArrayList<Lecturer>();
	static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.

	public static void main(String[] args) {

		int menu;
		int id =0;
		boolean exit = false;
		final int MIN = 0 , MAX =8;
		outOfRange problem =new outOfRange("Input value is out of range.");

		while(!exit){

			try{
				System.out.println("\n[1]Add Lecturer\n[2]Find Lecturer\n[3]Add Book\n[4]Remove Book\n[5]Search for book\n[6]Calculate Yearly Payment\n[7]Output to the file\n[8]Exit");
				menu = keyboard.nextInt();
				if(menu < MIN || menu > MAX){
					throw problem;  
				}else{
					switch (menu) {
					case 1:
						addLecturer(id);//Adds Lecturer
						break;
					case 2:
						searchLecturer(id);//Searchs for Lecture
						break;
					case 3:
						addBook(id);//Adds Books
						break;
					case 4:
						removeBook(id);//Removes Book
						break;
					case 5:
						searchBook(id);   //Search Book
						break;
					case 6:
						calculateYearPayment(id);// Calcuates Yearly Payment
						break;
					case 7:
						writeToFile();//Writes Details to files
						break;
					case 8:
						exit = true;//Exits files
						System.exit(0);
						break;
					default: System.out.print("I cant let you do that dave :-O");
					break;
					}
				}
			} catch (outOfRange exception){  System.out.println("The entered value is out of range.");}
		}

	}//end while

	public static int idIndex(int id) {//gets id
		int i = 0;
		while (i < lecturerList.size()) {
			if (id == lecturerList.get(i).getId()) {
				return i;
			} else {i++;}
		} return -1;
	}

	public static void addLecturer(int id){
		String name ,temp;
		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.println("Enter lecturer's name: ");
			name  = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.

		do{// Start of Do while Loop 
			System.out.println("Enter lecturer's ID: ");//Asks for input
			temp =  keyboard.nextLine();
		}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

		id = Integer.parseInt(temp);//Converts string to int

		do {//Start of Do Loop
			if (idIndex(id) == -1) {
				lecturerList.add(new Lecturer(name, id));
			} else {
				System.out.println("ID already exists!\n");
			}
		} while (id <= 0); //End of do while loop
	}

	public static void searchLecturer(int id){

		String idtemp;
		keyboard.nextLine();

		do{// Start of Do while Loop 
			System.out.println("Enter lecturer ID: ");//Asks for input
			idtemp =  keyboard.nextLine();
		}while(!idtemp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

		id = Integer.parseInt(idtemp);//Converts string to int

		do {//Start of Do Loop
			int lecturerIndex = idIndex(id);//Gets id of lecture
			if (lecturerIndex == -1) {
				System.out.println("Id not on the list.\n");
			} else {
				lecturerList.get(lecturerIndex).print();//Prints id
			}
		} while (id <= 0); //End of do while loop
	}

	public static void addBook(int id){
		String author,temp,title;

		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.println("Enter ID of lecturer you want to add a book: ");
			temp =  keyboard.nextLine();
		}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

		id = Integer.parseInt(temp);//Converts string to int
		int lecturerIndex = idIndex(id);
		if (lecturerIndex == -1) {System.out.println("Id not on the list.\n");
		}else{

			do{// Start of Do while Loop 
				System.out.println("Enter book's title: ");
				title = keyboard.nextLine();
			}while(!title.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.

			do{// Start of Do while Loop 
				System.out.println("Enter book's ISBN: ");
				temp =  keyboard.nextLine();
			}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.
			int isbn = Integer.parseInt(temp);//Converts string to int

			do{// Start of Do while Loop 
				System.out.println("Enter book's author: ");
				author = keyboard.nextLine();
			}while(!author.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.

			System.out.println("Enter book's price: ");
			if (!keyboard.hasNextDouble()) {
				System.out.println("Enter real numeric value!\n");
				keyboard.nextLine();
			} else {
				double price = keyboard.nextDouble();
				lecturerList.get(lecturerIndex).addBook(new Book(title, isbn, author, price));
			}
		}                    
	}

	public static void removeBook(int id){
		String temp;
		int isbn,lecturerIndex;
		keyboard.nextLine();
		do{// Start of Do while Loop 
			System.out.println("Enter ID of lecturer you want to remove a book: ");
			temp =  keyboard.nextLine();
		}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

		id = Integer.parseInt(temp);//Converts string to int
		lecturerIndex = idIndex(id);

		if (lecturerIndex == -1) {System.out.println("Id is not in the list.\n");
		} else {	

			do{// Start of Do while Loop 
				System.out.println("Enter ISBN of the book to remove: ");
				temp =  keyboard.nextLine();
			}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.
			isbn = Integer.parseInt(temp);//Converts string to int

			int i = 0;
			if (lecturerList.get(lecturerIndex).getBookList().isEmpty()) {
				System.out.println("The book list is empty!\n");
			} else {
				while (i < lecturerList.get(lecturerIndex).getBookList().getTotal()) {
					if (isbn != lecturerList.get(lecturerIndex).getBookList().getBook(i).getIsbn()) {
						i++;
						if (i == lecturerList.get(lecturerIndex).getBookList().getTotal()) {
							System.out.println("ISBN not on the list!");
						}
					} else {
						lecturerList.get(lecturerIndex).removeBook(i); //removes book and this is my change.
						System.out.println("Book removed!\n");
						// ends while loop after removing a book
						i = lecturerList.get(lecturerIndex).getBookList().getTotal();
					}
				}
			}
		}
	}
	public static void searchBook(int id){
		String temp;
		int isbn,lecturerIndex ; 
		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.println("Please enter the ID of lecturer you want to search a book for: ");
			temp =  keyboard.nextLine();
		}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

		id = Integer.parseInt(temp);//Converts string to int
		lecturerIndex = idIndex(id);

		if (lecturerIndex == -1) {
			System.out.println("Id not on the list.\n");
		} else {
			do{// Start of Do while Loop 
				System.out.println("Enter ISBN of the book to search: ");
				temp =  keyboard.nextLine();
			}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.

			isbn = Integer.parseInt(temp);//Converts string to
			int i = 0;
			int sizeOfBooklist = lecturerList.get(lecturerIndex).getBookList().getTotal();//gets total of book
			try{
				while (i < sizeOfBooklist) {
					if (isbn != lecturerList.get(lecturerIndex).getBookList().getBook(i).getIsbn()) {
						i++;
						if (i == sizeOfBooklist) {System.out.println("ISBN is not on the list!");}
					} else {
						System.out.println(lecturerList.get(lecturerIndex).getBookList().getBook(i).toString() + "\n");
						i = sizeOfBooklist;// ends while loop after finding a book
					}
				}
			}catch(NullPointerException e){
				System.out.println("ISBN is not on the list!");
			}
		}
	}

	public static void calculateYearPayment(int id){

		String temp;
		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.println("Enter ID of lecturer to calculate yearly payment for: ");
			temp =  keyboard.nextLine();
		}while(!temp.matches("^[0-9.]*$"));// Validates input and checks that only string is input.
		id = Integer.parseInt(temp);//Converts string to int
		int lecturerIndex = idIndex(id);
		if (lecturerIndex == -1) {
			System.out.println("Id is not on the List .\n");
		} else {
			System.out.println("The yearly payment is: " + lecturerList.get(lecturerIndex).getBookList().calculateYearlyBookPayment());
		}
	}

	public static void writeToFile(){
		BufferedWriter bWriter = null;
		try{
			File file = new File("details.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);
			// Goes through each lecturer and writes its details
			for(int i=0; i<lecturerList.size(); i++){
				bWriter.write(lecturerList.get(i).toString());
				bWriter.newLine();
				int k=0;
				// Goes through book list and writes each book details
				while(lecturerList.get(i).getBookList().getBook(k)!=null){
					bWriter.write(lecturerList.get(i).getBookList().getBook(k).toString());
					bWriter.newLine();
					k++;
				}           
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				// clears memory and closes writer
				if(bWriter != null){
					bWriter.flush();
					bWriter.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}       
	}   
}