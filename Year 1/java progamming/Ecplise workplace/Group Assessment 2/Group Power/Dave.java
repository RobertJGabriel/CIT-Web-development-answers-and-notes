import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Email{		

	final static	String weirdthing = "~";

	public static void main(String[] args) {


		String overwriteAnswer="";
		String  userName = "";
		String   password = "";
		String	 message = "";
		int 	time = 0;

		Scanner keyboard = new Scanner(System.in);
		boolean flag = false;

		try {
			File file = new File("5gt.txt");

			if (!file.exists()) {
				file.createNewFile();

				do { 
					String result = time ();

					System.out.println("Email App by Robert Gabriel and The Gang");
					System.out.println(" Its Currently " + result  + " нннннннннннннннннннннннннннннннннннннннннн");

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

			}else {
				if (file.exists()){
					System.out.println("Would you like to reuse your current file or create a new one Y/N");
					overwriteAnswer= keyboard.next().toUpperCase();

					//deletes the current file 
					optionChoice(overwriteAnswer, file, time);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			bWriter.write(userName + weirdthing + password + weirdthing + message + weirdthing +  time);
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
			file.delete();
			System.out.println("Text file deleted, Maybe you should have a coke!!! ;)");
			System.exit(0);
		}
		else{
			alertSystem(time);
		}
	} 


	// Reading File
	public static int readingTime()
	{
		try {
			final	String weirdthing = "~";
			File f = new File("5gt.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String record = null;
			while ((record = br.readLine()) != null) {
				String[] fields = record.split(weirdthing);
				//System.out.print("Email " + fields[0] + "\tPassword: " + fields[1] + "\tMessage : " + fields[2]+ "\tTime: " + fields[3]);
				System.out.println();
			int 	aInt = Integer.parseInt(fields[3]);
				
			return aInt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	
	}








	// Time and Lulz
	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println( sdf.format(cal.getTime()) );
		String ee =	sdf.format(cal.getTime());
		return ee ;
	}



	// Email Example to send the Email
	public static void EmailAlert()
	{
		String to="robert.gabriel@mycit.ie";//change accordingly

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
				return new PasswordAuthentication("gtirob@gmail.com","gtibuddy");//change accordingly
			}
		});

		//compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("gtirob@gmail.com"));//change accordingly
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject("Hello");
			message.setText("Testing.......");

			//send message
			Transport.send(message);

			System.out.println("message sent successfully");

		} catch (MessagingException e) {throw new RuntimeException(e);}

	}
}