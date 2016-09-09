
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class addDoctor {

	private JFrame frame;
	private JTextField txtU;
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
	public addDoctor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 304, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddPatient = new JLabel("Add Doctor");
		lblAddPatient.setBounds(96, 48, 106, 16);
		frame.getContentPane().add(lblAddPatient);
		
		txtU = new JTextField();
		txtU.setText("Name");
		txtU.setBounds(80, 76, 134, 28);
		frame.getContentPane().add(txtU);
		txtU.setColumns(10);
		
		JButton btnAddPatient = new JButton("Add Doctor");
		btnAddPatient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = txtU.getText();
				String passwordField1 = passwordField.getText();
				surgey.addDoctor(nameText, passwordField1);
				System.out.print("Doctor Added");
				 DBConnect connect = new DBConnect(); 
				 connect.insertData(nameText, passwordField1);
				
				
				frame.dispose();
			}
		}
				);	
		
		btnAddPatient.setBounds(80, 154, 134, 29);
		frame.getContentPane().add(btnAddPatient);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 116, 134, 28);
		frame.getContentPane().add(passwordField);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
