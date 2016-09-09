package test;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class swing extends JFrame implements ActionListener{
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	public swing (){
		mypanel = new JPanel();
		mybutton = new JButton("Ok");
		mybutton.addActionListener(this);
		mylabel = new JLabel();
		mypanel.add(mybutton);
		mypanel.add(mylabel);
		this.add(mypanel);
	}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == mybutton)
		{
			mylabel.setText("My BUTTON WAS CLICKED");
		}
	}}