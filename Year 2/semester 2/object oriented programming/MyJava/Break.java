class Break
{
	public static void main( String[] args )
	{	
		for ( int i = 1; i < 4 ; i++ )
		{
			for ( int j = 1; j < 4 ; j++ )
			{	
				if ( i == 1 && j == 1 )
				{
					System.out.println( "Continues innerLoop when i=" +i+ " j="+j );	
					continue;
				}
				
				if ( i == 2 && j == 1 )
				{
					System.out.println( "Breaks innerLoop when i=" +i+ " j="+j );	
					break;
				}

				System.out.println( "Running i=" +i+ " j="+j );
			}
		}
	}
}
