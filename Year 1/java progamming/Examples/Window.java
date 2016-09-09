import javax.swing.*;

class Window extends JFrame 
{
	JPanel pnl = new JPanel();
	
	public Window()
	{
		super("Swing Window");
		setSize( 500,200 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(pnl);
		setVisible( true );
	}

	public static void main ( String[] args )
	{
		Window gui = new Window();
	} 
}
