package javaProject;
import java.sql.*;
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
	
	public void insertData(){

		try {
			String query = "INSERT INTO	doctor ( name ,password)  VALUES ('BATMAN','BATA')" ;
		
			 st.executeUpdate(query);
			System.out.println("Records from database");
		

			
		}catch (Exception ex){
			
			System.out.print(ex);
		}
		
	}
	public void updateData(){

		try {
			String query = "UPDATE	doctor SET name WHERE id=1" ;
		
			 st.executeUpdate(query);
			System.out.println("Records from database");
		

			
		}catch (Exception ex){
			
			System.out.print(ex);
		}
		
	}	
	
	public void deleData(){

		try {
			String query = "DELETE FROM	doctor WHERE id=3" ;
		
			 st.executeUpdate(query);
			System.out.println("Records from database");
		

			
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

}
