class Power
{
	public static void main( String[] args )
	{
		int num = Integer.parseInt( args[0] );
		int square = (int) Math.pow( num, 2 );
		int cube = (int) Math.pow( num, 3 );
		int sqrt = (int) Math.sqrt( num );

		System.out.println( num +" squared is "+square );
		System.out.println( num +" cubed is " +cube );
		System.out.println( "Square root of "+num+" is "+sqrt );
	}
}
