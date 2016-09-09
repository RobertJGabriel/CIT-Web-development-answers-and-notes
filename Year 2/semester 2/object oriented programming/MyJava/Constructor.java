class Car
{
	private String maker ;
	private String color ;
	private String bodyType ;

	public Car()
	{
		maker = "Porsche";
		color = "Silver";
		bodyType = "Coupe";
	}

	private String accelerate()
	{
		String motion = "Accelerating..." ;
		return motion;
	}

	public void setCar( String brand, String paint, String style )
	{
		maker = brand ;
		color = paint;
		bodyType = style;
	}

	public void getCar()
	{
		System.out.println( maker +" paint is " + color );
		System.out.println( maker + " style is " + bodyType );
		System.out.println( maker + " is " + accelerate() +"\n" );
	}	
}

class Constructor
{
	public static void main(String[] args)
	{
		Car Porsche = new Car();
		Porsche.getCar();

		Car Ferrari = new Car();
		Ferrari.setCar("Ferrari","Red","Sport");
		Ferrari.getCar();
	}
}


