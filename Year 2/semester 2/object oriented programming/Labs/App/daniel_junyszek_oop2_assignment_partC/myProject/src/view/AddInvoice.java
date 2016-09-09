package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JPanel;
import org.freixas.jcalendar.JCalendarCombo;

import model.Procedure;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class AddInvoice extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7263283388026835754L;
	private JCalendarCombo calendarCombo;
	private JTextField pNameTf;
	private JTextField pIdTf;
	private JCheckBox chckbxPaid;
	public JComboBox<String> proc1CB;
	public JComboBox<String> proc2CB;
	public JComboBox<String> proc3CB;
	public JComboBox<String> procCB4;
	public JComboBox<String> proc5CB;
	private JCheckBox proc1ChB;
	private JCheckBox proc2ChB;
	private JCheckBox proc3ChB;
	private JCheckBox proc4ChB;
	private JCheckBox proc5ChB;
	private JLabel lblcheckBoxTo;
	/**
	 * Create the panel.
	 */
	public AddInvoice() {
		setLayout(new MigLayout("", "[grow][grow][grow]", "[][][][][][][][][][][grow]"));
		
		JLabel lblPatientName = new JLabel("Patient Name:");
		add(lblPatientName, "cell 0 0,alignx trailing");
		
		pNameTf = new JTextField();
		add(pNameTf, "cell 1 0,growx");
		pNameTf.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient Id:");
		add(lblPatientId, "cell 0 1,alignx trailing");
		
		pIdTf = new JTextField();
		add(pIdTf, "cell 1 1,growx");
		pIdTf.setColumns(10);
		
		JLabel lblInvoiceDate = new JLabel("Invoice Date: ");
		add(lblInvoiceDate, "cell 0 2,alignx trailing");
		
		calendarCombo = new JCalendarCombo();
		add(calendarCombo, "cell 1 2,growx");
		
		chckbxPaid = new JCheckBox("Paid");
		add(chckbxPaid, "cell 1 3");
		
		JLabel lblListOf = new JLabel("List of Procedures");
		add(lblListOf, "cell 0 4,alignx left");
		
		lblcheckBoxTo = new JLabel("(check box to add procedure)");
		add(lblcheckBoxTo, "cell 1 4");
		
		proc1ChB = new JCheckBox("1.");
		add(proc1ChB, "flowx,cell 0 5,alignx right");
		
		proc1CB = new JComboBox<String>();
		add(proc1CB, "cell 1 5,growx");
		
		proc2ChB = new JCheckBox("2.");
		add(proc2ChB, "cell 0 6,alignx right");
		
		proc2CB = new JComboBox<String>();
		add(proc2CB, "cell 1 6,growx");
		
		proc3ChB = new JCheckBox("3.");
		add(proc3ChB, "cell 0 7,alignx right");
		
		proc3CB = new JComboBox<String>();
		add(proc3CB, "cell 1 7,growx");
		
		proc4ChB = new JCheckBox("4.");
		add(proc4ChB, "cell 0 8,alignx right");
		
		procCB4 = new JComboBox<String>();
		add(procCB4, "cell 1 8,growx");
		
		proc5ChB = new JCheckBox("5.");
		add(proc5ChB, "cell 0 9,alignx right");
		
		proc5CB = new JComboBox<String>();
		add(proc5CB, "cell 1 9,growx");

	}
	public String getPatientName()
	{
		return pNameTf.toString();
	}
	public int getPatientId()
	{
		String s = pIdTf.getText();
		return Integer.parseInt(s); 
	}
	public Date getDate(){
		return calendarCombo.getDate();
	}
	
	public boolean getPaid()
	{
		return chckbxPaid.isSelected();
	}
	public void clear(){
		pNameTf.setText("");
		pIdTf.setText("");
		pIdTf.setColumns(10);
	}
	public void setProceduresCombos(LinkedList<Procedure> procedure){
		
		
		for(int i=0; i<procedure.size();i++)
		{
			proc1CB.addItem(procedure.get(i).getProcName()+":"+procedure.get(i).getProcCost());
		
			proc2CB.addItem(procedure.get(i).getProcName()+":"+procedure.get(i).getProcCost());
			
			proc3CB.addItem(procedure.get(i).getProcName()+":"+procedure.get(i).getProcCost());
			
			procCB4.addItem(procedure.get(i).getProcName()+":"+procedure.get(i).getProcCost());
			
			proc5CB.addItem(procedure.get(i).getProcName()+":"+procedure.get(i).getProcCost());
		}
	}
	public ArrayList<String> getProcedures(){
		ArrayList<String> procList=new ArrayList<String>();
		if(proc1ChB.isSelected()){
			procList.add(proc1CB.getSelectedItem().toString());
		}
		if(proc2ChB.isSelected()){
			procList.add(proc2CB.getSelectedItem().toString());
		}
		if(proc3ChB.isSelected()){
			procList.add(proc3CB.getSelectedItem().toString());
		}
		if(proc4ChB.isSelected()){
			procList.add(procCB4.getSelectedItem().toString());
		}
		if(proc5ChB.isSelected()){
			procList.add(proc5CB.getSelectedItem().toString());
		}
		
		
			return procList;

	}
	public void clearComboLists() {
		proc1CB.removeAllItems();
		proc2CB.removeAllItems();
		proc3CB.removeAllItems();
		procCB4.removeAllItems();
		proc5CB.removeAllItems();
		
	}}
