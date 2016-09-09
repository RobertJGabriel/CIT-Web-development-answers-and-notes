import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;



public class appPatient {
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
	public appPatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 392, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAddPatient = new JLabel("Add Patient");
		lblAddPatient.setBounds(144, 48, 106, 16);
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

		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String  nameText = txtU.getText();
				String addressText = txtAddress.getText();
				String phoneText = txtPhone.getText();
			

				Date patientDOB = null;


				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String dateInString = txtDateOfBirth.getText();

				try {

					patientDOB = formatter.parse(dateInString);

				} catch (ParseException ex) {

					ex.printStackTrace();
					JOptionPane.showMessageDialog(txtDateOfBirth, "Correct date format is: dd/MM/yyyy");
				}
				if(patientDOB != null){

					surgey.addPatient(nameText, addressText, phoneText , patientDOB );

					JOptionPane.showMessageDialog(null, "Patient added");

					surgey.listPatients();

					frame.dispose();
				}
			}
		}
				);	

		btnAddPatient.setBounds(122, 238, 134, 29);
		frame.getContentPane().add(btnAddPatient);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
