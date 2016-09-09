class Elements
{
	public static void main( String[] args )
	{
		int[] kiosk_q1  = { 42000, 48000, 50000 };
		int[] kiosk_q2  = { 52000, 58000, 60000 };
		int[] kiosk_q3  = { 46000, 49000, 58000 };
		int[] kiosk_q4  = { 50000, 51000, 61000 };

		int[] outlet_q1 = { 57000, 63000, 60000 };
		int[] outlet_q2 = { 70000, 67000, 73000 };
		int[] outlet_q3 = { 67000, 65000, 62000 };
		int[] outlet_q4 = { 72000, 69000, 75000 };

		int[] sum = new int[ 12 ];
		int total = 0;

		for( int i = 0; i < kiosk_q1.length; i++ )
		{
			sum[i]   = kiosk_q1[i] + outlet_q1[i];
			sum[i+3] = kiosk_q2[i] + outlet_q2[i];
			sum[i+6] = kiosk_q3[i] + outlet_q3[i];
			sum[i+9] = kiosk_q4[i] + outlet_q4[i];
		} 

		
		for( int i = 0; i < sum.length; i++ ) 
		{
			System.out.println( "Month "+(i+1)+" sales:\t" + sum[i]);
			total += sum[i];
		} 

		System.out.println( "TOTAL YEAR SALES\t" + total);
	}
}
