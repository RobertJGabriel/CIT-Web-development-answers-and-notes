class Args
{
	public static void main( String[] args )
	{
		if(args.length != 3)
		{
			System.out.println("Wrong number of arguments"); 
			return;
		}

		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[2]);

		String msg = args[0] + args[1] + args[2] + "=";

		if( args[1].equals("+") ) 	msg += (num1 + num2);
		else if( args[1].equals("-") ) 	msg += (num1 - num2);
		else if( args[1].equals("x") ) 	msg += (num1 * num2);
		else if( args[1].equals("/") ) 	msg += (num1 / num2);
		else msg = "Incorrect operator";

		System.out.println( msg );
	}
}
