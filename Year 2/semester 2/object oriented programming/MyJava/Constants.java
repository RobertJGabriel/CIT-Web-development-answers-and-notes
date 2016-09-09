/*
	A program to demonstrate constant variables.
*/

class Constants
{
	public static void main( String args[] )
	{
		// Constant score values.
		final int TOUCHDOWN = 6;
		final int CONVERSION = 1;
		final int FIELDGOAL = 3;

		// Calculate points scored.		
		int td, pat, fg, total;
		td  = 4 * TOUCHDOWN;		// 4x6=24
		pat = 3 * CONVERSION;		// 3x1= 3
		fg  = 2 * FIELDGOAL;		// 2x3= 6
		total = ( td + pat + fg );	// 24+3+6=33

		// Output calculated total.
		System.out.println( "Score: " + total );
	}
}
