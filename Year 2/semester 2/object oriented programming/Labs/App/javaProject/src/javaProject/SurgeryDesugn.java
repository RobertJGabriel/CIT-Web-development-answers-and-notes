package javaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SurgeryDesugn {
	static JFrame frame;
	JFrame LoginFrame;
	JFrame addDoctorFrame;
	JMenuBar menuBar;
	JMenu docMenu;
	JMenu patentMenu;
	JPanel westPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JList jl ;
	
	
	public  void addDoctor() { 
		
		//Conpunents 
		final JTextField name ;
		final JPasswordField passwords;
		JPanel middle;
		JPanel center;
		JLabel labelPass;
		JButton button;
		final JLabel label;

		//Label

		label = new JLabel();
		labelPass = new JLabel();
		label.setText("Enter Doctor Name");
		labelPass.setText("Enter Password");

		//Frame 
		addDoctorFrame = new JFrame();
		addDoctorFrame.setLayout(new BorderLayout());

		//Center
		center  = new JPanel();
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 

		//Input Field
		name =  new JTextField(10);
		name.setText("Doctor Name");

		//input Password
		passwords = new JPasswordField(10);
		passwords.setText("Password");

		//Button
		button =  new JButton();
		button.setText("Add Doctor");


		//Middle Container
		middle  = new JPanel();
		middle.setPreferredSize(new Dimension (200, 400) );
		middle.add(label,BorderLayout.NORTH);
		middle.add(name , BorderLayout.NORTH);
		middle.add(labelPass,BorderLayout.NORTH);
		middle.add(passwords, BorderLayout.NORTH);
		middle.add(button , BorderLayout.NORTH);

		center.add(middle);	

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = name.getText();
				String passwordText = passwords.getText();
				label.setText("Updated");
			//	docList.add(new Doctor(nameText,  passwordText));
				addDoctorFrame.setVisible(false);
				System.out.print("Add Docotor");
			}
		}
				);		
		addDoctorFrame.add(center);
		addDoctorFrame.setSize(300, 200);
		addDoctorFrame.setLocationRelativeTo(null);  
		addDoctorFrame.setTitle("Add Doctor");
		addDoctorFrame.setVisible(true);
		addDoctorFrame.setResizable(false);

		
		
	}
	
	public void login (){
		
		JPanel center;
		JPanel middle;
		JLabel label;
		JLabel passLabel;
		final JTextField txt;
		final JPasswordField ps;
		JButton button;

		LoginFrame = new JFrame();
		LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		LoginFrame.setLayout(new BorderLayout());
		LoginFrame.setLocationRelativeTo(null);  
		// UserName Label
		label = new JLabel();
		label.setText("UserName");

		// Doctors Name for input
		txt =new JTextField(10);
		txt.setText("Doctor Name");
		txt.requestFocus();
		// 	Password Label
		passLabel = new JLabel();
		passLabel.setText("Password  ");

		// Password Field	
		ps =new JPasswordField(10);
		ps.setText("*****");

		// Button
		button =new JButton();
		button.setText("Login");

		// Panel 
		middle = new JPanel();
		center = new JPanel();
		middle.setPreferredSize(new Dimension (160,400));
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 

		middle.add(label , BorderLayout.NORTH);
		middle.add(txt , BorderLayout.NORTH);
		middle.add(passLabel , BorderLayout.NORTH);
		middle.add(ps , BorderLayout.NORTH);
		middle.add(button , BorderLayout.NORTH);
		center.add(middle);

		// Adding parts to the frame
		LoginFrame.add(center);
		LoginFrame.setSize(300, 200);
		LoginFrame.setTitle("Login");
		LoginFrame.setVisible(true);
		LoginFrame.setResizable(false);

		//Button Listener
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String getTxt = txt.getText();
				String password = ps.getText();

	if(loginCheck(getTxt,password)== true){
			System.out.print(getTxt);
					frame.setTitle( getTxt);
			txt.setText("");  
					ps.setText("");
					LoginFrame.setVisible(false);
					frame();

				}else {txt.setText("Wrong!!");}
			}
		}
				);	
		
	}
	
	
	
	
	public static boolean loginCheck(String name,String password) {

		int x =  docList.size();
		int i =0;
		for (i = 0 ; i <= x-1; i++){
			if (docList.get(i).getDoctorName().toString().equals(name) && (docList.get(i).getDocPasswd().toString().equals(password))){
				System.out.println("Login Ok");
				return true;
			}
		}
		System.out.println("Something is wrong");
		return false;
	} 
	
	
	
	
	
	
	
	public void frame(){

		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setTitle("Welcome Doctor");
		menuBar = new JMenuBar();
		docMenu = new JMenu("Doctor");
		patentMenu = new JMenu("Patent");


	//	docMenu.add(makeMenuItem("Restore"));
		//docMenu.add(makeMenuItem("BackUp"));
		//docMenu.add(makeMenuItem("Add Doctor"));
		//docMenu.add(makeMenuItem("Log Out"));

//		patentMenu.add(makeMenuItem("Add Patient"));
	//	patentMenu.add(makeMenuItem("Update Patrient "));
		//patentMenu.add(makeMenuItem("Refresh List"));
		//patentMenu.add(makeMenuItem("Remove Patient"));

		//Adding menu together.

		menuBar.add(docMenu);
		menuBar.add(patentMenu);


		//West Panel
		westPanel= new JPanel(); 
		westPanel.add(new JLabel("Doctors List"), BorderLayout.NORTH);
		westPanel.setPreferredSize(new Dimension (200, 500) );
		westPanel.setBackground(new Color(231, 76, 60));
	//	teste = new DefaultListModel<String>();

		//teste.addElement("Name \t " + "Phone" );

		//if (docList.get(1).getPatient().size() == 0){
			//teste.addElement("No Patients");
		//}else{

		//}
		jl = new JList();
		//jl.setModel(teste);
		jl.setPreferredSize(new Dimension (200, 500) );


		westPanel.add(jl,BorderLayout.NORTH);
		//Center Panel
		centerPanel = new JPanel(); 
		centerPanel.setPreferredSize(new Dimension (500, 400) );
		centerPanel.setBackground(new Color(52, 152, 219));

		//South Panel
		southPanel = new JPanel(); 
		southPanel.setPreferredSize(new Dimension (500, 50) );
		southPanel.setBackground(new Color(211, 84, 0));


		//Frame Layout
		frame.setLayout(new BorderLayout());
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.add(westPanel, BorderLayout.WEST);	
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	
	}
}
