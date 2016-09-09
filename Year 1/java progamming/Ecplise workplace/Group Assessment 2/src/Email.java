import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Email{		

	final static String DELIMITER = "~";

	public static void main(String[] args) {
		final  int[] menu = {1,2,3};
		String overwriteAnswer="";
		String  userName = "";
		String   password = "";
		String	 message = "";
		int 	time = 0;
		boolean flag = false;



		final String[] options = {"Set Reminder","Delet Messeage","List Report"};
		Scanner keyboard = new Scanner(System.in);
		while(true){
			System.out.println("Welcome");
			System.out.println("Menu  нннннннннннннннннннннннннннннннннннннннннн");

			for (int icourse = 0; icourse < menu.length; icourse++){
				System.out.println("Option " + menu[icourse]+ " = " + options[icourse]);
			}
			int option;


			System.out.println("Please Enter Option : ");
			option = keyboard.nextInt();


			if(option == 1){
				try{
					File file = new File("Information2.txt");

					if (file.exists()) {
						file.createNewFile();
						do { 
							System.out.println("Email App by Robert Gabriel and The Gang");
							System.out.println(" нннннннннннннннннннннннннннннннннннннннннн");
							// Your email
							do {
								System.out.println("Hey Whats your email ?");
								userName = keyboard.nextLine();
							}
							while(!userName.contains("@"));
							// Password
							do{
								System.out.println("Password: ");
								password  = keyboard.nextLine();
							}
							while(password.equals(""));

							//Message

							System.out.println("What message would you like to send yourself as a reminder?");
							message  = keyboard.nextLine();


							System.out.println("What time would you like to send this message today(in minutes)?");
							time  = keyboard.nextInt();

							flag = true;

						} while (flag = false);
						//creates the file 
						creatingFile(userName, password, message, time, file);
					} else {
						EmailAlert();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if (option == 2) {
				File file = new File("Information2.txt");	
				
				if (file.exists()) {
					if (overwriteAnswer.equals("Y")){
						file.delete();
						System.out.println("Reinder Deltied file deleted, Maybe you should have a coke!!! ;)");
						System.exit(0);
					}
					else{
						alertSystem(time);
					}

				}
			}else if (option == 4) {


			}else {
				
			}

		}
	}






	public static void alertSystem(int time){
		while (true) {
			int countdown = time--;
			if ( countdown==0)
			{
				EmailAlert();
			}
		}

	}







	// Time and Lulz
	public static void creatingFile(String userName, String password, String message, int time, File file)
	{
		try {
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(userName + DELIMITER  + password + DELIMITER  + message + DELIMITER  +  time);
			bWriter.flush();
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void optionChoice(String overwriteAnswer, File file, int time){
		// debug
		int result = readingTime ();
		System.out.println(result);
		//end	
		if (overwriteAnswer.equals("Y")){
		
			System.out.println("Text file deleted, Maybe you should have a coke!!! ;)");
			file.delete();
		}
		else{
			alertSystem(time);
		}
	} 


	// Reading File and passes the cloak timer
	public static int readingTime()
	{
		try {
			File f = new File("Information.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String record = null;
			while ((record = br.readLine()) != null) {
				String[] fields = record.split(DELIMITER);
				//passes the int
				int aInt = Integer.parseInt(fields[3]);
				return aInt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

public static void list() 
{
	try {
		File f = new File("highscores.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String record = null;
		while ((record = br.readLine()) != null) {
			String[] fields = record.split("\\*");
			System.out.print("Name: " + fields[0] + "\tScore: " + fields[1]);
			System.out.println();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}



	// Gets time for machine and whats happening
	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println( sdf.format(cal.getTime()) );
		String ee =	sdf.format(cal.getTime());
		return ee ;
	}


	//Creates and stores information for successfull sends and who sent it
	public static void saveinformation(String email)
	{
		String result = time ();
		final String FILE_PATH = "success.txt";
		final int MAX_SCORES = 10;
		final String DELIMITER = "~";
		// declare this outside the try block so that we can reference it in the finally block
		BufferedWriter bWriter = null; 

		try {

			FileWriter fWriter = new FileWriter(FILE_PATH , true);
			bWriter = new BufferedWriter(fWriter);

			Scanner keyboard = new Scanner(System.in);

			// parallel arrays to store the high scores table
			String[] emails = new String[MAX_SCORES];
			String[] statues = new String[MAX_SCORES];
			String[] date = new String[MAX_SCORES];

			// initialise the arrays
			Arrays.fill(emails, "");
			Arrays.fill(statues, "");
			Arrays.fill(date, "");

			// read into the parallel arrays until the exit condition is typed, up to MAX_SCORES number of times
			int counter = 0;
			emails[counter] = email;
			statues[counter] = "message sent successfully";
			date[counter] = result;
			counter++;
			// write a record to the file for each row in the parallel arrays, stopping when an empty element is found
			counter = 0;
			while (counter < MAX_SCORES && !emails[counter].isEmpty()) {
				bWriter.write(emails[counter] + DELIMITER + date[counter]+ DELIMITER + statues[counter]);
				bWriter.newLine();
				counter++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//Close the BufferedWriter
			try {
				if (bWriter != null) {
					bWriter.flush();
					bWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}




	// Email Example to send the Email
	public static void EmailAlert()
	{
		try {
			File f = new File("Information.txt");;
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String record = null;
			while ((record = br.readLine()) != null) {
				String[] fields = record.split(DELIMITER);

				final String email = fields[0];
				final String password = fields[1];
				String message3 = fields[2];

				String to=email;//change accordingly

				//Get the session object
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email,password);//change accordingly
					}
				});

				//compose message
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(email));//change accordingly
					message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
					message.setSubject("Java Proget reminder");
					message.setText(message3);
					//send message
					Transport.send(message);
					System.out.println("message sent successfully");
					saveinformation(email);
				} catch (MessagingException e) {throw new RuntimeException(e);}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}