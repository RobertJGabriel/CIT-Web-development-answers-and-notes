package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.TextField;
import org.freixas.jcalendar.JCalendarCombo;
import java.util.Date;

import javax.swing.JTextField;
public class AddHistory extends JPanel {
	
	private static final long serialVersionUID = 2L;
	JTextField conditionNameTF;
	JTextField medicationRecievedTF;
	JCalendarCombo calendarCombo;
	private JTextField patientNameTF;
	private JTextField txtPatientId;
	private JLabel lblPatientNameOr;
	private JLabel lblPatientId;
	public AddHistory() {
		setLayout(new MigLayout("", "[][111.00,grow][grow]", "[][][][][]"));
		
		lblPatientNameOr = new JLabel("Patient Name:           or");
		add(lblPatientNameOr, "flowy,cell 1 0");
		
		patientNameTF = new JTextField();
		patientNameTF.setText("");
		add(patientNameTF, "cell 1 0,growx");
		patientNameTF.setColumns(10);
		
		lblPatientId = new JLabel("Patient ID");
		add(lblPatientId, "flowy,cell 2 0");
		
		txtPatientId = new JTextField();
		txtPatientId.setText("");
		add(txtPatientId, "cell 2 0,growx");
		txtPatientId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Condition Name:");
		add(lblNewLabel, "cell 1 1");
		
		conditionNameTF = new JTextField();
		add(conditionNameTF, "cell 2 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Medication Recieved:");
		add(lblNewLabel_1, "cell 1 2");
		
		medicationRecievedTF = new JTextField();
		add(medicationRecievedTF, "cell 2 2,growx");
		
		JLabel lblDateOccoured = new JLabel("Date Occoured:");
		add(lblDateOccoured, "cell 1 3");
		
		calendarCombo = new JCalendarCombo();
		add(calendarCombo, "cell 2 3");
		
		

	}
	public String getPatientName()
	{
		return patientNameTF.getText();
	}
	public int getPatientId()
	{
		String s = txtPatientId.getText();
		try{
			Integer.parseInt(s);
		}
		catch(NumberFormatException nfe){
			return -1;
		}
		return Integer.parseInt(s);
	}
	public String getConditionName()
	{
		return conditionNameTF.getText();
	}
	public Date getDate(){
		return calendarCombo.getDate();
	}
	public String getMedication(){
		return medicationRecievedTF.getText();
	}
	public void clear(){
		patientNameTF.setText("");
		txtPatientId.setText("");
		conditionNameTF.setText("");
		medicationRecievedTF.setText("");
	}

}
