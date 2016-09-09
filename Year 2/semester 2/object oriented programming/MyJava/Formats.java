import java.text.NumberFormat ;
import java.text.SimpleDateFormat;

public class Formats
{
	public static void main(String[] args) 
	{		
		NumberFormat nf = NumberFormat.getNumberInstance();
		System.out.println( "\nNumber : " + nf.format(123456789) );
		
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		System.out.println( "\nCurrency : " + cf.format(1234.50f) );

		NumberFormat pf = NumberFormat.getPercentInstance();
		System.out.println( "\nPercent : " +  pf.format(0.75f) );

		java.util.Calendar cal = java.util.Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("M/d/y");
		System.out.println( "\nDate : " + df.format( cal.getTime() ) );

		SimpleDateFormat tf = new SimpleDateFormat("H:m");
		System.out.println( "\nTime : " + tf.format( cal.getTime() ) );
	}
}
