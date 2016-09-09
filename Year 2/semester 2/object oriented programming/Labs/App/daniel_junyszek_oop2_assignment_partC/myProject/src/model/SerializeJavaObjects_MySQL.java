package model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

 
public class SerializeJavaObjects_MySQL {
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   /* These variable values are used to setup
      the Connection object */
 
   static final String URL = "jdbc:mysql://127.0.0.1:3306/mydb";
   static final String USER = "root";
   static final String PASSWORD = "root";
   static final String DRIVER = "com.mysql.jdbc.Driver";
   
   
   
   public Connection getConnection() throws SQLException {
	      Connection con = null;
	      try {
	         Class.forName(DRIVER); 
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	      }
	      catch(ClassNotFoundException e) {
	         System.out.println(e.getMessage());
	         System.exit(-1);
	      }
	      return con;
   }
   public void flushDatabase() throws SQLException
   {
	   try
	   {
	   Statement s = getConnection().createStatement();
       s.execute("TRUNCATE TABLE patient;");
       s.execute("TRUNCATE TABLE history;");
       s.execute("TRUNCATE TABLE invoice;");
       s.execute("TRUNCATE TABLE invoice_procedures;");
       s.execute("TRUNCATE TABLE procedures;");
       s.execute("TRUNCATE TABLE indexes;");
	   }
	   catch(SQLException e)
	   {
		   
	   }
	   
   }
   
   //get list of patients
   public LinkedList<Patient> getPatients() {
	      LinkedList<Patient> pList= new LinkedList<Patient>();
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM patient");
	         while(rs.next()) {
	        	 pList.add(new Patient(rs.getInt("patient_id"),rs.getString("patient_name"), rs.getString("patient_address"), rs.getString("patient_phone")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return pList;
   }
   
   
   
   
   //get declared procedures
   public LinkedList<Procedure> getProcedures(int invoiceId) {
	      LinkedList<Procedure> pList= new LinkedList<Procedure>();
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM procedures WHERE procedure_id=(SELECT procedure_id FROM invoice_procedures WHERE invoice_id="+invoiceId+");");
	         while(rs.next()) {
	        	 pList.add(new Procedure(rs.getInt("procedure_id"), rs.getString("procedure_name"), rs.getDouble("procedure_cost")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return pList;
   }
   public LinkedList<Procedure> getProcedureList() {
	      LinkedList<Procedure> pList= new LinkedList<Procedure>();
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM procedures;");
	         while(rs.next()) {
	        	 pList.add(new Procedure(rs.getInt("procedure_id"), rs.getString("procedure_name"), rs.getDouble("procedure_cost")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return pList;
}
   
   
   //get history for patient
   public LinkedList<History> getpHistory(int patientId) {
	      LinkedList<History> hList= new LinkedList<History>();
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM history WHERE patient_id=" + patientId + ";" );
	         while(rs.next()) {
	        	 hList.add(new History(rs.getInt("history_id"), rs.getString("condition_name"), rs.getDate("date_occoured"), rs.getString("medication_recieved")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return hList;
   }
   
   //get invoices for patient
   public LinkedList<Invoice> getpInvoice(int patientId) {
	      LinkedList<Invoice> iList= new LinkedList<Invoice>();
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM invoice WHERE p_id='" + patientId + "';" );
	         while(rs.next()) {
	        	 iList.add(new Invoice(rs.getInt("i_id"), rs.getDouble("i_amt"), rs.getDate("i_date"), null, rs.getBoolean("i_paid")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return iList;
   }
   //get procedures depending on invoice from database 
   public LinkedList<Procedure> getInvoiceProcedure(int invoiceNum) {
	      LinkedList<Procedure> pList= new LinkedList<Procedure>();
	   ResultSet rs = null;
	   ResultSet rs1 = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs1 = s.executeQuery("SELECT procedure_id FROM invoice_procedures WHERE invoice_id=" + invoiceNum + ";");
	         rs1.next();
	         rs = s.executeQuery("SELECT * FROM procedures WHERE procedure_id = "+rs1.getInt("procedure_id")+";");
	         while(rs.next()) {
	        	 pList.add(new Procedure(rs.getInt("procedure_id"), rs.getString("procedure_name"), rs.getDouble("procedure_cost")));
	         }
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return pList;
   }
   //methods for inserting data into mysqlad
   public void insertPatients(LinkedList<Patient> p) throws SQLException{
	   try{
	   System.out.println("a");
	   Statement statement=getConnection().createStatement();
	   System.out.println("b");
	   for(int i =0; i<p.size();i++)
	   {
		   System.out.println("c");
		   statement.executeUpdate("INSERT INTO patient VALUES('"+p.get(i).getPatientNum() + "', '" + p.get(i).getPatientName() + "', '" + p.get(i).getPatientAddress() + "', '" + p.get(i).getPatientPhone()+"');");
	   }
	   System.out.println("d");
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e.getMessage()+ e.getErrorCode());
	   }
   }
   public void insertHistory(LinkedList<History> h, int pId) throws SQLException{
	   Statement statement=getConnection().createStatement();
	   for(int i =0; i<h.size();i++)
	   {
	   statement.executeUpdate("INSERT INTO history VALUES('"+h.get(i).getHistID() + "', '" + pId + "', '" + h.get(i).getConditionName() + "', '" + sdf.format(h.get(i).getDateOccoured()) + "', '" + h.get(i).getMedicationRecieved() +"');");
	   }
   }
   public void insertInvoice(LinkedList<Invoice> j, int pId) throws SQLException{
	   Statement statement=getConnection().createStatement();
	   

	   for(int i =0; i<j.size();i++)
	   {
		   System.out.println("loop i");
	   statement.executeUpdate("INSERT INTO invoice VALUES('" + j.get(i).getInvoiceAmt() + "', '" + sdf.format(j.get(i).getInvoiceDate()) + "', '" + pId + "', '" + j.get(i).getInvoiceNum() + "', '" + j.get(i).getInvoicePaid() +"');");
	   }
   }
   public void insertInvoiceProceduresRelation(LinkedList<Procedure> p, int iId) throws SQLException{
	   Statement statement=getConnection().createStatement();
	   for(int i =0; i<p.size();i++)
		   
	   statement.executeUpdate("INSERT INTO invoice_procedures VALUES('" + iId + "', '" + p.get(i).getProcNum() + "');");

   }
   public void insertProcedures(LinkedList<Procedure> p) throws SQLException{
	  System.out.println("aaa");
	   Statement statement=getConnection().createStatement();
	   System.out.println("bbb");
	   for(int i =0; i<p.size();i++)
	   {
		   System.out.println("abc");
	   statement.executeUpdate("INSERT INTO procedures VALUES('" + p.get(i).getProcNum() + "', '" + p.get(i).getProcName() + "', '" + p.get(i).getProcCost() + "');");
	   System.out.println("cba");
	   }
   }
   public void insertIndexes(int[] ids) throws SQLException
   {
	   Statement statement=getConnection().createStatement();
	   statement.executeUpdate("INSERT INTO indexes VALUES('" + ids[0] + "', '" + ids[1] + "', '" + ids[2] + "', '" + ids[3] + "', '" + ids[4] + "');");
   }
   public int[] getIndexes()  throws SQLException
   {
	   int[] id = new int[5];
	   ResultSet rs = null;
	      try {
	         Statement s = getConnection().createStatement();
	         rs = s.executeQuery("SELECT * FROM indexes;");
	        	 while(rs.next()) {
	        		 id[0]=rs.getInt("patient_num");
		        	 id[1]=rs.getInt("proc_num");
		        	 id[2]=rs.getInt("hist_id");
		        	 id[3]=rs.getInt("invoice_id");
		        	 id[4]=rs.getInt("inv_proc_id");
	        		 
		        }
	        	 
	      }
	      catch(SQLException e) {
	          System.out.println(e.getMessage());
	          System.exit(-1);
	      }
	      return  id;
   }
   
   
}
