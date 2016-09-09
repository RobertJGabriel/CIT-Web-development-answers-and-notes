// 2
import javax.swing.*;
import java.awt.event.*;	

// 1 & 3
class Sound extends JFrame implements ActionListener	
{
	JPanel pnl = new JPanel();
	
	// 4
	ClassLoader ldr = this.getClass().getClassLoader();	
	
	// 5
	java.applet.AudioClip audio = JApplet.newAudioClip( ldr.getResource("flourish.mid") );
	
	// 6
	JButton playBtn = new JButton("Play");	
	JButton stopBtn = new JButton("Stop");

	// 1
	public Sound()
	{
		super("Swing Window");
		setSize( 500, 200 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		add(pnl);
	
		// 7
		pnl.add( playBtn );
		pnl.add( stopBtn );
		
		// 8
		playBtn.addActionListener(this);	
		stopBtn.addActionListener(this);

		setVisible(true);		
	}

	// 9
	public void actionPerformed( ActionEvent event )	
	{
		// 10
		if (event.getSource() == playBtn) audio.play();
		
		// 11
		if (event.getSource() == stopBtn) audio.stop();
	}

	public static void main(String[] args)
	{
		// 1
		Sound snd = new Sound();
	}
}
