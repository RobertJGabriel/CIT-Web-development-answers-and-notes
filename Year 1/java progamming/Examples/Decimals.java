import java.math.BigDecimal ;

public class Decimals
{
	public static void main(String[] args)
	{

	java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance();

	// VARIABLES...
    double item = 0.70 , rate = 0.05;
	double tax = item * rate;
	double total = item + tax;	

	// BigDecimal OBJECTS...
	// COMMENT OUT THE VARIABLES AND UNCOMMENT THIS BLOCK TO SOLVE THE PROBLEM.
/*
	BigDecimal item = new BigDecimal(0.70);
	BigDecimal rate = new BigDecimal(0.05);
	BigDecimal tax = item.multiply(rate);
	BigDecimal total = item.add(tax );	
*/

	// OUTPUT FORMATTED VALUES...
	System.out.println("\nItem :\t"  + cf.format(item)  ) ;
	System.out.println("Tax :\t"   + cf.format(tax)   ) ;
	System.out.println("Total :\t" + cf.format(total) ) ;

		
	// OUTPUT UNFORMATTED VALUES...
	// UNCOMMENT THIS BLOCK TO SEE THE CAUSE OF THE PROBLEM...
/*
	System.out.println("\nItem :\t"  + item  ) ;
	System.out.println("Tax :\t"   + tax   ) ;
	System.out.println("Total :\t" + total ) ;		
*/
	}
}

