import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class surgey implements java.io.Serializable {

	public static int id = -1,Patientid;
	static ArrayList<Doctor> docList = new ArrayList<Doctor>(); 
	static final String fileName = "doctorList.ser";    //filename where all data is stored
	private static ArrayList<Patient>  patientt = new ArrayList();
	public static Date date1;
	static Date date2;

	public static void main(String[] args) { 


		//Connects to the Database and loads the information 	
		DBConnect connect = new DBConnect(); 
		connect.load();

		//Creates the new window for the first time if logged in and creates main window
		if (id == -1){@SuppressWarnings("unused") surgey Surgey = new surgey();}

		//  restoreSystemFromFile();

	}


	@SuppressWarnings("unused")
	public  surgey() { 

		//New Surgey Window is created	
		SurgeyDesigner Surgey = new SurgeyDesigner();
	}

	public static void addDoctor(String nameText, String passwordField1) { 

		//Adds a new Doctor to the doctor List.	
		docList.add(new Doctor(nameText,passwordField1));

	} 

	//private static void updateDoctor() { // Needs to be done} 

	public  static void saveSystemToFile() { 

		//Saves Arrays and Objects to a file
		WriteRead writeread = new WriteRead();      //instance of created WriteRead class   
		writeread.writeFile(fileName, docList);		// Paces the fileName and Doclist

	} 

	public static boolean loginCheck(String name,String password) {

		// Checks if the user Input is the the Array list for Doctors	
		int x =  docList.size(); 			//Gets the amount of Doctors
		for (int i = 0 ; i <= x-1; i++){	
			if (docList.get(i).getDoctorName().toString().equals(name) && (docList.get(i).getDocPasswd().toString().equals(password))){
				System.out.println("Login Ok");
				id = i;
				return true;
			}
		}
		return false;
	} 


	public static void restoreSystemFromFile(){

		//Restores from file and places to  ArrayList	
		ArrayList<Doctor> tempDoctorList = new ArrayList<Doctor>();    // Makes a Shallow copy of Doctors
		WriteRead writeread = new WriteRead();      //instance of created WriteRead class                   
		docList.clear();
		tempDoctorList = writeread.readFile(fileName);

		//doctor addition to deep copy and print out
		for (int i = 0; i < tempDoctorList.size();i++){
			docList.add(tempDoctorList.get(i));
			docList.get(i).print();
			System.out.println( docList.get(i).getPatient().size());
			for (int k = 0; k < docList.get(i).getPatient().size();k++){
				docList.get(i).getPatient().get(k).print();
			}
		}
		//Must be done once to set the correct counter after restart of the program, otherwise the counter starts always from 0
		Doctor.instanceCounter = docList.size();

	}
	public static void addPatient(String nameText, String addressText,String  phoneText ,Date  patientDOB ){

		//Adds Patient to Current logged in Doctor
		docList.get(id).addPatient(nameText, addressText, phoneText , patientDOB);

	}



	public static  void printStuff(){

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
				bWriter.write("Doctor Name : " + docList.get(id).getDoctorName().toString());
				bWriter.newLine();

				patientt = listPatients();

				for (int i = 0; i < patientt.size();i++){
					bWriter.write("\n" + "************* Patient *************"+ "\n");
					bWriter.write("\n Name : " + patientt.get(i).getPName() + "");
					bWriter.write("\n Address : " + patientt.get(i).getPAddress() + "");
					bWriter.write("\n Phone : " + patientt.get(i).getPPhone()+ "\n");


					for (int k = 0; k < patientt.get(i).getPatientHistory().size();k++){
						bWriter.write("---------" + "History ----------"+ "\n");
						bWriter.newLine();
						bWriter.write( "\t"  +  patientt.get(i).getPatientHistory().get(k).getMedicine() + "\n");
						bWriter.newLine();
						bWriter.write( "\t"  +  patientt.get(i).getPatientHistory().get(k).getProcedure() + "\n");
						bWriter.newLine();
						bWriter.write( "\t"  +  patientt.get(i).getPatientHistory().get(k).getDescription() + "\n");
						bWriter.newLine();
						bWriter.write( "\t"  +  patientt.get(i).getPatientHistory().get(k).getDate().toString() + "\n");
					}
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


	public static ArrayList<Patient>  listPatients(){

		ArrayList<Patient> allPatientsSorted = new ArrayList<Patient>();
		allPatientsSorted = docList.get(id).sortPatients(docList.get(id).getPatient());

		//Lists the Patients and Displays them
		String []  patients = new String [docList.get(id).getPatient().size()];

		for (int i = 0; i < docList.get(id).getPatient().size(); i++){
			patients[i] = 	allPatientsSorted.get(i).getPName();
		}

		mainWindow.setPatientListModel(patients);
		return allPatientsSorted;
	}

	public static ArrayList<PatientHistory> sortPatientsByDate(Date date12, Date date22){
		ArrayList<PatientHistory> allHistorySorted = new ArrayList<PatientHistory>();
		Patient patient = new Patient();
		allHistorySorted = patient.sortPatients(getAllHistory());
		System.out.print(allHistorySorted);
		return allHistorySorted;
	}




	public static ArrayList getAllHistory(){

		ArrayList<PatientHistory> allHistory = new ArrayList<PatientHistory>();
		//allPatients.clear();
		for (int i = 0; i < docList.size();i++){
			for (int k = 0; k < docList.get(i).getPatient().size();k++){
				System.out.print(docList.get(i).getPatient().size());
				for (int l = 0; l < docList.get(i).getPatient().get(k).getPatientHistory().size(); l++){
					System.out.print(docList.get(i).getPatient().get(k).getPatientHistory().size());
					if(date1.compareTo(docList.get(i).getPatient().get(k).getPatientHistory().get(l).getVisitDate()) * docList.get(i).getPatient().get(k).getPatientHistory().get(l).getVisitDate().compareTo(date2) > 0)

						allHistory.add(docList.get(i).getPatient().get(k).getPatientHistory().get(l));
				}
			}
		}    	
		return allHistory;
	}


	public static ArrayList getAllPatients(){
		ArrayList<Patient> allPatients = new ArrayList<Patient>();
		int distinct = 0;

		//allPatients.clear();
		for (int i = 0; i < docList.size();i++){
			for (int k = 0; k < docList.get(i).pList.size();k++){
				for (int l = 0; l < docList.get(i).pList.get(k).getPatientHistory().size(); l++){
					allPatients.add(docList.get(i).pList.get(k));
					distinct = 1;
				}
				distinct = 0;
			}
		}
		return allPatients;
	}

	public static void listHistory(int pid){

		// Lists the Patients History and Displays them
		String []  HistoryList = new String [surgey.docList.get(surgey.id).pList.get(pid).myHistory.size()];
		System.out.println(surgey.docList.get(surgey.id).pList.get(pid).myHistory.size());
		for (int i = 0; i < surgey.docList.get(surgey.id).pList.get(pid).myHistory.size(); i++){
			HistoryList[i] = surgey.docList.get(surgey.id).pList.get(pid).myHistory.get(i).toString(); 
		}
		mainWindow.setPatientListHistory( HistoryList);
	}

}