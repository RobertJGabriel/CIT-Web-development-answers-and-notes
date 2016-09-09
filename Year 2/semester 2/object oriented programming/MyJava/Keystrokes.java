// 1
import javax.swing.*;
import java.awt.event.* ;	

// 1 & 2
class Keystrokes extends JFrame implements KeyListener 
{
	JPanel pnl = new JPanel();

	// 3
	JTextField field  = new JTextField( 38 ) ;	
	JTextArea txtArea = new JTextArea( 5, 38 ) ;

	// 1
	public Keystrokes()
	{
		super( "Swing Window" );
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);
		
		// 4
		pnl.add( field );	
		pnl.add( txtArea );
		
		// 5
		field.addKeyListener( this ) ; 
	
		setVisible( true );
	}

	// 6
	public void keyPressed( KeyEvent event )	
	{
		txtArea.setText("Key Pressed") ;
	}

	// 7
	public void keyTyped( KeyEvent event )	
	{
		txtArea.append("\nCharacter : " + event.getKeyChar()) ;
	}
	
	// 8
	public void keyReleased( KeyEvent event )	
	{
		int keyCode = event.getKeyCode();
		txtArea.append( "\nKey Code : " + keyCode );
		txtArea.append( "\nKey Text : " + event.getKeyText(keyCode));
	}


	public static void main ( String[] args )
	{
		// 1
		Keystrokes gui = new Keystrokes();
	} 
}

