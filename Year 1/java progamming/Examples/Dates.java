import java.util.Calendar;

public class Dates 
{
	public static void main(String[] args) 
	{
		Calendar cal = Calendar.getInstance();
cal.set( Calendar.YEAR, 2012 );
        System.out.println("\nIt is now " + cal.getTime() );

		cal.set( Calendar.YEAR, 2015 );
		System.out.println("\nDate is now " + cal.getTime() );

		String fields = "\nYear:\t\t\t" + cal.get(Calendar.YEAR);
		fields += "\nMonth:\t\t\t"  + cal.get(Calendar.MONTH);
		fields += "\nDay of the month:\t" + cal.get(Calendar.DAY_OF_MONTH);
		fields += "\nDay of the week:\t"  + cal.get(Calendar.DAY_OF_WEEK);
		fields += "\nDay of the year:\t"  + cal.get(Calendar.DAY_OF_YEAR);
		fields += "\nWeek of the year:\t" + cal.get(Calendar.WEEK_OF_YEAR);
		fields += "\nWeek of the month:\t" + cal.get(Calendar.WEEK_OF_MONTH);
		fields += "\nDay of week in month:\t" + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		fields += "\nHour (0-11):\t\t" + cal.get(Calendar.HOUR);
		fields += "\nA.M.(0) or P.M.(1):\t" + cal.get(Calendar.AM_PM);
		fields += "\nHour (0-23):\t\t" + cal.get(Calendar.HOUR_OF_DAY);
		fields += "\nMinute:\t\t\t" + cal.get(Calendar.MINUTE);
		fields += "\nSecond:\t\t\t" + cal.get(Calendar.SECOND);
		System.out.println( fields ) ;
	}
}


