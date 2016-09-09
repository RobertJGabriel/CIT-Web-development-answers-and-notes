class FirstInstance
{
	public static void main( String[] args )
	{
		System.out.println( "Car paint is " + Car.color ) ;
		System.out.println( "Car style is " + Car.bodyType ) ;
		System.out.println( Car.accelerate() ) ;

		Car Porsche = new Car() ;

		System.out.println( "Porsche paint is " + Car.color ) ;
		System.out.println( "Porsche style is " + Car.bodyType ) ;
		System.out.println( Porsche.accelerate() ) ;
	}
}

