import javax.swing.*;

class Radios extends JFrame 
{
	JPanel pnl = new JPanel();
	
	JRadioButton rad1 = new JRadioButton( "Red", true ) ;
	JRadioButton rad2 = new JRadioButton( "Rosé" ) ;
	JRadioButton rad3 = new JRadioButton( "White" ) ;

	ButtonGroup wines = new ButtonGroup() ;	

	public Radios()
	{
		super( "Swing Window" );
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);

		wines.add( rad1 ) ;
		wines.add( rad2 ) ;
		wines.add( rad3 ) ;

		pnl.add( rad1 ) ;
		pnl.add( rad2 ) ;
		pnl.add( rad3 ) ;

		setVisible( true );
	}

	public static void main ( String[] args )
	{
		Radios gui = new Radios();
	} 
}
