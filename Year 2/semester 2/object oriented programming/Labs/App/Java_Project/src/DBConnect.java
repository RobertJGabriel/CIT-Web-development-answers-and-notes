import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class DBConnect {

	private Connection con;
	private Statement st;
	private ResultSet rs;

	public DBConnect(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctors","root","");
			st = con.createStatement();

		}catch(Exception ex){

			System.out.print("error:" + ex);

		}
	}











	public void insertData(String nameText, String passwordField1){

		String test  = "'" + nameText + "'"+"," + "'" +  passwordField1 +"'" ;
		try {

			String query = "INSERT INTO	doctor ( name ,password) VALUES (" + test + ")" ;
			st.executeUpdate(query);
			System.out.println("Records from database");

		}catch (Exception ex){

			System.out.print(ex);
		}
	}



	public void insertPatientData(int pId, String nameText, String addressText, String phoneText, java.util.Date patientDOB){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = formatter.format( patientDOB);
		Date date = Date.valueOf(reportDate);

		int id = surgey.id ;
		System.out.print(id);
		String test  = "'" + pId + "'"    + ",'" + nameText + "'"    +    ","    + "'" + addressText +"'"   +    ","    +   "'" + phoneText +"'"   +    ","    +   "'" + date +"'"+    ","    +   "'" + id +"'" ;
		try {

			String query = "INSERT INTO	patient ( id,name ,address,phoneText,date,doctorid)  VALUES (" + test+     ")" ;
			st.executeUpdate(query);
			System.out.println("Records from database");

		}catch (Exception ex){
			System.out.print(ex);
		}
	}



	@SuppressWarnings("unused")
	public void updateData(int number, int id2, String nameText, String addressText, String phoneText, java.util.Date patientDOB){
		int id = surgey.id ;
		try {

			String query = "UPDATE	patient SET name='"+ nameText +  "', address='" + addressText + "', phoneText='" + phoneText + "' WHERE doctorid="+ id2 +" AND id=" + number;
			st.executeUpdate(query);

			System.out.println("Update from database");

		}catch (Exception ex){

			System.out.print(ex);
		}
	}	

	public void deleData(int id3, int number){

		try {
			String query = "DELETE FROM	patient WHERE doctorid="+ id3 +" AND id=" + number;

			st.executeUpdate(query);
			System.out.println("Removed from database");



		}catch (Exception ex){

			System.out.print(ex);
		}

	}


	public void getData(){
		try {
			String query = "select * from doctor";

			rs = st.executeQuery(query);
			System.out.println("Records from database");
			while (rs.next()){
				String name = rs.getString("name");
				String age = rs.getString("password");
				System.out.println("Name :" + name + "Age :" + age);

			}

		}catch (Exception ex){

			System.out.print(ex);
		}


	}

	public void insertHistory(int id, int selectedIndex, String patientMedText, String patientDesText, String patientProdText) {

		String test  = "'" + id + "'"    +    ","    + "'" + selectedIndex +"'"   +    ","    +   "'" + patientMedText +"'"   +    ","    +   "'" + patientDesText +"'"+    ","    +   "'" + patientProdText +"'" ;
		try {
			String query = "INSERT INTO	history ( DoctorId ,patientId,patientMed,patientDes,patientProd)  VALUES (" + test+     ")" ;

			st.executeUpdate(query);
			System.out.println("Records from database");

		}catch (Exception ex){
			System.out.print(ex);
		}

	}





	@SuppressWarnings("unused")
	public void between(java.util.Date date1, java.util.Date date2)  {


		java.sql.Date DateStart  = new java.sql.Date(date1.getYear(), date1.getMonth(),date1.getDay());

		java.sql.Date DateEnd = new java.sql.Date(date2.getYear(), date2.getMonth(),date2.getDay());


		String startString  = "'"  + DateStart  + "'"  ;
		String endString  =  "'"+ DateEnd + "'"   ;
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

				try {
					String query = "SELECT * FROM patient WHERE date BETWEEN " + startString + " AND " + endString ;
					System.out.print(query);
					rs = st.executeQuery(query);
					System.out.println("Records from database");
					while (rs.next()){

						bWriter.write("\n" + "************* Patient *************"+ "\n");
						bWriter.write("\n Name : " + rs.getString("name").toString() + "");
						bWriter.write("\n Address : " + rs.getString("address").toString() + "");
						bWriter.write("\n Phone : " + rs.getString("phoneText").toString()+ "\n");
						bWriter.write("\n Date : " + rs.getString("date").toString()+ "\n");
						bWriter.write("\n Doctor Id : " + rs.getString("doctorid").toString()+ "\n");

					}

				}catch (Exception ex){

					System.out.print(ex);
				}
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
		}}

















































	@SuppressWarnings("unused")
	public void load()  {

		try {
			// Step 1: Load the JDBC driver. jdbc:mysql://localhost:3306/travel
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2: Establish the connection to the database.
			String url = "jdbc:mysql://localhost:3306/doctors";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();

			int test =0 ;
			ResultSet srs = st.executeQuery("SELECT * FROM doctor");
			while (srs.next()) {
				test = srs.getInt("id");
				Doctor person = new Doctor(srs.getInt("id"),srs.getString("name"),srs.getString("password"));
				surgey.docList.add(person);

			}

			ResultSet srs2 = st.executeQuery("SELECT * FROM patient");
			while (srs2.next()) {

				int test2 = srs2.getInt("doctorid");
				System.out.println(srs2.getString("name"));
				Patient person2 = new Patient(srs2.getInt("id"),srs2.getString("name"),srs2.getString("address"),srs2.getString("phoneText"),srs2.getDate("date"));
				surgey.docList.get(test2).pList.add(person2);
			}

			ResultSet srs3 = st.executeQuery("SELECT * FROM history");
			while (srs3.next()) {

				int test3 = srs3.getInt("DoctorId");
				int test4 = srs3.getInt("patientId");
				System.out.println(test3);

				PatientHistory person3 = new PatientHistory(srs3.getInt("patientId"),srs3.getInt("DoctorId"),srs3.getString("patientMed"),srs3.getString("patientDes"),srs3.getString("patientProd"));
				surgey.docList.get(test3).pList.get(test4).myHistory.add(person3);
			}

		} catch (Exception e) {
			System.err.println("Got an exception ");
		}
	}
}
