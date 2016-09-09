class StringLength
{
	public static void main( String[] args )
	{
		String lang = "Java";

		String series = " in easy steps";

		String title = lang.concat( series );

		System.out.print( "\""+title + "\" has ");
		System.out.println( title.length()+" characters" );
	}
}
