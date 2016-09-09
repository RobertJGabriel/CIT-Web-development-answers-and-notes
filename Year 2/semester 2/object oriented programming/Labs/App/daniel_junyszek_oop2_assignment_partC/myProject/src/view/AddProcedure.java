package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddProcedure extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private JTextField procedureNameTF;
	private JTextField procedureCostTF;

	/**
	 * Create the panel.
	 */
	public AddProcedure() {
		setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblProcedureName = new JLabel("Procedure Name: ");
		add(lblProcedureName, "cell 0 0,alignx trailing");
		
		procedureNameTF = new JTextField();
		add(procedureNameTF, "cell 1 0,growx");
		procedureNameTF.setColumns(10);
		
		JLabel lblProcedureCost = new JLabel("Procedure Cost:");
		add(lblProcedureCost, "cell 0 1,alignx trailing");
		
		procedureCostTF = new JTextField();
		procedureCostTF.setText("0.00");
		add(procedureCostTF, "cell 1 1,growx");
		procedureCostTF.setColumns(10);

	}
	public String getProcedureName(){
		return procedureNameTF.getText();
	}
	public double getProcedureCost(){
		return Double.parseDouble(procedureCostTF.getText());
	}
	public void clear(){
		procedureNameTF.setText("");
		procedureCostTF.setText("");
	}

}
