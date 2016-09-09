package program;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class WriteRead {
	ArrayList<Doctor> recoveredDoctorList;
	String fileName;
	Object object;
	
	public WriteRead(){
		fileName = "fileName.ser";
		recoveredDoctorList = new ArrayList <Doctor> ();
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
	
	public ArrayList<Doctor> readFile(String fileName){
	//	ArrayList<Doctor> recoveredDoctorList;/* = new ArrayList <Doctor> ();*/
		try{
			      InputStream file = new FileInputStream(fileName);
			      InputStream buffer = new BufferedInputStream(file);
			      ObjectInputStream input = new ObjectInputStream (buffer);
			   //de-serialize the List

			      recoveredDoctorList = (ArrayList )input.readObject();
			      input.close();
			      //display its data
			     /* for(Doctor doctorList: recoveredDoctorList){
			        System.out.println("Recovered Doctor: " + doctorList);
			      }*/
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
