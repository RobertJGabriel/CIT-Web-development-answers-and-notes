import java.io.* ;

class ListFiles
{
	public static void main( String[] args )
	{
		File dir = new File( "data" ) ;

		if( dir.exists() )
		{
			String[] files = dir.list() ;
			System.out.println( files.length + " files found..." );

			for( int i = 0; i < files.length; i++ )
			{
				System.out.println( files[i] ) ;

			}
		}
		else
		{
			System.out.println( "Folder not found." ) ;
		}
	}
}
