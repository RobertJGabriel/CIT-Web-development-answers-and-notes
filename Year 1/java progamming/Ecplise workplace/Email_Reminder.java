/**
	 * 
	 * @param userName
	 *            The users email
	 * @param password
	 *            The users password
	 * @param message
	 *            The message you want emailed to yourself as a reminder
	 * @param time
	 *            How long before the email is to be sent
	 * @param file
	 *            The file that is going to be created
	 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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

/**
 * Venga boys Team: Robert Gabriel, Matthew Cooney, Sebastian Blesznowski, Dave
 * Cahill
 * 
 * The email reminder application implements an application that sends the user
 * a reminder to their email.
 * 
 * Venga boys java doc
 */

public class Email_Reminder {

	final static String DELIMITER = "~";

	/**
	 * 
	 * @param args
	 *            Reads information from the keyboard and displaying the menu
	 *            options
	 */

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		// Menu Options Arrays
		final int[] menu = { 1, 2, 3, 4 };
		final String[] options = { "Set Reminder.", "Delete Message.",
				"List Report.", "Send Message" };
		int option;
		while (true) {
			System.out.println("нннннннннннннннн Menu нннннннннннннннн");
			for (int icourse = 0; icourse < menu.length; icourse++) {
				System.out.println("Option " + menu[icourse] + " : "
						+ options[icourse]);
			}
			System.out.println("Please Enter Option : ");
			while (!keyboard.hasNextInt()) {
				keyboard.next();
				System.out.println("Please Enter a valid number ");
			}

			option = keyboard.nextInt();
			keyboard.nextLine();

			// ------------Menu
			if (option == 1) {
				menuOptionOne(keyboard);
			} else if (option == 2) {
				menuOptionTwo(keyboard);
			} else if (option == 4) {

				int time = 1;
				alertSystem(time);
			} else if (option == 3) {
				list();
			}
		}
	}

	/**
	 * 
	 * @param keyboard
	 *            The user enters the necessary information needed to create the
	 *            reminder (email, password etc.)
	 */

	public static void menuOptionOne(Scanner keyboard) {
		File file = new File("Information.txt");
		boolean flag = false;
		String userName = "";
		String password = "";
		int time = 0;
		String message = "";
		try {
			if (!file.exists()) {
				file.createNewFile();
				do {
					System.out.println("Email App by the Venga Boyz");
					System.out.println(" нннннннннннннннннннннннннннннннннннннннннн");
					// Your email
					do {
						System.out.println("Hey Whats your CIT email ?");
						userName = keyboard.nextLine();
					} while (!userName.contains("@mycit.ie"));
					// Password
					do {
						System.out.println("Password: ");
						password = keyboard.nextLine();
					} while (password.equals(""));
					// Message
					System.out.println("What message would you like to send yourself as a reminder?");
					message = keyboard.nextLine();

					System.out.println("What time would you like to send this message today(in minutes)?");
					time = keyboard.nextInt();

					flag = true;
				} while (flag = false);
				// creates the file
				creatingFile(userName, password, message, time, file);
			} else {
				System.out.println("Awesome , you have already set a reminder ,psss click number 4 ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param keyboard
	 *            Gives the user the option of deleting the file
	 */

	// menu two Option , Deleting the File.
	public static void menuOptionTwo(Scanner keyboard) {
		String yesNo = null;
		do {
			System.out.println("Would you like to delete your reminder ? (y/n)");
			yesNo = keyboard.next();
		} while (yesNo.isEmpty());
		dele(yesNo);
	}

	/**
	 * 
	 * @param time
	 *            The length of time in which you are going to wait for the
	 *            reminder to send in minutes
	 */
	public static int readTime() {
		try {
			File f = new File("Information.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String record = null;
			while ((record = br.readLine()) != null) {
				String[] fields = record.split(DELIMITER);
				String time = fields[3];
				int timeValue = Integer.parseInt(time);
				return timeValue;
			}
		} catch (Exception e) {
		}
		;
		return 0;

	}

	// Limiting the User to the CountDown before it goes off.
	public static void alertSystem(int time) {
		int countDownTime = readTime();
		System.out.print("\n" + countDownTime);
		int countTimer = countDownTime * 60000;
		System.out.print("\nLoading......");
		for (int i = countTimer; i >= 0; i--) {

			if (i == 0) {
				EmailAlert();
				break;
			}
		}
	}

	public static void EmailAlert() {
		final String Successfull = "Successfull";
		final String wasntSuccfull = "Didnt send ";

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

				String to = email;// change accordingly

				// Get the session object
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
								return new PasswordAuthentication(email,password);
							}
						});

				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(email));// change
																// accordingly
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(to));
					message.setSubject("Java Project reminder");
					message.setText(message3);
					// send message
					Transport.send(message);
					saveInformation(email, Successfull);
					System.out.println("message sent");

				} catch (Exception e) {
				}
			}
			// closing feed
			br.close();
		} catch (Exception e) {
			saveInformation("Unsent Email ", wasntSuccfull);
			System.out.println("Error  :(");
		}
	}

	public static String currentTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String stampTime = sdf.format(cal.getTime());
		return stampTime;
	}

	/**
	 * javadoc.txt
	 * 
	 * @param email
	 *            This details of what to send and who to send it too.
	 * @param successfulMessage
	 *            Whether or not the email is sent.
	 */

	public static void saveInformation(String email, String successfulMessage) {
		File file = new File("success.txt");
		String result = currentTime();
		final String FILE_PATH = "success.txt";
		final int MAX_SCORES = 10;
		final String DELIMITER = "~";
		// declare this outside the try block so that we can reference it in the
		// finally block
		BufferedWriter bWriter = null;

		try {
			FileWriter fWriter = new FileWriter(FILE_PATH, true);
			bWriter = new BufferedWriter(fWriter);

			// parallel arrays to store the high scores table
			String[] emails = new String[MAX_SCORES];
			String[] statues = new String[MAX_SCORES];
			String[] date = new String[MAX_SCORES];

			// initialise the arrays
			Arrays.fill(emails, "");
			Arrays.fill(statues, "");
			Arrays.fill(date, "");

			// read into the parallel arrays until the exit condition is typed,
			// up to MAX_SCORES number of times
			int counter = 0;
			emails[counter] = email;
			statues[counter] = successfulMessage;
			date[counter] = result;
			counter++;
			// write a record to the file for each row in the parallel arrays,
			// stopping when an empty element is found
			counter = 0;
			while (counter < MAX_SCORES && !emails[counter].isEmpty()) {
				bWriter.write(emails[counter] + DELIMITER + date[counter]
						+ DELIMITER + statues[counter]);
				bWriter.newLine();
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the BufferedWriter
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

	public static void list() {
		try {

			File file = new File("success.txt");
			if (file.exists()) {
				File f = new File("success.txt");
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String record = null;
				while ((record = br.readLine()) != null) {
					String[] fields = record.split(DELIMITER);
					System.out.print("Email: " + fields[0] + "\tTime: " + fields[1] + "\tSuccessful: " + fields[2]);
					System.out.println();
				}
			} else {
				System.out.println("There isnt any Information there yet, make sur eyou set a reminder ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Deletes the informations

	/**
	 * 
	 * @param yesNo
	 *            Offers you the option to delete the file.
	 */

	public static void dele(String yesNo) {
		File file = new File("Information.txt");
		if (yesNo.equalsIgnoreCase("y")) {
			System.out.println("Reminder file deleted, Maybe you should have a coke!!! ");
			try {
				new FileOutputStream(file).close();
				file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param userName
	 *            The users email
	 * @param password
	 *            The users password
	 * @param message
	 *            The message you want emailed to yourself as a reminder
	 * @param time
	 *            How long before the email is to be sent
	 * @param file
	 *            The file that is going to be created
	 */

	// Creates Information file , name email and etc
	public static void creatingFile(String userName, String password,
			String message, int time, File file) {
		try {
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(userName + DELIMITER + password + DELIMITER + message + DELIMITER + time);
			System.out.println("All Saved");
			bWriter.flush();
			bWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}