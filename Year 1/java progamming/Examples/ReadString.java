import java.io.*; 

class ReadString 
{ 
	public static void main (String[] args) 
	{ 
      		System.out.print( "Enter the title of this book: " );

      		InputStreamReader isr = new InputStreamReader( System.in );
      		BufferedReader buffer = new BufferedReader( isr ); 
      		String input = "";

      		try
		{ 
         		input = buffer.readLine();
			buffer.close() ; 
      		} 
		catch (IOException e ) 
		{ 
         		System.out.println(e);  
      		} 

      		System.out.println("\nThanks, you are reading " + input ); 
	} 
} 


