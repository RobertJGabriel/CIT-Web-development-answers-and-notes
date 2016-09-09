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

/**
 * WriteRead Class for the Y2S2Project
 * @author Libor Kampas
 * @version 1.4
 * @date 20/02/2014
 */

//http://www.wikihow.com/Serialize-an-Object-in-Java
//http://www.javapractices.com/topic/TopicAction.do?Id=57
//https://www.google.ie/search?q=serialize+object+in+java&oq=serialize+object+in+java&aqs=chrome..69i57j69i60j0l3.10849j0j4&sourceid=chrome&ie=UTF-8#q=serialize+object+in+java+to+file
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
