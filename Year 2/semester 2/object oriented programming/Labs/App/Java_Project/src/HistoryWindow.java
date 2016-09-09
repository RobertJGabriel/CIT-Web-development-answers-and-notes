import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JTextField; 
import javax.swing.JButton; 


public class HistoryWindow{ 

	private JFrame frame; 
	private JTextField patientMed; 
	private JTextField patientDes; 

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
	public HistoryWindow() { 
		initialize(); 
	} 

	/** 
	 * Initialize the contents of the frame. 
	 */
	private void initialize() { 
		frame = new JFrame(); 
		frame.setBounds(100, 100, 392, 280); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.getContentPane().setLayout(null); 

		JLabel lblAddPatient = new JLabel("Patient History"); 
		lblAddPatient.setBounds(150, 26, 106, 16); 
		frame.getContentPane().add(lblAddPatient);

		patientMed = new JTextField(); 
		patientMed.setText("Medicine"); 
		patientMed.setBounds(137, 54, 134, 28); 
		frame.getContentPane().add(patientMed); 
		patientMed.setColumns(10); 

		patientDes = new JTextField(); 
		patientDes.setText("Desciptions"); 
		patientDes.setBounds(137, 94, 134, 28); 
		frame.getContentPane().add(patientDes); 
		patientDes.setColumns(10); 

		patientProd = new JTextField(); 
		patientProd.setText("Procedures"); 
		patientProd.setBounds(137, 134, 134, 28); 
		frame.getContentPane().add(patientProd); 
		patientProd.setColumns(10); 

		JButton btHistoryPatent = new JButton("Add History"); 
		btHistoryPatent.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 


				String patientMedText = patientMed.getText(); 
				String patientDesText = patientDes.getText(); 
				String patientProdText = patientProd.getText(); 

				
				

				
				surgey.docList.get(surgey.id).pList.get(mainWindow.selectedIndex).doctorsVisit(mainWindow.selectedIndex,patientMedText,patientDesText, patientProdText); 
				 DBConnect connect = new DBConnect(); 
				 connect.insertHistory(surgey.id, mainWindow.selectedIndex,patientMedText,patientDesText, patientProdText);

				JOptionPane.showMessageDialog(null, "Patient History Added"); 
				frame.dispose(); 
			} 
		} 
				);   

		btHistoryPatent.setBounds(137, 184, 134, 29); 
		frame.getContentPane().add(btHistoryPatent); 
		frame.setVisible(true); 
		frame.setResizable(false); 
	} 
} 




















