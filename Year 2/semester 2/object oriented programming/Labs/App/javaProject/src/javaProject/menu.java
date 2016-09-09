package javaProject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class menu extends JFrame implements ActionListener {
	private Surgey home ;
	private JPanel mypanel;
	private	JButton mybutton;
	private	JLabel mylabel;
	private JTextField textField ;
	private JPasswordField passField;


	public static void main(String[] s) {
		new  menu();
	}

	public  menu() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});

		//Set Color of background
		Color c = new Color(41, 128, 185);
		Container con = this.getContentPane();
		con.setBackground(c);

		textField = new JTextField(20);
		passField = new JPasswordField(20);






		//form 




		JPanel westPanel = new JPanel(); 
		westPanel.setSize(200, 500);
		westPanel.setBackground(new Color(231, 76, 60));



		//Layout

		setLayout(new BorderLayout());
		//add(menuBar, BorderLayout.NORTH);
		add(new Button("South"), BorderLayout.SOUTH);
		add( westPanel, BorderLayout.WEST);
		add(new Button("Center"), BorderLayout.CENTER);


		this.setSize(700, 500);
		this.setLocation(200, 200);
		setVisible(true);
		setResizable(false);
	}






	public void actionPerformed(ActionEvent e) {

		// Menu item actions
		String command = e.getActionCommand();

		if (command.equals("Quit")) {
			System.exit(0);
		} else if (command.equals("Login")) {
			// Open menu item action


		} else if (command.equals("Save")) {
			// Save menu item action
			System.out.println("Save menu item clicked");
		}
	}

	private JMenuItem makeMenuItem(String name) {
		JMenuItem m = new JMenuItem(name);
		m.addActionListener(this);
		return m;
	}
}