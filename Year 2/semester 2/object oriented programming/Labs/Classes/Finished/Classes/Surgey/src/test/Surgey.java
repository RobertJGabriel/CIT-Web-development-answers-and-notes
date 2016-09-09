
package test;
/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
import java.awt.*;
import javax.swing.*;
import javax.swing .JFrame;

import java.util.ArrayList; 
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Surgey { 

	private int surgeryId ; 
	private String surgeryAdd; 
	private static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	static Scanner keyboard = new Scanner (System.in);// Scanner for Keyboard input.


	public static void main(String[] args) { 

				swing first = new swing();
				first.setTitle("I done");
				first.setSize(300,200);
				first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				first.setVisible(true);
		
		for (int x = 0 ; x <= 2; x++){

			addDoctor();
		} 
	}


	private static void addDoctor() { 
		String name,password ;

		do{// Start of Do while Loop 
			keyboard.nextLine();
			System.out.print("Please Enters Doctor's Name : ");
			name = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.


		do{// Start of Do while Loop
			System.out.print("Please Enter "+ name + "'s Password : ");
			password = keyboard.nextLine();
		}while(!name.matches("^[\\p{L} .'-]+$"));// Validates input and checks that only string is input.
		docList.add(new Doctor(name, password));// Adds doctors to class
		saveSystemToFile() ;
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
	private static void restoreSystemFromFile() { 

	} 
	private static void login() { 

	} 

	private static void display(){

		int x =  docList.size();
		for (int i =0;i <= x-1;i++) // Start of For Loop
		{
			docList.get(i).print();// Gets Person Information
		}


	}
} 