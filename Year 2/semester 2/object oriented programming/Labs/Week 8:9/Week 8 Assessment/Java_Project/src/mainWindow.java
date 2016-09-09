


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
import java.awt.Window.Type;

public class mainWindow {

	private JFrame frame;
	private JList listbox_1;
	private static JList<String> patientListDisplay = new JList<String>();
	private static JPanel patientListPanel = new JPanel();
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	public int selectedIndex;
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
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 568, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle(	surgey.docList.get(surgey.id).getDoctorName());




		listbox_1 = new JList(model);

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

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Doctor");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
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

		JMenuItem mntmRefreshList = new JMenuItem("Refresh List");
		mntmRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surgey.restoreSystemFromFile();
				surgey.listPatients();
			}});
		mnPatent.add(mntmRefreshList);

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

		JLabel lblDate = new JLabel("Name");
		lblDate.setBounds(6, 6, 61, 16);
		panel.add(lblDate);

		final JLabel lblDescription = new JLabel("Address");
		lblDescription.setBounds(6, 66, 89, 16);
		panel.add(lblDescription);

		JLabel lblMedicine = new JLabel("Phone");
		lblMedicine.setBounds(6, 117, 132, 16);
		panel.add(lblMedicine);

		JLabel lblProcedure = new JLabel("Date of Birth");
		lblProcedure.setBounds(6, 171, 79, 16);
		panel.add(lblProcedure);


		// History
		JLabel Med = new JLabel("Medicine");
		Med.setBounds(166, 6, 61, 16);
		panel.add(Med);

		JLabel discriptuion = new JLabel("Discriptuion");
		discriptuion.setBounds(166, 66, 89, 16);
		panel.add(discriptuion);

		JLabel  procedure = new JLabel("Opption");
		procedure.setBounds(166, 117, 89, 16);
		panel.add(procedure);


		//Add 


		final JTextArea MEDInput = new JTextArea();
		MEDInput.setBounds(166, 94, 105, 20);
		panel.add(MEDInput);

		final JTextArea discInput = new JTextArea();
		discInput.setBounds(166, 34, 105, 20);
		panel.add(discInput);

		final JTextArea OPPInput = new JTextArea();
		OPPInput.setBounds(166, 145, 105, 20);
		panel.add(OPPInput);




		final JTextArea desInput = new JTextArea();
		desInput.setBounds(16, 94, 105, 20);
		panel.add(desInput);

		final JTextArea dateInput = new JTextArea();
		dateInput.setBounds(16, 34, 105, 20);
		panel.add(dateInput);

		final JTextArea medInput = new JTextArea();
		medInput.setBounds(16, 145, 105, 20);
		panel.add(medInput);

		final JTextArea proInput = new JTextArea();
		proInput.setBounds(16, 206, 105, 20);
		panel.add(proInput);

		final JButton remove  = new  JButton();
		remove.setBounds(16, 263, 150, 30);
		remove.setVisible(false);
		remove.setText("Rmove Patient");
		panel.add(remove);


		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				int number = selectedIndex;

				if (number < surgey.docList.get(surgey.id).pList.size() && number > -1 ){
					surgey.docList.get(surgey.id).pList.remove(number);

					System.out.print("Patient removed");
					JOptionPane.showMessageDialog(null, "Patient Removed");

					surgey.listPatients();

					remove.setVisible(false);

				}
			}
		}
				);	



		final JButton update  = new  JButton();
		update.setBounds(211, 263, 150, 30);
		update.setVisible(false);
		update.setText("Update Patient");
		panel.add( update);


		update.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				int number = selectedIndex;

				if (number < surgey.docList.get(surgey.id).pList.size() && number > -1 ){
					surgey.docList.get(surgey.id).pList.remove(number);

					System.out.print("Patient removed");



					String  nameText = dateInput.getText();
					String addressText = 	desInput.getText();
					String phoneText = medInput.getText();
					String dobText =proInput.getText();


					surgey.addPatient(nameText, addressText, phoneText , dobText );

					JOptionPane.showMessageDialog(null, "Patient Removed");

					surgey.listPatients();

					update.setVisible(false);

				}
			}
		}
				);	


		listbox_1.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				selectedIndex =	listbox_1.getSelectedIndex();

				if(  e.getValueIsAdjusting() == true){
					remove.setVisible(true);
					update.setVisible(true);
					Doctor.findPatient(selectedIndex);
					dateInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).getPName());
					desInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).getPAddress());
					medInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).getPPhone());

/*
					discInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).myHistory.get(1).getDescription());
					MEDInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).myHistory.get(1).getMedicine());
					OPPInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).myHistory.get(1).getProcedure());
					//  proInput.setText(surgey.docList.get(surgey.id).pList.get(selectedIndex).getPDOB());
	*/
				}
			}
		});

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
