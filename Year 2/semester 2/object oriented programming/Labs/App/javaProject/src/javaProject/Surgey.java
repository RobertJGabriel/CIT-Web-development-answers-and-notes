
package javaProject;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;





public class Surgey extends JFrame implements ActionListener { 

	private int surgeryId ; 
	private String surgeryAdd; 
	private static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	public static String holder;
	public String password;

	
	DefaultListModel<String> teste;
	public static void main(String[] args) { 
		Surgey Surgey = new Surgey();
		docList.add(new Doctor("rob", "sss"));
		docList.add(new Doctor("Kenneth", "sss"));
		docList.add(new Doctor("Batman", "sss"));
		docList.add(new Doctor("Ign", "sss"));

	}

	public  Surgey() { 
		login();
	}

	

	private void addList(){
		for(Patient s : docList.get(1).getPatient())  
		{  
			teste.addElement(s.getPName() + "\t" +  s.getPPhone());

		}

	}
	private  void addDoctor() { 

	
	} 

	private static void updateDoctor() { 

	} 
	private static void saveSystemToFile() { 
		BufferedWriter bWriter = null;
		try{
			File file = new File("Database.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);
			// Goes through each lecturer and writes its details
			for(int i=0; i<docList.size(); i++){
				bWriter.write(docList.get(i).getDoctorName().toString());
				bWriter.write(";");
				bWriter.write(docList.get(i).getDocPasswd().toString());
				bWriter.write(";");
				bWriter.write(docList.get(i).getDoctorId());
				bWriter.newLine();
			}
			System.out.print("Saved");
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


	private  void login()
	{
		SurgeryDesugn test = new SurgeryDesugn();
		test.login();
	
	}





	public static boolean loginCheck(String name,String password) {

		int x =  docList.size();
		int i =0;
		for (i = 0 ; i <= x-1; i++){
			if (docList.get(i).getDoctorName().toString().equals(name) && (docList.get(i).getDocPasswd().toString().equals(password))){
				System.out.println("Login Ok");
				return true;
			}
		}
		System.out.println("Something is wrong");
		return false;
	} 

	private static void listPatients(){

		int x =  docList.size();
		for (int i =0;i <= x-1;i++) // Start of For Loop
		{
			System.out.print(docList.get(i).getDoctorName());// Gets Person Information
			System.out.print("\n----- patiets -----\n");
			System.out.print(docList.get(i).getPatient().get(x).toString());
			for (Patient s : 	docList.get(1).getPatient()) {  
				System.out.print(s.toString());  //Display Flims for actor
			}  
		}
	//	frame.repaint();
	}

	private static void restoreSystemFromFile() { 

	} 

	public void actionPerformed(ActionEvent e) {

		// Menu item actions
		String command = e.getActionCommand();

		if (command.equals("Log Out")) {
		//	frame.dispose();
		//	LoginFrame.setVisible(true);

		} else if (command.equals("Login")) {
			// Open menu item action


		}else if (command.equals("Add Patient")) {
			// Open menu item action
			Doctor.addPatient(null);
		}
		else if (command.equals("Update Patient")) {
			// Open menu item action
			Doctor.updatePatient();
		}

		else if (command.equals("Add Doctor")) {
			addDoctor();

		}	else if (command.equals("Refresh List")) {
			addList();

		}
		else if (command.equals("Restore")) {
			// Save menu item action
			System.out.println("Save menu item clicked");
		}
		else if (command.equals("BackUp")) {
			// Save menu item action
			saveSystemToFile() ;
		}
	}

	private JMenuItem makeMenuItem(String name) {
		JMenuItem m = new JMenuItem(name);
		m.addActionListener(this);
		return m;
	}
} 





