


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



import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SurgeyDesigner {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SurgeyDesigner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 326, 296);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		final JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(141, 74, 61, 16);
		frame.getContentPane().add(lblLogin);
		
		textField = new JTextField();
		textField.setBounds(92, 110, 134, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(92, 150, 134, 28);
		frame.getContentPane().add(passwordField);
		
		Button button = new Button("Sign In ");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			boolean help = 	surgey.loginCheck(textField.getText(), passwordField.getText());
			if (help == true){
				mainWindow loginMainWindow = new mainWindow();
				frame.dispose();
			
			
			}else{
				lblLogin.setText("Wrong :-(");
				System.out.print("false");
				
			}
			
			}
		});
		button.setBounds(92, 184, 134, 29);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().add(button);
	}
	
}
