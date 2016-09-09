class Assignment
{
	public static void main( String[] args )
	{
		String txt = "Fantastic ";
		
		String lang = "Java";

		txt += lang;
		System.out.println("Add & Assign Strings: " + txt );

		int sum = 10;
		int num = 20;
		sum += num;
		System.out.println("Add & Assign Integers: " + sum );

		int factor = 5;
		sum *= factor;
		System.out.println("Multiplication sum: " + sum );

		sum /= factor;
		System.out.println("Division sum: " + sum );
	}
}
