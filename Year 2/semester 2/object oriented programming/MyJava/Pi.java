class Pi
{
	public static void main( String[] args )
	{
		float radius = Float.parseFloat( args[0] );
		
		float shortPi = (float) Math.PI;

		float circ = shortPi * ( radius + radius );

		float area = shortPi * (radius * radius); 

		System.out.print("With Pi commuted from " + Math.PI );
		System.out.println(" to " + shortPi + "...");
		System.out.println("A circle of radius " + radius + " cm" );
		System.out.println("has a circumference of " + circ + " cm");
		System.out.println("and an area of " + area + " sq.cm");
	}
}
