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


public class Finishef {

	final static String DELIMITER = "~";
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		//Menu Options Arrays		
		final  int[] menu = {1,2,3};
		final String[] options = {"Set Reminder.","Delete Message.","List Report."};

		// Variables		
		File file = new File("Information.txt");
		
		String	userName = "";
		String	password = "";
		String	message = "";
		int option;
		String yesNo ;
		int		time = 0;
		boolean flag = false;


		while(true){
			System.out.println("нннннннннннннннн Menu нннннннннннннннн");
			for (int icourse = 0; icourse < menu.length; icourse++){
				System.out.println("Option " + menu[icourse]+ " : " + options[icourse]);
			}
			System.out.println("Please Enter Option : ");
			while (!keyboard.hasNextInt()){
				keyboard.next();
				System.out.println("Please Enter a valid number ");
			
			}
			option = keyboard.nextInt();
			keyboard.nextLine();
			// If statement for menu
			if(option == 1){
				try {
					if (!file.exists()) {
						file.createNewFile();

						do { 
							System.out.println("Email App by Robert Gabriel and The Gang");
							System.out.println(" нннннннннннннннннннннннннннннннннннннннннн");
							// Your email
							do {
								
								System.out.println("Hey Whats your CIT email ?");
								userName = keyboard.nextLine();
								
							}
							while(!userName.contains("@mycit.ie"));
							
							
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
					}else {
						alertSystem(time);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
			else if(option == 2){
				do {
					System.out.println("Would you like to delete your reminder ? (y/n)");
					yesNo = keyboard.next();
				}while (yesNo.isEmpty());
				dele(yesNo);
			}
			else {
				list();
			}


		}

	}


	//
	public static void alertSystem(int time){
		System.out.print ("Loading......");
		while (true) {
			int newTime =	time * 6;
			int countdown = newTime--;
			if ( countdown==0)
			{
				EmailAlert();
				break;
			}
		}

	}




	public static void EmailAlert()
	{
		try {

			File f = new File("Information.txt");
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
					message.setSubject("Java Project reminder");
					message.setText(message3);
					//send message
					Transport.send(message);
					System.out.println("message sent");
					saveinformation(email);
				} catch (MessagingException e) {throw new RuntimeException(e);}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println( sdf.format(cal.getTime()) );
		String ee =	sdf.format(cal.getTime());
		return ee ;
	}




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
			statues[counter] = "Successfull <3 ";
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


	public static void list() 
	{
		try {
			File f = new File("success.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String record = null;
			while ((record = br.readLine()) != null) {
				String[] fields = record.split(DELIMITER);
				System.out.print("Email: " + fields[0] + "\tTime: " + fields[1] + "\tSuccessful: " + fields[2]);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	public static void dele(String yesNo){
		File file = new File("Information.txt");

		
			if (yesNo.equalsIgnoreCase("y")){

				System.out.println("Reminder file deleted, Maybe you should have a coke!!! ;)");
				file.delete();

			
		}	}



	// CreateFile
	public static void creatingFile(String userName, String password, String message, int time, File file)
	{
		try {
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(userName + DELIMITER  + password + DELIMITER  + message + DELIMITER  +  time);
			System.out.println("All Saved");

			bWriter.flush();
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
