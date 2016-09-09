class Random
{
	public static void main( String[] args )
	{
		float random = (float) Math.random()  ;
		System.out.println("Random number: " + random );
		
		float multiplied = random * 10;
		System.out.println("Multiplied number: " + multiplied );
		
		int randomInt = (int) Math.ceil( multiplied );
		System.out.println("Random Integer: " + randomInt );
	}
}
