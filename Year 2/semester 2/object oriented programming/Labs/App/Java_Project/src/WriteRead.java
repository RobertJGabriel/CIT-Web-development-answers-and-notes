import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class WriteRead {
	ArrayList<Doctor> recoveredDoctorList;
	String fileName;
	Object object;
	private ArrayList<PatientHistory>  patientHistory = new ArrayList();
	public WriteRead(){
		fileName = "fileName.ser";
		recoveredDoctorList = new ArrayList <Doctor>();
	}







	public void dateReport(Date date1, Date date2){


		//Prints the files.
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null); 
		if (returnVal == JFileChooser.APPROVE_OPTION) { 
			File file2  = fc.getSelectedFile(); 

			BufferedWriter bWriter = null;
			try{
				File file = new File(file2 + ".txt");
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter fWriter = new FileWriter(file);
				bWriter = new BufferedWriter(fWriter);

				// Goes through each lecturer and writes its details
				bWriter.write("History :D");
				bWriter.newLine();

				patientHistory = surgey.sortPatientsByDate(date1, date2);


				for (int i = 0; i < patientHistory.size();i++){
					bWriter.write(patientHistory.get(i).toString());


				}

				System.out.print("Saved");
			}catch(IOException ex){
				JOptionPane.showMessageDialog(null, "Error");
			}finally{
				try{
					// Clears Memory and Closes Writer
					if(bWriter != null){
						bWriter.flush();
						bWriter.close();
					}
				}catch(IOException ex){
					JOptionPane.showMessageDialog(null, "Error");

				}
			}
		}    	
	}

	public void writeFile(String fileName, ArrayList <Doctor> object){
		try {
			OutputStream file = new FileOutputStream(fileName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(object);
			output.close();
		}  
		catch(Exception ex){
			System.out.println("rubbish");
			//fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
		}
	}


	public void writeToFile(ArrayList <Doctor> object){
		try {
			OutputStream file = new FileOutputStream("report.txt");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(object);
			output.close();
		}  
		catch(Exception ex){
			System.out.println("rubbish");
			//fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
		}
	}




	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Doctor> readFile(String fileName){
		//	ArrayList<Doctor> recoveredDoctorList;/* = new ArrayList <Doctor> ();*/
		try{
			InputStream file = new FileInputStream(fileName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInputStream input = new ObjectInputStream (buffer);
			//De-serialize the List

			recoveredDoctorList = (ArrayList )input.readObject();
			input.close();
		}
		catch(ClassNotFoundException ex){
			// fLogger.log(Level.SEVERE, "Cannot perform input. Class not found.", ex);
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());ex.printStackTrace();
			// fLogger.log(Level.SEVERE, "Cannot perform input.", ex);
		}
		return recoveredDoctorList;

	}	
}
