import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class Example extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example frame = new Example();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Example() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(57, 23, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(289, 23, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFname = new JLabel("fName");
		lblFname.setBounds(6, 29, 61, 16);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("LName");
		lblLname.setBounds(216, 29, 61, 16);
		contentPane.add(lblLname);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(437, 29, 61, 16);
		contentPane.add(lblPhone);
		
		textField_2 = new JTextField();
		textField_2.setBounds(491, 23, 134, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAddbutton = new JButton("AddButton");
		btnAddbutton.setBounds(184, 103, 117, 29);
		contentPane.add(btnAddbutton);
		
		JButton btnRemoveButton = new JButton("Remove Button");
		btnRemoveButton.setBounds(55, 103, 117, 29);
		contentPane.add(btnRemoveButton);
		
		JButton btnDisplayButton = new JButton("Display Button");
		btnDisplayButton.setBounds(313, 103, 117, 29);
		contentPane.add(btnDisplayButton);
		
		JButton btnQuitProgram = new JButton("Quit Program");
		btnQuitProgram.setBounds(442, 103, 117, 29);
		contentPane.add(btnQuitProgram);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 184, 647, 104);
		contentPane.add(textArea);
	}
}
