// 2
import javax.swing.* ;
import java.awt.event.* ;

// 1 & 3
class Actions extends JFrame implements ActionListener
{
	JPanel pnl = new JPanel() ;

	// 4
	JButton btn1 = new JButton( "Button 1" ) ;
	JButton btn2 = new JButton( "Button 2" ) ;
	JTextArea txtArea = new JTextArea( 5 , 38 ) ;

	// 1
	public Actions()
	{
		super("Swing Window");
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);

		// 5
		pnl.add( btn1 ) ;
		pnl.add( btn2 ) ;
		pnl.add( txtArea ) ;
		
		// 6
		btn2.setEnabled( false ) ;
		txtArea.setText( "Button 2 is Disabled" ) ;

		// 7
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		setVisible( true );
	}

	// 8
	public void actionPerformed( ActionEvent event )
	{
		txtArea.setText( event.getActionCommand() + " Clicked and Disabled" ) ;
		
		// 9
		if( event.getSource() == btn1) 
		{
			btn2.setEnabled( true ); 
			btn1.setEnabled( false ) ;
		}		
		
		if( event.getSource() == btn2)
		{
			btn1.setEnabled( true );
			btn2.setEnabled( false ) ;
		}	
	}

	public static void main( String[] args )
	{
		// 1
		Actions gui = new Actions();
	}
}
