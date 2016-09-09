

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.awt.Font;

public class mainWindow {

	private JFrame frame;
	private JList listbox_1;
	private static  DefaultListModel	listModel = new DefaultListModel();
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	
	private static JList<String> listbox = new JList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(	surgey.docList.get(surgey.id).getDoctorName());




		listbox_1 = new JList(listModel);

		listbox_1.setBounds(19, 67, 137, 299);
	
	
		
		frame.getContentPane().add(listbox_1);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Doctor");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Restore");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgey.restoreSystemFromFile();
				surgey.listPatients();
				JOptionPane.showMessageDialog(null, "System restored.");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmBackUp = new JMenuItem("Back up");
		mntmBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgey.saveSystemToFile();
				JOptionPane.showMessageDialog(null, "System saved."); 
			}
		});
		mnNewMenu.add(mntmBackUp);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Doctor");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDoctor doctors = new addDoctor();

			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgey test = new surgey();
				surgey.id = -1;
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmLogOut);

		JMenu mnPatent = new JMenu("Patent");
		mnPatent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		menuBar.add(mnPatent);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Patient");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appPatient aP = new 	appPatient();
			}
		});
		mnPatent.add(mntmNewMenuItem_2);


		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add History");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addHistory history = new 	addHistory();
			}
		});
		mnPatent.add(mntmNewMenuItem_3);



		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Update Patrient");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePatient uP = new 	updatePatient();
			}
		});
		mnPatent.add(mntmNewMenuItem_4);

		JMenuItem mntmRefreshList = new JMenuItem("Refresh List");
		mntmRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				surgey.restoreSystemFromFile();
				surgey.listPatients();
			}});
		mnPatent.add(mntmRefreshList);

		JMenuItem mntmRemovePatient = new JMenuItem("Remove Patient");
		mntmRemovePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePatient rP = new 	removePatient();
			}
		});
		mnPatent.add(mntmRemovePatient);
		frame.getContentPane().setLayout(null);



		JLabel lblPatientInformation = new JLabel("Patients");
		lblPatientInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblPatientInformation.setBounds(19, 40, 137, 16);
		frame.getContentPane().add(lblPatientInformation);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(253, 302, 19, 20);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBounds(184, 67, 367, 299);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(6, 6, 61, 16);
		panel.add(lblDate);

		final JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 66, 89, 16);
		panel.add(lblDescription);

		JLabel lblMedicine = new JLabel("Medicine");
		lblMedicine.setBounds(6, 117, 132, 16);
		panel.add(lblMedicine);

		JLabel lblProcedure = new JLabel("Procedure");
		lblProcedure.setBounds(6, 171, 79, 16);
		panel.add(lblProcedure);
		
		final JTextArea desInput = new JTextArea();
		desInput.setBounds(16, 94, 345, 20);
		panel.add(desInput);
		
		JTextArea dateInput = new JTextArea();
		dateInput.setBounds(16, 34, 345, 20);
		panel.add(dateInput);
		
		JTextArea medInput = new JTextArea();
		medInput.setBounds(16, 145, 345, 20);
		panel.add(medInput);
		
		JTextArea proInput = new JTextArea();
		proInput.setBounds(16, 206, 345, 20);
		panel.add(proInput);

		listbox_1.addListSelectionListener(new ListSelectionListener(){
		      public void valueChanged(ListSelectionEvent e) {
		            int selectedIndex =	listbox_1.getSelectedIndex();
		       
		          if(  e.getValueIsAdjusting() == true){
	
		              Doctor.findPatient(selectedIndex);
		         
		           }
		     
		      
		          desInput.setText(	"ssss");
		          
		      }
		});

	}	
	

	
	
	  public static void setPatientListModel(String [] patientsArray){

		  listModel.removeAllElements();
	    	for (int i = 0; i < patientsArray.length; i++)
	    	{
	    		System.out.print(surgey.docList.get(i).getPatient().get(0).getPName());
	    		System.out.println(patientsArray[i]); 
	    		listModel.addElement(patientsArray[i]);
	    	}
			mainWindow.model = model;
		
	listbox.setModel(model);
		}
}
