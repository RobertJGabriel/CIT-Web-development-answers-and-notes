package view;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import model.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Database myDatabase = new Database();
	
	
	private ActionHandler myActionListener = new ActionHandler();
	private JPanel contentPane;
	private JButton btnAddPatient;
	private JButton btnAddHistory;
	private JButton btnAddInvoice;
	private JButton btnAddProcedure;
	private JButton btnViewData;
	private JButton btnSaveData;
	private AddPerson personField;
	private AddHistory historyField;
	private AddInvoice invoiceField;
	private AddProcedure procedureField;
	private ViewData viewDataField;
	private SaveData saveDataField;
	private JButton btnSubmit;
	private JButton btnSave;
	private JButton btnTerminate;
	private JButton btnLoad;
	private SerializeJavaObjects_MySQL sqlAction= new SerializeJavaObjects_MySQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][grow]", "[grow][grow][][]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("", "[]", "[][][][][][]"));
		
		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(myActionListener);
		panel.add(btnAddPatient, "flowy,cell 0 0,growx");
		
		btnAddHistory = new JButton("Add History");
		panel.add(btnAddHistory, "cell 0 0,growx");
		btnAddHistory.addActionListener(myActionListener);
		
		btnAddInvoice = new JButton("Add Invoice");
		panel.add(btnAddInvoice, "cell 0 1,growx");
		btnAddInvoice.addActionListener(myActionListener);
		
		btnAddProcedure = new JButton("Add Procedure");
		panel.add(btnAddProcedure, "flowy,cell 0 2,growx");
		btnAddProcedure.addActionListener(myActionListener);
		
		btnViewData = new JButton("View Data");
		panel.add(btnViewData, "cell 0 3,grow");
		btnViewData.addActionListener(myActionListener);
		
		btnSaveData = new JButton("Working Method");
		panel.add(btnSaveData, "cell 0 4,growx");
		btnSaveData.addActionListener(myActionListener);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 2 1,grow");
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setLayout(new CardLayout(0, 0));
		personField = new AddPerson();
		panel_1.add(personField);
		historyField = new AddHistory();
		panel_1.add(historyField);
		historyField.setVisible(false);
		invoiceField = new AddInvoice();
		panel_1.add(invoiceField);
		invoiceField.setVisible(false);
		procedureField = new AddProcedure();
		panel_1.add(procedureField);
		procedureField.setVisible(false);
		viewDataField = new ViewData();
		panel_1.add(viewDataField);
		viewDataField.setVisible(false);
		saveDataField = new SaveData();
		panel_1.add(saveDataField);
		viewDataField.patientNameCB.addActionListener(myActionListener);
		viewDataField.btnRemovePatient.addActionListener(myActionListener);
		viewDataField.btnEditPhone.addActionListener(myActionListener);
		viewDataField.btnEditAddress.addActionListener(myActionListener);
		viewDataField.btnRemoveInvoice.addActionListener(myActionListener);
		viewDataField.btnRemoveHistory.addActionListener(myActionListener);
		
		
		
		btnSubmit = new JButton("Submit");
		contentPane.add(btnSubmit, "flowx,cell 2 2");
		btnSubmit.addActionListener(myActionListener);
		contentPane.getRootPane().setDefaultButton(btnSubmit);
		
		btnSave = new JButton("Save");
		contentPane.add(btnSave, "cell 2 2");
		btnSave.addActionListener(myActionListener);
		
		btnTerminate = new JButton("Terminate");
		contentPane.add(btnTerminate, "cell 2 2");
		
		btnLoad = new JButton("Load");
		contentPane.add(btnLoad, "cell 2 2");
		btnLoad.addActionListener(myActionListener);
		btnTerminate.addActionListener(myActionListener);
		saveDataField.setVisible(false);
		
		
		
		
	}
	private class ActionHandler implements ActionListener{

	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddPatient){
				personField.setVisible(true);
				historyField.setVisible(false);
				invoiceField.setVisible(false);
				procedureField.setVisible(false);
				viewDataField.setVisible(false);
				saveDataField.setVisible(false);
			}
			else if(e.getSource()==btnAddHistory){
				personField.setVisible(false);
				historyField.setVisible(true);
				invoiceField.setVisible(false);
				procedureField.setVisible(false);
				viewDataField.setVisible(false);
				saveDataField.setVisible(false);
				
			}
			else if(e.getSource()==btnAddInvoice){
				invoiceField.clearComboLists();
				invoiceField.repaint();
				invoiceField.setProceduresCombos(myDatabase.getProcedureList());
				personField.setVisible(false);
				historyField.setVisible(false);
				invoiceField.setVisible(true);
				procedureField.setVisible(false);
				viewDataField.setVisible(false);
				saveDataField.setVisible(false);
						}
			else if(e.getSource()==btnAddProcedure){
				personField.setVisible(false);
				historyField.setVisible(false);
				invoiceField.setVisible(false);
				procedureField.setVisible(true);
				viewDataField.setVisible(false);
				saveDataField.setVisible(false);
			}
			else if(e.getSource()==btnViewData){
				viewDataField.clearComboList();
				viewDataField.repaint();
				viewDataField.setComboLists(myDatabase.getPatientList());
				personField.setVisible(false);
				historyField.setVisible(false);
				invoiceField.setVisible(false);
				procedureField.setVisible(false);
				viewDataField.setVisible(true);
				saveDataField.setVisible(false);
			}
			else if(e.getSource()==btnSaveData){
				personField.setVisible(false);
				historyField.setVisible(false);
				invoiceField.setVisible(false);
				procedureField.setVisible(false);
				viewDataField.setVisible(false);
				saveDataField.setVisible(true);
			}
			else if(e.getSource()==btnSubmit){
/*Add new patient*/
				if(personField.isVisible()==true){
					myDatabase.addPatient(personField.getName(), personField.getAddress(), personField.getPhone());
					for(int o = 0; o<myDatabase.getPatientList().size();o++)
					personField.clear();
					JOptionPane.showMessageDialog(null, myDatabase.patientList.getLast().getPatientString()+" \nSuccesfully added to the database.");
				}
/*Add patient history*/
				else if(historyField.isVisible()==true){
					int i = myDatabase.findPatient(historyField.getPatientName(),historyField.getPatientId());
					if(i==-1||(historyField.getPatientName().equals("")||historyField.getPatientName().equals("") || historyField.getPatientId()<-1))
						JOptionPane.showMessageDialog(null, "Patient not Found. Check all entered information. \n Check all data entered and try again");
					else	
						myDatabase.addPatientHistory(i, historyField.getConditionName(), historyField.getDate(), historyField.getMedication());
						historyField.clear();
						JOptionPane.showMessageDialog(null, myDatabase.patientList.get(i).getP_History().getLast().getHistory());
				}
/*Add patient Invoice*/
				else if(invoiceField.isVisible()==true){
					//*look for patient in Database object.
					int i = myDatabase.findPatient(invoiceField.getPatientName(),invoiceField.getPatientId());
					double cost=0;
					int j;
					int g;
					LinkedList<Procedure> procList = new LinkedList<Procedure>();
					if(i==-1)//*if findPatient method returned -1 means it didn't find the patient
						JOptionPane.showMessageDialog(null, "Patient not Found.");
					else//* else patient was found
					{
						for(j = 0; j<invoiceField.getProcedures().size();j++)//* go trough the list of procedures from invoice field
						{
							
							for(g=0;g<myDatabase.getProcedureList().size();g++)//*go trough the list of procedures from database object
							{
								
								//*check if string from the text field in invoiceField pane matches name of procedure in database
								if(invoiceField.getProcedures().get(j).equals(myDatabase.getProcedureList().get(g).getProcName()+":"+myDatabase.getProcedureList().get(g).getProcCost()))
								{
									procList.add((myDatabase.procList.get(g)));//* add procedure to procList
									cost += myDatabase.getProcByNum(g).getProcCost();//* add price of procedure to total cost
								}
								else if(invoiceField.getProcedures().get(j).equals(""))
									{
								break;}
								else if(invoiceField.getProcedures().get(0)==null)
								{
									JOptionPane.showMessageDialog(null, "You have to add at leas one procedure!");}
								
									}
							}
								
						}
						Invoice addingInvoice=new Invoice(0, cost, invoiceField.getDate(), procList, invoiceField.getPaid());
						
						if(myDatabase.addPatientInvoice(i, addingInvoice)==true){
							historyField.clear();
							JOptionPane.showMessageDialog(null, myDatabase.getPatientList().get(i).getP_Invoice().getLast().getInvoice() + "Succesfully added.");
						}
						else{
							
							JOptionPane.showMessageDialog(null, "Couldn't add new invoice. Please try again.");}
						
					}
				else if(procedureField.isVisible()==true){
					if(myDatabase.findProcedure(procedureField.getProcedureName())!=-1)
					{
						JOptionPane.showMessageDialog(null, "Procedure already existing.");
					}
					else if(!(procedureField.getProcedureName().equals("")&& (procedureField.getProcedureCost()<0.00&&procedureField.getProcedureCost()>1000000.00)))
					{
						myDatabase.addProcedure(procedureField.getProcedureName(), procedureField.getProcedureCost());
						procedureField.clear();
						JOptionPane.showMessageDialog(null, myDatabase.getProcedureList().getLast() + "\nSuccesfully added.");
					}
					
					else
						JOptionPane.showMessageDialog(null, "Cannot add procedure, Please ensure all information in the fields is correct.");
				}
				else if(viewDataField.isVisible()==true)
				{
					
				}
			}
			else if(e.getSource()==btnTerminate)
				System.exit(0);
			else if(e.getSource()==btnLoad)
			{
				if(saveDataField.chckbxSaveToFile.isSelected())
				{
					try{
						String file = JOptionPane.showInputDialog("Please enter file path:", "C:\\Users\\junysz\\Desktop\\myDatabase.ser");
						FileInputStream fileStream = new FileInputStream(file);
						ObjectInputStream os = new ObjectInputStream(fileStream);
						myDatabase = (Database)os.readObject();
						os.close();
						JOptionPane.showMessageDialog(null, "File: '" + file + "' fully read in", "Success", DO_NOTHING_ON_CLOSE);
						}
						catch(Exception exc){
							JOptionPane.showMessageDialog(null, exc.getMessage());
						}
						myDatabase.setPatientIndex((myDatabase.getPatientList().getLast().getPatientNum())+1);
						viewDataField.clearComboList();
						viewDataField.repaint();
						viewDataField.setComboLists(myDatabase.getPatientList());
				}
				else if(saveDataField.chckbxSaveToDatabase.isSelected())
				{
					try{
						System.out.println("adfghjn");
						myDatabase.patientList=	sqlAction.getPatients();
						System.out.println("asdf");
						myDatabase.setProceduresList(sqlAction.getProcedureList());
						System.out.println("aqwe");
						myDatabase.setIndexes(sqlAction.getIndexes());
						System.out.println("agfgh");//working fine as fares here
						for(int i = 0;i<myDatabase.patientList.size();i++)
						{
							
							myDatabase.patientList.get(i).setHistory(sqlAction.getpHistory(myDatabase.patientList.get(i).getPatientNum()));
							myDatabase.patientList.get(i).setInvoices(sqlAction.getpInvoice(myDatabase.patientList.get(i).getPatientNum()));
							for(int j = 0 ; j < myDatabase.getPatientInvoices(i).size();j++)
							{
								myDatabase.patientList.get(i).getP_Invoice().get(j).setProcedures(sqlAction.getInvoiceProcedure(myDatabase.patientList.get(i).getP_Invoice().get(j).getInvoiceNum()));
							}
						}
						
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select work method!!");
					personField.setVisible(false);
					historyField.setVisible(false);
					invoiceField.setVisible(false);
					procedureField.setVisible(false);
					viewDataField.setVisible(false);
					saveDataField.setVisible(true);
				}
				
			}
			else if(e.getSource()==btnSave)
			{
				if(saveDataField.chckbxSaveToFile.isSelected())
				{
				try{ 
					FileOutputStream fileStream=new FileOutputStream(JOptionPane.showInputDialog("Please enter file path:", "C:\\Users\\junysz\\Desktop\\myDatabase.ser"));
					ObjectOutputStream os = new ObjectOutputStream(fileStream);
					os.writeObject(myDatabase);
					os.close();
					
					}
					catch(Exception exc){
						JOptionPane.showMessageDialog(null, exc.getMessage());
					}
				}
				else if(saveDataField.chckbxSaveToDatabase.isSelected())
				{
					try
					{
						System.out.println("trying......");
						sqlAction.flushDatabase();
						sqlAction.insertIndexes(myDatabase.getIndexes());
						sqlAction.insertProcedures(myDatabase.getProcedureList());
						sqlAction.insertPatients(myDatabase.getPatientList());
						for(int i = 0;i<myDatabase.getPatientList().size();i++)
						{
							System.out.println("inserting patient history");
							sqlAction.insertHistory(myDatabase.getPatientList().get(i).getP_History(), myDatabase.getPatientList().get(i).getPatientNum());
							System.out.println("inserting patient invoices");
							sqlAction.insertInvoice(myDatabase.getPatientList().get(i).getP_Invoice(), myDatabase.getPatientList().get(i).getPatientNum());
							System.out.println("inserting patient invoice procedures");
							for(int j =0; j<myDatabase.getPatientList().get(i).getP_Invoice().size();j++)
								sqlAction.insertInvoiceProceduresRelation(myDatabase.getPatientList().get(i).getP_Invoice().get(j).getInvoiceProcList(), myDatabase.getPatientList().get(i).getP_Invoice().get(j).getInvoiceNum());
							System.out.println();
						}
						System.out.println("done");
					}
					catch(SQLException e1){
						JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select work method!!");
					personField.setVisible(false);
					historyField.setVisible(false);
					invoiceField.setVisible(false);
					procedureField.setVisible(false);
					viewDataField.setVisible(false);
					saveDataField.setVisible(true);
				}
				
			}
			else if(e.getSource()==viewDataField.patientNameCB)
			{
				if(viewDataField.patientNameCB.getSelectedIndex()<0 || viewDataField.patientNameCB.getSelectedIndex()>myDatabase.getPatientList().size())
				{
					System.out.println("Index out of bound!!");
				}
				else
				{
				int i=viewDataField.patientNameCB.getSelectedIndex();
				viewDataField.patientID=i;
				viewDataField.patientAddressTF.setText(myDatabase.getPatientList().get(i).getPatientAddress());
				viewDataField.patientPhoneTF.setText(myDatabase.getPatientList().get(i).getPatientPhone());
				viewDataField.patientName=myDatabase.getPatientList().get(i).getPatientName();
				viewDataField.setTables(myDatabase.getPatientList().get(i).getP_Invoice(), myDatabase.getPatientList().get(i).getP_History());
				viewDataField.repaint();
				
				}
				
			}
			else if(e.getSource()==viewDataField.btnRemovePatient)
			{
				if(viewDataField.patientNameCB.getSelectedIndex()<0)
				{
					JOptionPane.showMessageDialog(null, "Please select patient!");
				}
				else
				{
					int optionConfirm = JOptionPane.showConfirmDialog(null, "Are You sure You want to delete selected patient?");
					System.out.println(optionConfirm);
					if (optionConfirm==0)
					{
						myDatabase.patientList.remove(viewDataField.patientID);
						viewDataField.clearComboList();
						viewDataField.repaint();
						viewDataField.setComboLists(myDatabase.getPatientList());
					}
				}	
			}
			else if(e.getSource()==viewDataField.btnEditAddress)
			{
				String newAddress = JOptionPane.showInputDialog(null, "Please enter new address", myDatabase.getPatientList().get(viewDataField.patientID).getPatientAddress());
				myDatabase.getPatientList().get(viewDataField.patientID).setAddress(newAddress);
				viewDataField.clearComboList();
				viewDataField.repaint();
				viewDataField.setComboLists(myDatabase.getPatientList());
			}
			else if(e.getSource()==viewDataField.btnEditPhone)
			{
				String newPhone = JOptionPane.showInputDialog(null, "Please enter new phone", myDatabase.getPatientList().get(viewDataField.patientID).getPatientPhone());
				myDatabase.getPatientList().get(viewDataField.patientID).setPhone(newPhone);
				viewDataField.clearComboList();
				viewDataField.repaint();
				viewDataField.setComboLists(myDatabase.getPatientList());
			}
			else if(e.getSource()==viewDataField.btnRemoveInvoice)
			{
				int optionConfirm = JOptionPane.showConfirmDialog(null, "Are You sure You want to delete selected invoice?");
				if (optionConfirm==0)
				{
					myDatabase.getPatientList().get(viewDataField.patientID).getP_Invoice().remove(viewDataField.invoiceTbl.getSelectedRow());
					viewDataField.clearComboList();
					viewDataField.repaint();
					viewDataField.setComboLists(myDatabase.getPatientList());
				}
			}
			else if(e.getSource()==viewDataField.btnRemoveHistory)
			{
				int optionConfirm = JOptionPane.showConfirmDialog(null, "Are You sure You want to delete selected history?");
				if (optionConfirm==0)
				{
					try{
					myDatabase.getPatientList().get(viewDataField.patientID).getP_History().remove(viewDataField.historyTbl.getSelectedRow());
					viewDataField.clearComboList();
					viewDataField.repaint();
					viewDataField.setComboLists(myDatabase.getPatientList());
					}
					catch(IndexOutOfBoundsException ioobe){
						JOptionPane.showMessageDialog(null, "Nothing to remove. make sure correct value is selected!", ioobe.getMessage(), ERROR);
					}
				}
			}
		}
		
	}

}
