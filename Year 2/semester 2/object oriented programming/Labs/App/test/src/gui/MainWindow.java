package gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import program.SurgeryMainDriver;
	
public class MainWindow {
	private static SurgeryMainDriver surgery = new SurgeryMainDriver();
	
	//main window frame
	private static JFrame frame;

//menu panel
	//menu fields
	private JMenu fileMenu = new JMenu("File");
	private JMenu reportMenu = new JMenu("Report");
	private JMenu helpMenu = new JMenu("Help");
	
	//file menu items
	private static JMenuItem loginMenuItem = new JMenuItem("Login");
	private static JMenuItem logoutMenuItem = new JMenuItem("Logout");
	private static JMenuItem backupMenuItem = new JMenuItem("Backup");
	private static JMenuItem restoreMenuItem = new JMenuItem("Restore");
	private static JMenuItem exitMenuItem = new JMenuItem("Exit");
			
	//report menu items
	private static JMenuItem reportMenuItem = new JMenuItem("Report");
			
	//help menu items
	private static JMenuItem aboutMenuItem = new JMenuItem("About");	
	
	
//patient list panel
	private static JList<String> patientListDisplay = new JList<String>();
	private static JPanel patientListPanel = new JPanel();
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	
//tabbed pane
	private static JTabbedPane tabbedPane = new JTabbedPane();
	
	public MainWindow()
	{	
		makeFrame();
		setJMenuItemsVisibility();
	}
	

	private void makeFrame()
    {
        frame = new JFrame("Doctor Surgery System");
        makeMenuBar();
        makePatientListPanel();
        makePatientDetailsPanel();
        
        Container contentPane = frame.getContentPane();
        
       /* JLabel label = new JLabel("I am a label. I can display some text.");
        contentPane.add(label);*/

        // building is done - arrange the components and show  
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {	//menu bar
    	JMenuBar mainMenuBar = new JMenuBar();
        frame.setJMenuBar(mainMenuBar);
        
      //add menu fields
		mainMenuBar.add(fileMenu);
		mainMenuBar.add(reportMenu);
		mainMenuBar.add(helpMenu);
		
		//add file menu items
		fileMenu.add(loginMenuItem);
		loginMenuItem.setMnemonic(KeyEvent.VK_I);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		        "Login item");
		
		fileMenu.add(logoutMenuItem);
		logoutMenuItem.setMnemonic(KeyEvent.VK_O);
		logoutMenuItem.getAccessibleContext().setAccessibleDescription(
		        "logout item");
		
		fileMenu.add(backupMenuItem);
		backupMenuItem.setMnemonic(KeyEvent.VK_B);
		backupMenuItem.getAccessibleContext().setAccessibleDescription(
		        "backup item");
		
		fileMenu.add(restoreMenuItem);
		restoreMenuItem.setMnemonic(KeyEvent.VK_R);
		restoreMenuItem.getAccessibleContext().setAccessibleDescription(
		        "restore item");
		
		fileMenu.add(exitMenuItem);
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.getAccessibleContext().setAccessibleDescription(
		        "exit item");
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		        "File menu");
		
		//add report menu items
		reportMenu.add(reportMenuItem);
		reportMenu.setMnemonic(KeyEvent.VK_R);
		reportMenu.getAccessibleContext().setAccessibleDescription(
		        "Report menu");
		
		//add help menu items
		helpMenu.add(aboutMenuItem);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription(
		        "Help menu");
		
		//add file menu action listeners
		loginMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					Login loginWindowInstance = new Login();
			}
		});
		logoutMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					surgery.logOut();
					setJMenuItemsVisibility();
			}
		});
		backupMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					surgery.saveSystemToFile();
					JOptionPane.showMessageDialog(null, "System saved."); 
			}
		});
		restoreMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					surgery.restoreSystemFromFile();
					surgery.listPatients();
					JOptionPane.showMessageDialog(null, "System restored."); 
			}
		});
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				System.exit(0);
			}
		});
    }
    
    /**
     * 
     */
    public static void makePatientListPanel(){
    	//JList scroll pane
		JScrollPane patientScrollPane = new JScrollPane(patientListDisplay);
		//patientListDisplay.setBackground(Color.WHITE);
		patientListDisplay.setVisibleRowCount(15);

		patientScrollPane.setPreferredSize(new Dimension(190, 520));		//190 ensures the width
		
		patientListPanel.setLayout(new BorderLayout());
		patientListPanel.add(patientScrollPane);
		patientListPanel.setVisible(false);
		
    	frame.add(patientListPanel, BorderLayout.WEST);
    }
    
    /**
     * 
     */
    public static void makePatientDetailsPanel(){
    	//tabs

    	ImageIcon icon = createImageIcon("images/middle.gif");
    	Tabbed panel1 = new Tabbed(1); //1 for adding tab
    	JComponent patientPanel1 = panel1.getJoinPanel(); 
    	tabbedPane.addTab("Create Patient", icon, patientPanel1, "Tab to add patient");
    	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    	
    	Tabbed panel2 = new Tabbed(2); //2 for search tab
    	JComponent patientPanel2 = panel2.getJoinPanel(); 
    	tabbedPane.addTab("Search & Update Patient", icon, patientPanel2, "Tab to search for patient");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    	frame.add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param patientsArray
     */
    public static void setPatientListModel(String [] patientsArray){
    	model.removeAllElements();
    	for (int i = 0; i < patientsArray.length; i++)
    	{
    		model.addElement(patientsArray[i]);
    	}
		MainWindow.model = model;
		patientListDisplay.setModel(model);
	}
    
    /**
     * 
     */
    public static void setJMenuItemsVisibility(){
		if (surgery.getLoginStatus()){
			patientListPanel.setVisible(true);
			tabbedPane.setVisible(true);
			loginMenuItem.setEnabled(false);
			logoutMenuItem.setEnabled(true);
			backupMenuItem.setEnabled(true);
			restoreMenuItem.setEnabled(true);
			reportMenuItem.setEnabled(true);
		}
		else{
			patientListPanel.setVisible(false);
			tabbedPane.setVisible(false);
			loginMenuItem.setEnabled(true);
			logoutMenuItem.setEnabled(false);
			backupMenuItem.setEnabled(false);
			restoreMenuItem.setEnabled(false);
			reportMenuItem.setEnabled(false);
		}
	}
    
    /**
     * gets icon for tab
     * @param path
     * @return
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}