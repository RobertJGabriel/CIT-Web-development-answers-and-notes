import javax.swing.*;

class TextFields extends JFrame 
{
	JPanel pnl = new JPanel();

	JTextField txt1 = new JTextField( 38 ) ;
	JTextField txt2 = new JTextField( "Default Text", 38 ) ;
	
	JTextArea txtArea = new JTextArea( 5, 37 ) ;
	
	JScrollPane pane = new JScrollPane( txtArea ) ;

	public TextFields()
	{
		super( "Swing Window" );
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);

		txtArea.setLineWrap( true ) ;
		txtArea.setWrapStyleWord( true ) ;

		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnl.add( txt1 ) ;
		pnl.add( txt2 ) ;
		pnl.add( pane ) ;

		setVisible( true );
	}

	public static void main ( String[] args )
	{
		TextFields gui = new TextFields();
	} 
}
