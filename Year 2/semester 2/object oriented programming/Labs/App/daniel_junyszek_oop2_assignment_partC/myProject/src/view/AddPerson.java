package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class AddPerson extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private static JTextField txtPnametf;
	private static JTextField textField;
	private static JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public AddPerson() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblName = new JLabel("Name:");
		add(lblName, "cell 0 0,alignx right");
		
		txtPnametf = new JTextField();
		add(txtPnametf, "cell 1 0,alignx left");
		txtPnametf.setColumns(10);
		
		JLabel lblAddress = new JLabel(" Address:");
		add(lblAddress, "cell 0 1,alignx right");
		
		textField = new JTextField();
		add(textField, "cell 1 1,alignx left");
		textField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		add(lblPhone, "cell 0 2,alignx right");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 2,alignx left");
		textField_1.setColumns(10);

	}
	public String getName(){
		String s = txtPnametf.getText();
		return s;
	}
	public String getAddress(){
		String s = textField.getText();
		return s;
	}
	public String getPhone(){
		String s = textField_1.getText();
		return s;
	}
	public void clear(){
		txtPnametf.setText("");
		textField.setText("");
		textField_1.setText("");
	}
	
	

}
