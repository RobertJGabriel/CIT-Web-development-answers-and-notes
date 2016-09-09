class Car
{
	public final static String color = "Red" ;
	public final static String bodyType = "Coupe" ;

	public static String accelerate()
	{
		String motion = "Accelerating..." ;
		return motion;
	}
}

class FirstObject
{
	public static void main( String[] args )
	{
		System.out.println( "Paint is " + Car.color ) ;
		System.out.println( "Style is " + Car.bodyType ) ;
		System.out.println( Car.accelerate() ) ;
	}
}
