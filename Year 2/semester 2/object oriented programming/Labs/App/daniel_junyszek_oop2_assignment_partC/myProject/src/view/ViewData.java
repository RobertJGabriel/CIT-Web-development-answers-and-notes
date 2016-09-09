package view;
import java.text.DateFormat;
import java.util.LinkedList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.*;
import javax.swing.JButton;

public class ViewData extends JPanel {
	
	private static final long serialVersionUID = 10L;
	public JTable invoiceTbl;
	public JTable historyTbl;
	public JTextField patientAddressTF;
	public JTextField patientPhoneTF;
	public JComboBox<String> patientNameCB;
	Object[] invoiceColumnNames = {"Number","Date","Procedures","Total","Paid"};
	Object[] historyColumnNames = {"Number","Condition Name","Date occoured","Medication Recieved"};
	Object[][] invoice;
	Object[][] history;
	public JButton btnEditAddress = new JButton("Edit Address");
	public JButton btnEditPhone = new JButton("Edit Phone");
	public JButton btnRemovePatient = new JButton("Remove Patient");
	public String patientName;
	public int patientID;
	public JButton btnRemoveInvoice = new JButton("Remove Invoice");
	public JButton btnRemoveHistory = new JButton("Remove History");

	
	public ViewData() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][][][][grow]"));
		
		JLabel lblPatentName = new JLabel("Patent :");
		add(lblPatentName, "cell 0 1,alignx trailing");
		
		patientNameCB = new JComboBox<String>();
		patientNameCB.setEditable(true);
		add(patientNameCB, "cell 1 1,growx");
		patientNameCB.setSize(1,5);
		
		JLabel lblPatientAddress = new JLabel("Patient Address:");
		add(lblPatientAddress, "cell 0 2,alignx trailing");
		
		patientAddressTF = new JTextField();
		patientAddressTF.setEditable(false);
		add(patientAddressTF, "cell 1 2,growx");
		patientAddressTF.setColumns(10);
		
		JLabel lblPatientPhone = new JLabel("Patient Phone:");
		add(lblPatientPhone, "cell 0 3,alignx trailing");
		
		patientPhoneTF = new JTextField();
		patientPhoneTF.setEditable(false);
		add(patientPhoneTF, "cell 1 3,growx");
		patientPhoneTF.setColumns(10);
		
		JLabel lblInvoices = new JLabel("Invoices:");
		add(lblInvoices, "cell 0 4");
		
		JLabel lblHistory = new JLabel("History");
		add(lblHistory, "cell 1 4");
		
		invoiceTbl = new JTable();
		add(invoiceTbl, "flowy,cell 0 5,grow");
		
		historyTbl = new JTable();
		add(historyTbl, "flowy,cell 1 5,grow");
		add(btnEditAddress, "cell 0 5");
		add(btnEditPhone, "cell 0 5");
		add(btnRemoveInvoice, "cell 1 5");
		add(btnRemoveHistory, "cell 1 5");
		add(btnRemovePatient, "cell 1 5");

	}
	

	public void setComboLists(LinkedList<Patient> patientList)
	{
		
		for(int i = 0 ; i<patientList.size();i++)
		{
			patientNameCB.addItem(Integer.toString(patientList.get(i).getPatientNum())+". "+patientList.get(i).getPatientName());
		}

		
		
		repaint();
		
	}
	public void clearComboList(){
		patientNameCB.removeAllItems();
	}
	public void  setTables(LinkedList<Invoice> invoicesList, LinkedList<History> historyList)
	{
		DefaultTableModel invoiceModel = new DefaultTableModel();
		DefaultTableModel historyModel = new DefaultTableModel();
		invoice = new Object[invoicesList.size()][5];
		history = new Object[historyList.size()][5];
		
		invoiceModel.addColumn(invoiceColumnNames[0]);
		invoiceModel.addColumn(invoiceColumnNames[1]);
		invoiceModel.addColumn(invoiceColumnNames[2]);
		invoiceModel.addColumn(invoiceColumnNames[3]);
		invoiceModel.addColumn(invoiceColumnNames[4]);
		
		historyModel.addColumn(historyColumnNames[0]);
		historyModel.addColumn(historyColumnNames[1]);
		historyModel.addColumn(historyColumnNames[2]);
		historyModel.addColumn(historyColumnNames[3]);
		DateFormat shortDf = DateFormat.getDateInstance(DateFormat.SHORT);
		
		
		
		for(int i = 0 ; i < invoicesList.size() ; i++ )
		{
			System.out.println(invoicesList.get(i).getInvoiceNum());
			invoice[i][0]=invoicesList.get(i).getInvoiceNum();
			invoice[i][1]=shortDf.format(invoicesList.get(i).getInvoiceDate());
			invoice[i][2]=invoicesList.get(i).getProceduresString();
			invoice[i][3]=invoicesList.get(i).getInvoiceAmt();
			if(invoicesList.get(i).getInvoicePaid()==true)
				invoice[i][4]="Yes";
			else
				invoice[i][4]="No";
			invoiceModel.addRow(invoice[i]);
		}
		
		for(int i = 0 ; i < historyList.size() ; i++ )
		{
			history[i][0]=historyList.get(i).getHistID();
			history[i][1]=historyList.get(i).getConditionName();
			history[i][2]=shortDf.format(historyList.get(i).getDateOccoured());
			history[i][3]=historyList.get(i).getMedicationRecieved();
			historyModel.addRow(history[i]);
		}
		invoiceTbl.setModel(invoiceModel);
		historyTbl.setModel(historyModel);
		invoiceTbl.repaint();
		historyTbl.repaint();
		
		
	}
}
