package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class SaveData extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	public JCheckBox chckbxSaveToFile = new JCheckBox("File");
	public JCheckBox chckbxSaveToDatabase = new JCheckBox("Database");
	/**
	 * Create the panel.
	 */
	public SaveData() {
		setLayout(new MigLayout("", "[][]", "[][][][][][][][][][]"));
		
		
		add(chckbxSaveToFile, "cell 0 0");
		add(chckbxSaveToDatabase, "cell 0 1");

	}

}
