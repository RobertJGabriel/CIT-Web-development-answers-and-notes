class Convert
{
	public static void main( String[] args )
	{
		float daysFloat = 365.25f;
		String weeksString = "52";

		int daysInt = (int) daysFloat;

		int weeksInt = Integer.parseInt( weeksString );

		int week = ( daysInt / weeksInt );

		System.out.println( "Days per week: " + week );
	}
}
