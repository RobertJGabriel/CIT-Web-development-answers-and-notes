import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class addHistory{

	private JFrame frame;
	private JTextField patientID;
	private JTextField patientMed;
	private JTextField patientDes;
	private JTextField doctorId;

	private JTextField patientProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public addHistory() {
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

		JLabel lblAddPatient = new JLabel("Patient History");
		lblAddPatient.setBounds(150, 26, 106, 16);
		frame.getContentPane().add(lblAddPatient);

		patientID = new JTextField();
		patientID.setText("Patient ID");
		patientID.setBounds(122, 76, 134, 28);
		frame.getContentPane().add(patientID);
		patientID.setColumns(10);

		patientMed = new JTextField();
		patientMed.setText("MEDICENC");
		patientMed.setBounds(122, 116, 134, 28);
		frame.getContentPane().add(patientMed);
		patientMed.setColumns(10);

		patientDes = new JTextField();
		patientDes.setText("Desciptions");
		patientDes.setBounds(122, 158, 134, 28);
		frame.getContentPane().add(patientDes);
		patientDes.setColumns(10);

		patientProd = new JTextField();
		patientProd.setText("peodecoir");
		patientProd.setBounds(122, 198, 134, 28);
		frame.getContentPane().add(patientProd);
		patientProd.setColumns(10);

		JButton btHistoryPatent = new JButton("Add History");
		btHistoryPatent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

			int nameText =Integer.parseInt(patientID.getText() );
				String patientMedText = patientMed.getText();
				String patientDesText = patientDes.getText();
				String patientProdText = patientProd.getText();
				
				//Send to tPatient List
			//	Patient.doctorsVisit(nameText,patientMedText,patientDesText,patientProdText);


				frame.dispose();
			}
		}
				);	

		btHistoryPatent.setBounds(122, 238, 134, 29);
		frame.getContentPane().add(btHistoryPatent);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
