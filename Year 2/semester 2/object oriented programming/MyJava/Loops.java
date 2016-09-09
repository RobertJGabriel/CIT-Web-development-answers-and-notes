class Loops
{
	public static void main( String[] args )
	{
		if( args.length > 0 )
		{
			for( int i = 0; i < args.length; i++ )
			{

			System.out.println( "args[" +i+ "] is | " + args[i] );

			}
		}

		String[] htm = { "HTML5", "in", "easy","steps" };

		int j = 0;

		while( j < htm.length )
		{
			System.out.println("htm[" +j+ "] is | " + htm[j] );
			j++;	
		}

		String[] xml = { "XML", "in", "easy","steps" };

		int k = 0;

		if( xml.length > 0 )
		do
		{
			System.out.println( "\t\txml[" +k+ "] is | " + xml[k] );
			k++;
		} while( k < xml.length ); 
	}
}
