class If
{
	public static void main( String[] args )
	{
		if ( 5 > 1 ) System.out.println( "Five is greater than one." );		

		if ( 2 < 4 )
		{
			System.out.println( "Two is less than four." );
			System.out.println( "Test succeeded." );
		}

		int num = 8;

		if ( (( num > 5 ) && ( num < 10 )) || ( num == 12 ) )
		System.out.println( "Number is 6-9 inclusive, or 12" ); 
	}
}
