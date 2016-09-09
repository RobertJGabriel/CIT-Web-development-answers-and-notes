package gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import program.SurgeryMainDriver;

public class Login{
	private JPanel buttonOptionsPanel = new JPanel(new FlowLayout());
	private JPanel detailsPanel = new JPanel(new FlowLayout()); 
	private JLabel userIdLabel = new JLabel("User name: ");
	private JLabel passwordLabel = new JLabel("User password: ");
	private final int COLS = 10;
	private JPasswordField passwordField = new JPasswordField(COLS);
	private JTextField userIDField = new JTextField(COLS);
	private	JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel"); 
	private JFrame frame;
	private int userID;
	private String userPassword;

   public Login() 
   {	
   		makeFrame();
   }
  
   private void makeFrame() 
   {
	   frame = new JFrame("Login");
	   makePanels();
	   
	   Container contentPane = frame.getContentPane();
	//without grid layout:frame.getContentPane().add(detailsPanel, BorderLayout.NORTH);
      frame.setLayout(new GridLayout(2, 1));
      frame.setResizable(false);
      //frame.setSize(450, 100);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   private void makePanels(){

	   frame.add(detailsPanel);
	   frame.add(buttonOptionsPanel);
	   detailsPanel.add(userIdLabel);
	   detailsPanel.add(userIDField);
	   detailsPanel.add(passwordLabel);
	   detailsPanel.add(passwordField);
	   buttonOptionsPanel.add(okButton);
	   buttonOptionsPanel.add(cancelButton);
	   frame.getRootPane().setDefaultButton(okButton);
	   
	   //escape key listener, close on escape
	   KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	   Action escapeAction = new AbstractAction() {
	       // close the frame when the user presses escape
	       public void actionPerformed(ActionEvent e) {
	           frame.dispose();
	       }
	   }; 
	   frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
	   frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
	   
	   
	   //mouse listener
	   okButton.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   okAction();	//code in method to avoid duplication  
		   }
	   });
	   
	   //enter key listener for okButton not necessary because okButton defined as default
	  /*okButton.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					okAction();
				}
			}
		});*/ 
	  
	   //cancel button enter key listener
	  cancelButton.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					frame.dispose();
					userIDField.setText("");
					passwordField.setText(""); 
				}
			}
		});

	   cancelButton.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   frame.dispose();
			   userIDField.setText("");
			   passwordField.setText(""); 
		   }
	   });
   }
   
   /**
    * called in okButton action listeners
    */
   private void okAction(){
	   int userID = Integer.parseInt(userIDField.getText());
		 
	   userPassword = new String(passwordField.getPassword());
 		  
		  boolean loggedin = SurgeryMainDriver.login(userID, userPassword);
		  if(loggedin){
			  System.out.println("logged in"); 
			  SurgeryMainDriver.listPatients();
			  MainWindow.setJMenuItemsVisibility();
			  frame.dispose();
		  }
		  else 
		  	JOptionPane.showMessageDialog(null, "Wrong credentials entered. Press OK and re-enter");  
   }
   
}


