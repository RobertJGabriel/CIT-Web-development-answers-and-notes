import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class updatePatient {


	private JFrame frame;
	private JTextField txtU;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtDateOfBirth;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public updatePatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 392, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddPatient = new JLabel("Update Patient");
		lblAddPatient.setBounds(140, 48, 106, 16);
		frame.getContentPane().add(lblAddPatient);
		
		txtU = new JTextField();
		txtU.setText("Name");
		txtU.setBounds(122, 76, 134, 28);
		frame.getContentPane().add(txtU);
		txtU.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setText("Address");
		txtAddress.setBounds(122, 116, 134, 28);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setText("Phone");
		txtPhone.setBounds(122, 158, 134, 28);
		frame.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		txtDateOfBirth = new JTextField();
		txtDateOfBirth.setText("Date of Birth");
		txtDateOfBirth.setBounds(122, 198, 134, 28);
		frame.getContentPane().add(txtDateOfBirth);
		txtDateOfBirth.setColumns(10);
		
		JButton btnAddPatient = new JButton("Update Patient");
		btnAddPatient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = txtU.getText();
				String addressText = txtAddress.getText();
				String phoneText = txtPhone.getText();
				String dobText = txtDateOfBirth.getText();
				
				System.out.print("Patient updated");
				frame.dispose();
			}
		}
				);	
		
		btnAddPatient.setBounds(122, 238, 134, 29);
		frame.getContentPane().add(btnAddPatient);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	}


