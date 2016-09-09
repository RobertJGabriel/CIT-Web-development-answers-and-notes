import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class removePatient {

	private JFrame frame;
	private JTextField txtU;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public removePatient() {
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
		
		JLabel lblAddPatient = new JLabel("Remove Patient");
		lblAddPatient.setBounds(140, 48, 106, 16);
		frame.getContentPane().add(lblAddPatient);
		
		txtU = new JTextField();
		txtU.setText("Name");
		txtU.setBounds(122, 76, 134, 28);
		frame.getContentPane().add(txtU);
		txtU.setColumns(10);
		
		JButton btnAddPatient = new JButton("Remove");
		btnAddPatient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String nameText = txtU.getText();
				System.out.print("Patient removed");
				frame.dispose();
			}
		}
				);	
		
		btnAddPatient.setBounds(122, 219, 134, 29);
		frame.getContentPane().add(btnAddPatient);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
