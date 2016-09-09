// 2
import javax.swing.* ;
import java.awt.event.* ;	

// 1 & 3
class Messages extends JFrame implements ActionListener
{
	JPanel pnl = new JPanel() ;

	// 4
	JButton btn1 = new JButton( "Show Information Message" ) ;	
	JButton btn2 = new JButton( "Show Warning Message" ) ;
	JButton btn3 = new JButton( "Show Error Message" ) ;

	// 1
	public Messages()
	{
		super( "Swing Window" );
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);

		// 5
		pnl.add( btn1 ) ;			
		pnl.add( btn2 ) ;
		pnl.add( btn3 ) ;
		
		// 6
		btn1.addActionListener(this);		
		btn2.addActionListener(this);
		btn3.addActionListener(this);
	
		setVisible( true );
	}

	// 7
	public void actionPerformed( ActionEvent event )
	{
		// 8
		if( event.getSource() == btn1) 
		JOptionPane.showMessageDialog( this,"Information...","Message Dialog",JOptionPane.INFORMATION_MESSAGE );	
		
		if( event.getSource() == btn2)
		JOptionPane.showMessageDialog( this,"Warning...","Message Dialog",JOptionPane.WARNING_MESSAGE);	
		
		if( event.getSource() == btn3)
		JOptionPane.showMessageDialog( this,"Error...","Message Dialog",JOptionPane.ERROR_MESSAGE);	
	}

	public static void main( String[] args )
	{
		// 1
		Messages gui = new Messages();
	}
}
