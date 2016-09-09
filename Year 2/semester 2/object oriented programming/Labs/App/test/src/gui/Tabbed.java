package gui;

import program.Doctor;
import program.Patient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import program.SurgeryMainDriver;
public class Tabbed extends JPanel {
	private static SurgeryMainDriver surgery = new SurgeryMainDriver();
	private static Doctor doctor;
	private static Patient patient;
	
	private JPanel labelPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel panelJoin = new JPanel(false);
	private JPanel buttonPanel = new JPanel();
	private JPanel historyLabelPanel = new JPanel();
	private JPanel historyTextPanel = new JPanel();
	
	//labels and fields for patient details
	private JLabel patientID = new JLabel("Patient ID: ");
	private JTextField patientIDValue = new JTextField();
	
	private JLabel patientName = new JLabel("Patient name: ");
	private JTextField patientNameValue = new JTextField();
	
	private JLabel patientAddress = new JLabel("Patient address: ");
	private JTextField patientAddressValue = new JTextField();
	
	private JLabel patientPhone = new JLabel("Patient phone: ");
	private JTextField patientPhoneValue = new JTextField();
	
	private JLabel patientDOB = new JLabel("Patient DOB (DD/MM/YYYY)");
	private JTextField patientDOBValue = new JTextField();
	
	private JButton button = new JButton("");
	private JButton updateButton = new JButton("");
	private JButton resetButton = new JButton("");
	private JButton addHistoryButton = new JButton("Add History");

	//labels and fields for patient history
	private JPanel panelPatientHistory = new JPanel(false);
	
	private JLabel description = new JLabel("Description: ");
	private JTextField descriptionValue = new JTextField();
	
	private JLabel medicine = new JLabel("Medicine: ");
	private JTextField medicineValue = new JTextField();
	
	private JLabel procedure = new JLabel("Procedure: ");
	private JTextField procedureValue = new JTextField();
	
	private int detailsType = 0;
	
	private JRadioButton idChoice;
	private JRadioButton nameChoice;
	private ButtonGroup group;
	private int controller = 0;
	
	private GridLayout experimentLayout = new GridLayout(2, 1, 0, 5);	//amount of rows, columns, horizontal gap, vertical gap
	
	public Tabbed(int detailsType){
		setDetailsType(detailsType);
		makePanel();
		
	}
	
	public void setDetailsType(int detailsType){
		this.detailsType = detailsType;
	}

	private void makePanel(){
		JPanel panelTop = new JPanel(false);
	    JPanel panelBottom = new JPanel(false);
	    
	    
	    if (detailsType == 1){
	    	button.setText("Add patient");
	    	experimentLayout.setRows(4);
	    	labelPanel.setLayout(experimentLayout);
	    	labelPanel.setMaximumSize(new Dimension(250, 350));

			labelPanel.add(patientName);
			labelPanel.add(patientAddress);
			labelPanel.add(patientPhone);
			labelPanel.add(patientDOB);
			
			textPanel.setLayout(experimentLayout);
			textPanel.setMaximumSize(new Dimension(250, 350));
			
			textPanel.add(patientNameValue);
			textPanel.add(patientAddressValue);
			textPanel.add(patientPhoneValue);
			textPanel.add(patientDOBValue);
			
			//add patient menu action listeners
			//mouse click listener
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(patientNameValue.getText().length() != 0 && patientAddressValue.getText().length() != 0 && (patientDOBValue.getText().length() != 0 && patientPhoneValue.getText().length() != 0))
						okAddAction();
					else 
						JOptionPane.showMessageDialog(null, "All fields are required", "alert", JOptionPane.ERROR_MESSAGE);	
				}
			});
			
			//enter key action listener
			button.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						okAddAction();
					}
				}
			});

	    }
	    else if(detailsType == 2){
	    	
	    	button.setText("Search patient");
	    	experimentLayout.setRows(5);
	    	patientNameValue.setEditable(false);
	    	
	    	nameChoice = new JRadioButton("Search by name", false);
	    	idChoice = new JRadioButton("Search by ID", true);
	    	add(nameChoice);
	    	add(idChoice);
	    	group = new ButtonGroup();
	    	group.add(nameChoice);
	    	group.add(idChoice);
	    		    	
	    	idChoice.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent e){
	    		            patientNameValue.setEditable(false);
	    		            patientIDValue.setEditable(true);
	    		            patientIDValue.requestFocus();
	    		            resetSearchForm();
	    		    }
	    	});
	    	
	    	HandlerClass handler = new HandlerClass();
	    	idChoice.addItemListener(handler);
	    	
	    	nameChoice.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent e){
	    		        JRadioButton button = (JRadioButton) e.getSource();
	    		        	patientNameValue.setEditable(true);
	    		        	patientNameValue.requestFocus();
	    		            patientIDValue.setEditable(false);
	    		            resetSearchForm();
	    		    }
	    	});
	    	
	    	//mouse listener
	 	   button.addActionListener(new ActionListener(){
	 		   public void actionPerformed(ActionEvent e){
	 			   if(patientIDValue.getText().length() != 0 || patientNameValue.getText().length() != 0){	//if the user didn't enter id or name
	 				  resetButton.setEnabled(true);
		 			   updateButton.setEnabled(true);
		 			   panelPatientHistory.setVisible(true);
		 			   addHistoryButton.setEnabled(true);
		 			   try {
						okSearchAction();//code in method to avoid duplication 
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	 			   }
	 			   else
	 				   JOptionPane.showMessageDialog(null, "You must enter patient Name or ID!", "alert", JOptionPane.ERROR_MESSAGE);	 
	 		   }
	 	   });
	 	   
	 	   //enter key listener
	 	   button.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_ENTER && (patientIDValue.getText().length() != 0 || patientNameValue.getText().length() != 0)){
						System.out.println("fail");
						  resetButton.setEnabled(true);
						  updateButton.setEnabled(true);
						  panelPatientHistory.setVisible(true);
						  addHistoryButton.setEnabled(true);
						try {
							okSearchAction();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "You must enter patient Name or ID!", "alert", JOptionPane.ERROR_MESSAGE);	
				}
			});
	 	   
	 	   //mouse listener
	 	   updateButton.addActionListener(new ActionListener(){
	 		   public void actionPerformed(ActionEvent e){
	 			   if (updateButton.getText().equals("Enable update")){
	 				   updateButton.setText("Update!");
		 			   patientAddressValue.setEditable(true);
		 			   patientPhoneValue.setEditable(true);
		 			   patientDOBValue.setEditable(true);
	 			   }
	 			   else if(updateButton.getText().equals("Update!")){
	 				   updatePatient();
	 			   }
	 		   }
	 	   });
	 	//mouse listener
	 	   resetButton.addActionListener(new ActionListener(){
	 		   public void actionPerformed(ActionEvent e){
	 			  resetSearchForm();
	 		   }
	 	   });
	 	   
	 	  //mouse listener
	 	  addHistoryButton.addActionListener(new ActionListener(){
	 		   public void actionPerformed(ActionEvent e){
	 			   int doctorID = surgery.getDoctorNum();
	 			   int patientID = Integer.parseInt(patientIDValue.getText());
	 			   String description = descriptionValue.getText();
	 			   String medicine = medicineValue.getText();
	 			   String procedure = procedureValue.getText();
	 			   
	 			   if(description.length() != 0 && medicine.length() != 0 && procedure.length() != 0){
	 				  patient.setPatientHistory(patientID, doctorID, description, medicine, procedure);
	 			   }
	 			   else
	 				  JOptionPane.showMessageDialog(null, "All patient history fields are required", "alert", JOptionPane.ERROR_MESSAGE);	
	 		   }
	 	   });
	 	  
	 	   
	    	labelPanel.setLayout(experimentLayout);
	    	labelPanel.setMaximumSize(new Dimension(250, 350));

	    	buttonPanel.setMaximumSize(new Dimension(250, 350));
	    	buttonPanel.add(idChoice);
	    	buttonPanel.add(nameChoice);
	    	
	    	labelPanel.add(patientID);
			labelPanel.add(patientName);
			
			labelPanel.add(patientAddress);
			patientAddress.setVisible(false);
			labelPanel.add(patientPhone);
			patientPhone.setVisible(false);
			labelPanel.add(patientDOB);
			patientDOB.setVisible(false);
			
			textPanel.setLayout(experimentLayout);
			textPanel.setMaximumSize(new Dimension(250, 350));
			
			textPanel.add(patientIDValue);
			textPanel.add(patientNameValue);
			
			textPanel.add(patientAddressValue);
			patientAddressValue.setVisible(false);
			textPanel.add(patientPhoneValue);
			patientPhoneValue.setVisible(false);
			textPanel.add(patientDOBValue);
			patientDOBValue.setVisible(false);
			
			panelJoin.add(Box.createRigidArea(new Dimension(0, 20)));
			panelJoin.add(buttonPanel);
			
			//history panel
			historyLabelPanel.setLayout(new GridLayout(3, 1, 0, 5));
			historyLabelPanel.add(procedure);
			procedure.setPreferredSize(new Dimension(250, 20));
			historyLabelPanel.add(description);
			historyLabelPanel.add(medicine);
			
			historyTextPanel.setLayout(new GridLayout(3, 1, 0, 5));
			historyTextPanel.add(procedureValue);
			procedureValue.setPreferredSize(new Dimension(250, 20));
			historyTextPanel.add(descriptionValue);
			historyTextPanel.add(medicineValue);
	    }
	    
	    
	    panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
	    panelTop.add(labelPanel);
	    panelTop.add(Box.createRigidArea(new Dimension(10,5)));
	    panelTop.add(textPanel);
	    
	   // panelBottom.setLayout(new GridLayout(3, 1, 0, 5));
	    panelBottom.add(Box.createRigidArea(new Dimension(410, 30))); //410 makes the button to go to the right
	    panelBottom.add(button);
	    resetButton.setText("Reset form");
	    resetButton.setEnabled(false);
	    updateButton.setEnabled(false);
	    updateButton.setText("Enable update");
	    if (detailsType == 2){
	    	panelBottom.add(Box.createRigidArea(new Dimension(0, 5)));
	    	panelBottom.add(updateButton);
	    	panelBottom.add(Box.createRigidArea(new Dimension(0, 5)));
	    	panelBottom.add(resetButton);
	    	panelPatientHistory.add(historyLabelPanel);
	    	panelPatientHistory.add(historyTextPanel);
	    	panelPatientHistory.add(Box.createRigidArea(new Dimension(100, 30)));
	    	panelPatientHistory.add(addHistoryButton);
	    	panelPatientHistory.setVisible(false);
	    	addHistoryButton.setEnabled(false);
	    }
	    	
	    
	    panelJoin.add(Box.createRigidArea(new Dimension(0, 20)));
	    panelJoin.add(panelTop);
	    panelJoin.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelJoin.add(panelBottom);
	    panelJoin.setLayout(new BoxLayout(panelJoin, BoxLayout.Y_AXIS));
	    
	    
	    if (detailsType == 2){
	    	panelJoin.add(Box.createRigidArea(new Dimension(0, 10)));
	    	panelJoin.add(panelPatientHistory);
	    	panelPatientHistory.setBorder(BorderFactory.createTitledBorder("Add Patient history"));
	    }
	    
	  //  panelJoin.getRootPane().setDefaultButton(button);
	    
	}
	
	public JPanel getJoinPanel(){
		return panelJoin;
	}

	private class HandlerClass implements ItemListener {
	      public void itemStateChanged(ItemEvent event) {
	           if (controller == 0)
	        	   controller = 1;
	           else
	        	   controller = 0;
	      }
	  }
	
	public void updatePatient(){
		int patientIDValueCopy = Integer.parseInt(patientIDValue.getText());	//variables to call different overloaded methods
		patient = doctor.findPatient(patientIDValueCopy);
		patient.setAddress(patientAddressValue.getText());
		patient.setPhone(patientPhoneValue.getText());
		
		Date patientDOB = null;
	    DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateAsString = patientDOBValue.getText();
		try {
			patientDOB = sourceFormat.parse(dateAsString);
		} catch (ParseException ex) {

			ex.printStackTrace();
			JOptionPane.showMessageDialog(patientDOBValue, "Correct date format is: dd/MM/yyyy");
		}
		if(patientDOB != null)
			patient.setDate(patientDOB);
		
	}
	
	public void okSearchAction() throws ParseException{
		experimentLayout.setRows(5);
		
		doctor = surgery.getDoctor();

		
		if(controller == 0){
			int patientIDValueCopy = Integer.parseInt(patientIDValue.getText());	//variables to call different overloaded methods
			patient = doctor.findPatient(patientIDValueCopy);
			
		}
		else{
			String patientNameValueCopy = patientNameValue.getText();
			patient = doctor.findPatient(patientNameValueCopy);
			
		}
		if (patient != null){
			patientNameValue.setText(patient.getName());
			StringBuilder sb = new StringBuilder();
			sb.append(patient.getId());
			patientIDValue.setText(sb.toString());
			
			patientAddress.setVisible(true);
			patientPhone.setVisible(true);
			patientDOB.setVisible(true);
			
			patientAddressValue.setVisible(true);
			patientAddressValue.setEditable(false);
			patientAddressValue.setText(patient.getAddress());
			
			patientPhoneValue.setVisible(true);
			patientPhoneValue.setEditable(false);
			patientPhoneValue.setText(patient.getPhone());
			
			DateFormat sourceFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date dateAsDate = patient.getDOB();
			String patientDOBasString = "";
			patientDOBasString = sourceFormat.format(dateAsDate);
			
			patientDOBValue.setVisible(true);
			patientDOBValue.setEditable(false);
			patientDOBValue.setText(patientDOBasString);
			updateButton.setText("Enable update");
			panelPatientHistory.setVisible(true);
			addHistoryButton.setEnabled(true);
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Patient not found!", "alert", JOptionPane.ERROR_MESSAGE);
			resetButton.setEnabled(false);
			updateButton.setEnabled(false);
			panelPatientHistory.setVisible(false);
			addHistoryButton.setEnabled(false);
		}
	}
	
	public void okAddAction(){
		String patientName = patientNameValue.getText();
		String patientAddress = patientAddressValue.getText();
		String patientPhone = patientPhoneValue.getText();
		
		Date patientDOB = null;
	    DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateAsString = patientDOBValue.getText();
		try {
			patientDOB = sourceFormat.parse(dateAsString);
		} catch (ParseException ex) {

			ex.printStackTrace();
			JOptionPane.showMessageDialog(patientDOBValue, "Correct date format is: dd/MM/yyyy");
		}
		if(patientDOB != null){
			surgery.addPatient(patientName, patientAddress, patientPhone, patientDOB );
			patientNameValue.setText("");
			patientAddressValue.setText("");
			patientPhoneValue.setText("");
			patientDOBValue.setText("");
			JOptionPane.showMessageDialog(null, "Patient added");
		}
	}
	
	public void resetSearchForm(){
		patientIDValue.setText("");
		patientNameValue.setText("");
		patientAddressValue.setText("");
		patientAddressValue.setVisible(false);
		patientPhoneValue.setText("");
		patientPhoneValue.setVisible(false);
		patientDOBValue.setText("");
		patientDOBValue.setVisible(false);
		resetButton.setEnabled(false);
		updateButton.setEnabled(false);
		panelPatientHistory.setVisible(false);
		addHistoryButton.setEnabled(false);
	}
}
