class Lottery
{
	public static void main( String[] args )
	{
		int[] nums = new int[50];
			

		// Fill elements 1-49 with integers 1-49.
		for( int i = 1; i < 50; i++ ) { nums[i] = i; }
		
		// Shuffle the values in elements 1-49.
		for( int i = 1; i < 50; i++ )
		{
			int r = (int) Math.ceil( Math.random() * 49 ) ;
			int temp=nums[i]; nums[i]=nums[r]; nums[r]=temp;
		}

		// Display the values in elements one to six.
		for ( int i = 1; i < 7; i++ )
		{
			System.out.print(Integer.toString(nums[ i ]) + "  ");
			if(i % 10 == 0) System.out.println();
		}
	}
}
