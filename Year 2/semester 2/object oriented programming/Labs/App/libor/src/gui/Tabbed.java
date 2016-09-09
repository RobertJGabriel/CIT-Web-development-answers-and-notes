package gui;

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

	private JPanel labelPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel panelJoin = new JPanel(false);
	private JPanel buttonPanel = new JPanel();
	
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
	
	private JLabel patientHistory = new JLabel("Patient History: ");
	private JTextField patientHistoryValue = new JTextField();
	private JButton button = new JButton("");

	private int detailsType = 0;
	
	private JRadioButton idChoice;
	private JRadioButton nameChoice;
	private ButtonGroup group;
	
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
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
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
			});

	    }
	    else if(detailsType == 2){
	    	
	    	button.setText("Search patient");
	    	experimentLayout.setRows(2);
	    	
	    	
	    	idChoice = new JRadioButton("Search by ID", true);
	    	nameChoice = new JRadioButton("Search by name", false);
	    	add(idChoice);
	    	add(nameChoice);
	    	group = new ButtonGroup();
	    	group.add(idChoice);
	    	group.add(nameChoice);
	    	
	    	idChoice.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent e){
	    		            patientNameValue.setEditable(false);
	    		            patientIDValue.setEditable(true);
	    		    }
	    	});
	    	
	    	nameChoice.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent e){
	    		        JRadioButton button = (JRadioButton) e.getSource();
	    		        	patientNameValue.setEditable(true);
	    		            patientIDValue.setEditable(false);
	    		    }
	    	});
	    	
	    	labelPanel.setLayout(experimentLayout);
	    	labelPanel.setMaximumSize(new Dimension(250, 350));

	    	buttonPanel.setMaximumSize(new Dimension(250, 350));
	    	buttonPanel.add(idChoice);
	    	buttonPanel.add(nameChoice);
	    	
			labelPanel.add(patientName);
			labelPanel.add(patientID);
			
			textPanel.setLayout(experimentLayout);
			textPanel.setMaximumSize(new Dimension(250, 350));
			
			textPanel.add(patientNameValue);
			textPanel.add(patientIDValue);
			
			panelJoin.add(Box.createRigidArea(new Dimension(0, 20)));
			panelJoin.add(buttonPanel);
	    }
	    
	    
	    panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
	    panelTop.add(labelPanel);
	    panelTop.add(Box.createRigidArea(new Dimension(10,5)));
	    panelTop.add(textPanel);
	    
	    panelBottom.add(Box.createRigidArea(new Dimension(410, 30)));
	    panelBottom.add(button);
	    
	    panelJoin.add(Box.createRigidArea(new Dimension(0, 20)));
	    panelJoin.add(panelTop);
	    panelJoin.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelJoin.add(panelBottom);
	    panelJoin.setLayout(new BoxLayout(panelJoin, BoxLayout.Y_AXIS));
		
	}
	
	public JPanel getJoinPanel(){
		return panelJoin;
	}

}
