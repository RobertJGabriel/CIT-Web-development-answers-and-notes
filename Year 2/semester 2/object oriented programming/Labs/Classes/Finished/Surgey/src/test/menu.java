package test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.util.ArrayList;

import javax.swing.*;

public class menu extends JFrame implements ActionListener {
	private Surgey home ;
    public static void main(String[] s) {
        new  menu();
        
    }
 
    public  menu() {
    	 super("Doctor lOGIN");
         addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
        // Name the JMenu & Add Items
        JMenu menu = new JMenu("Doctor");
        menu.add(makeMenuItem("Login"));
        menu.add(makeMenuItem("Save"));
        menu.add(makeMenuItem("Quit"));
        
        JMenu menu2 = new JMenu("Patent");
        menu2.add(makeMenuItem("Open"));
        menu2.add(makeMenuItem("Save"));
        menu2.add(makeMenuItem("Quit"));
 
        
        
        
        
        
        // Add JMenu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(menu2);
        setJMenuBar(menuBar);
        setSize(500, 400);
        setLocation(200, 200);
        setVisible(true);
        
        Color c = new Color(41, 128, 185);
		Container con = this.getContentPane();
		con.setBackground(c);
		setResizable(false);
    }
 
    public void actionPerformed(ActionEvent e) {
 
        // Menu item actions
        String command = e.getActionCommand();
 
        if (command.equals("Quit")) {
            System.exit(0);
        } else if (command.equals("Login")) {
            // Open menu item action
        	home.login();
        } else if (command.equals("Save")) {
            // Save menu item action
            System.out.println("Save menu item clicked");
        }
    }
 
    private JMenuItem makeMenuItem(String name) {
        JMenuItem m = new JMenuItem(name);
        m.addActionListener(this);
        return m;
    }
}