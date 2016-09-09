function init()
{
 var panel = document.getElementById( "panel" ) ;
 var days = [ "Sun" , "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat" ] ;
 var months = [ "Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec" ] ;
 var now = new Date() ;
 var yy = now.getFullYear() ; // Year.
 var mm = now.getMonth() ; // Month name index.
 var dd = now.getDate() ; // Day of the month number.
 var dy = now.getDay() ; // Day name index.
 mm = months[ mm ] ; // Convert to month name string.
 dy = days[ dy ] ; // Convert to day name string.
 var str = dy + ", " + mm + " " + dd + ", " + yy ;
 panel.innerHTML += "US Date String: " + str ;
 str = dy + ", " + dd + " " + mm + ", " + yy ;
 panel.innerHTML += "<br>UK Date String: " + str ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;