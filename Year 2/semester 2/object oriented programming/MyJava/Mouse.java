// 1
import javax.swing.* ;
import java.awt.event.* ;	

// 1 & 2
class Mouse extends JFrame implements MouseMotionListener, MouseListener 	
{
	JPanel pnl = new JPanel() ;

	// 3
	JTextArea txtArea = new JTextArea( 8, 38 ) ;	
	int x, y ;

	// 1
	public Mouse()
	{
		super("Swing Window");
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);

		// 4
		pnl.add( txtArea ) ;
		txtArea.addMouseMotionListener(this);	
		txtArea.addMouseListener(this);

		setVisible( true );
	}

	// 5
	public void mouseMoved   ( MouseEvent event) { x = event.getX(); y = event.getY();	}		
	public void mouseDragged ( MouseEvent event) {	}

	// 6
	public void mouseEntered ( MouseEvent event) { txtArea.setText( "Mouse Entered" );	}
	public void mousePressed ( MouseEvent event) { txtArea.append( "\nMouse Pressed at X : " + x + " Y : " + y );	}	
	public void mouseReleased( MouseEvent event) { txtArea.append( "\nMouse Released") ;	}	
	public void mouseClicked ( MouseEvent event) {	}	
	public void mouseExited  ( MouseEvent event) {	}

	public static void main( String[] args )
	{
		// 1
		Mouse gui = new Mouse();
	}
}
