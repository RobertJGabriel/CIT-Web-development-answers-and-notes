import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.ScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class mainWindow{

	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JList patientslist;
	private static JList<String> patientListDisplay = new JList<String>();
	@SuppressWarnings("unused")
	private static JPanel patientListPanel = new JPanel();
	private static JTextArea historyPanel ;
	private static DefaultListModel<String> model = new DefaultListModel<String>();


	public static int selectedIndex = -1;
	public int number;
	private JTextField txtSearchPatient;
	private JTextField startDate;
	private JTextField endDate;

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
		surgey.listPatients();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(SystemColor.window);
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/RobertGabriel/Downloads/imac.png"));
		frame.setBounds(100, 100, 558, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Welcome " +  surgey.docList.get(surgey.id).getDoctorName());

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

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Doctor");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				addDoctor doctors = new addDoctor();

			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmBackUp = new JMenuItem("Back up");
		mntmBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgey.saveSystemToFile();
				JOptionPane.showMessageDialog(null, "System saved."); 
			}
		});
		mnNewMenu.add(mntmBackUp);


		JMenuItem report = new JMenuItem("Report");
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgey.printStuff();
				JOptionPane.showMessageDialog(null, "System saved."); 
			}
		});
		mnNewMenu.add(report);







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
				appPatient aP = new     appPatient();
			}
		});
		mnPatent.add(mntmNewMenuItem_2);


		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add History");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryWindow history = new    HistoryWindow();
			}
		});
		mnPatent.add(mntmNewMenuItem_3);


		frame.getContentPane().setLayout(null);

		JPanel admin = new JPanel();
		admin.setBackground(new Color(237,239,241));
		admin.setBounds(160, 76, 172, 217);
		frame.getContentPane().add(admin);
		admin.setEnabled(false);
		admin.setLayout(null);

		JLabel lblDate = new JLabel("Patient Information");
		lblDate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDate.setBounds(16, 9, 155, 16);
		admin.add(lblDate);




		final JTextArea desInput = new JTextArea();
		desInput.setText("Address");
		desInput.setBounds(6, 90, 160, 20);
		admin.add(desInput);

		final JTextArea dateInput = new JTextArea();
		dateInput.setText("Name");
		dateInput.setBounds(6, 45, 160, 20);
		admin.add(dateInput);

		final JTextArea medInput = new JTextArea();
		medInput.setText("Phone");
		medInput.setBounds(6, 133, 160, 20);
		admin.add(medInput);

		final JTextArea proInput = new JTextArea();
		proInput.setText("YYYY/MM/DD");
		proInput.setBounds(6, 165, 160, 20);
		admin.add(proInput);

		JPanel sideBar = new JPanel();
		sideBar.setBackground(new Color(44, 62, 80));
		sideBar.setBounds(0, 0, 137, 453);
		frame.getContentPane().add(sideBar);
		sideBar.setLayout(null);




		patientslist = new JList(model);
		patientslist.setForeground(Color.WHITE);
		patientslist.setBackground(new Color(44, 62, 80));
		patientslist.setBounds(6, 73, 125, 355);
		sideBar.add(patientslist);

		JLabel label = new JLabel("Patients");
		label.setBounds(32, 26, 71, 16);
		sideBar.add(label);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 17));


		patientslist.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {

				Patient test = surgey.docList.get(surgey.id).findPatient("NOTHING");
				try{
					test = surgey.docList.get(surgey.id).findPatient(patientslist.getSelectedValue().toString());
				}
				catch (Exception ex) { 
				}

				if( e.getValueIsAdjusting() == true){

					historyPanel.setText(" ");
					selectedIndex = test.getId();
					dateInput.setText(test.getPName());
					desInput.setText(test.getPAddress());
					medInput.setText(test.getPPhone());


					SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
					String	date_to_string = formatDateJava.format(test.getPDOB());
					proInput.setText(date_to_string);


					surgey.listHistory(selectedIndex);

				}
			}
		});

		startDate = new JTextField();
		startDate.setBounds(160, 404, 127, 28);
		frame.getContentPane().add(startDate);
		startDate.setColumns(10);

		endDate = new JTextField();
		endDate.setBounds(299, 404, 127, 28);
		frame.getContentPane().add(endDate);
		endDate.setColumns(10);

		JButton btnSetDate = new JButton("Set");
		btnSetDate.setBounds(438, 405, 85, 29);
		frame.getContentPane().add(btnSetDate);

		txtSearchPatient = new JTextField();
		txtSearchPatient.setBounds(150, 24, 265, 28);
		frame.getContentPane().add(txtSearchPatient);
		txtSearchPatient.setText("Search Patient by Name ");
		txtSearchPatient.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(427, 25, 99, 29);
		frame.getContentPane().add(btnSearch);

		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(609, 228, 61, 16);
		frame.getContentPane().add(lblHistory);
		lblHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 15));


		historyPanel = new JTextArea();
		historyPanel.setBackground(new Color(237,239,241));
		historyPanel.setBounds(369, 76, 154, 299);
		frame.getContentPane().add(historyPanel);



		final JButton update  = new  JButton();
		update.setBounds(160, 345, 172, 30);
		frame.getContentPane().add(update);

		update.setText("Update");

		final JButton remove  = new  JButton();
		remove.setBounds(160, 314, 171, 30);
		frame.getContentPane().add(remove);

		remove.setText("Remove");


		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				int number = selectedIndex;

				if (number < surgey.docList.get(surgey.id).pList.size() && number > -1 ){
					surgey.docList.get(surgey.id).pList.remove(number);

					System.out.print("Patient removed");
					JOptionPane.showMessageDialog(null, "Patient Removed");


					DBConnect connect = new DBConnect(); 
					connect.deleData(surgey.id,number);

					//	surgey.restoreSystemFromFile();
					surgey.listPatients();


				}
			}
		}
				);  


		update.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				number = selectedIndex;

				if (number < surgey.docList.get(surgey.id).pList.size() && number > -1 ){

					//surgey.docList.get(surgey.id).pList.remove(number);


					String  nameText = dateInput.getText();
					String addressText =    desInput.getText();
					String phoneText = medInput.getText();
					String dobText =proInput.getText();

					Date patientDOB = null;
					DateFormat sourceFormat = new SimpleDateFormat("yyyy/MM/dd");
					String dateAsString = proInput.getText();
					try {
						patientDOB = sourceFormat.parse(dateAsString);
					} catch (ParseException ex) {

						ex.printStackTrace();
						JOptionPane.showMessageDialog(proInput, "Correct date format is: yyyy/MM/dd");
					}

					if(patientDOB != null){

						int number = selectedIndex;

						surgey.docList.get(surgey.id).pList.get(number).setPName(nameText);
						surgey.docList.get(surgey.id).pList.get(number).setPAddress( addressText);
						surgey.docList.get(surgey.id).pList.get(number).setPPhone(phoneText);
						surgey.docList.get(surgey.id).pList.get(number).setpDOB(patientDOB);

						DBConnect connect = new DBConnect(); 
						connect.updateData(number, surgey.id,nameText, addressText, phoneText , patientDOB);

						JOptionPane.showMessageDialog(null, "Patient Update");
						//surgey.restoreSystemFromFile();
						surgey.listPatients();

					}
				}

			}
		}
				);  
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String test = 	txtSearchPatient.getText();

				int x =  surgey.docList.get(surgey.id).pList.size(); 			//Gets the amount of Doctors
				int foo = -1;
				try{
					foo = Integer.parseInt(test);
				} catch(NumberFormatException nfe) {  

				}  
				for (int i = 0 ; i <= x-1; i++){	
					if (surgey.docList.get(surgey.id).pList.get(i).getPName().equalsIgnoreCase(test) || surgey.docList.get(surgey.id).pList.get(i).getId() == foo ){
						System.out.println("Found :-o ");
						dateInput.setText(	surgey.docList.get(surgey.id).pList.get(i).getPName());
						desInput.setText(surgey.docList.get(surgey.id).pList.get(i).getPAddress());
						medInput.setText(surgey.docList.get(surgey.id).pList.get(i).getPPhone());
						SimpleDateFormat formatDateJava = new SimpleDateFormat("dd/MM/yyyy");
						String	date_to_string = formatDateJava.format(surgey.docList.get(surgey.id).pList.get(i).getPDOB());
						proInput.setText(date_to_string);

						JOptionPane.showMessageDialog(proInput, "Found");

						break;
					}
				}

			}
		});
		btnSetDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				Date 	date1 = null;
				Date 	date2 = null;




				SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy/MM/dd");
				String dateAsString = startDate.getText();

				try {
					date1 = sourceFormat.parse(dateAsString);
				} catch (ParseException ex) {

					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Correct end date format is: yyyy/MM/dd");
				}


				String date2AsString = endDate.getText();

				try {
					date2 = sourceFormat.parse(date2AsString);
				} catch (ParseException ex) {

					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Correct end date format is: yyyy/MM/dd");
				}


				DBConnect connect = new DBConnect(); 
				connect.between(date1, date2);
			}
		});

	}   


	public static void setPatientListHistory(String [] HistoryList){
		historyPanel.setText(" ");
		for (int i = 0; i < HistoryList.length; i++)
		{
			for(String W: HistoryList)
				historyPanel.append(W);
		}
		mainWindow.historyPanel = historyPanel;
	}

	public static void setPatientListModel(String [] patientsArray){

		model.removeAllElements();

		for (int i = 0; i < patientsArray.length; i++)
		{
			model.addElement(patientsArray[i]);
		}


		mainWindow.model = model;
		patientListDisplay.setModel(model);
	}
}