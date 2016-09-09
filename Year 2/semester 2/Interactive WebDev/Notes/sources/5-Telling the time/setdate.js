function init()
{
 var panel = document.getElementById( "panel" ) ;
 var hol = new Date( 2014 , 6 , 4 ) ;
 panel.innerHTML = "Object: " + hol ;
 hol.setFullYear( 2013 ) ;
 hol.setMonth( 11 ) ;
 hol.setDate( 25 ) ;
 hol.setHours( 12 ) ;
 hol.setMinutes( 0 ) ;
 hol.setSeconds( 0 ) ;
 hol.setMilliseconds( 0 ) ;
 panel.innerHTML += "<br>String: " + hol.toString() ;
 panel.innerHTML += "<br>UTC: " + hol.toUTCString() ;
 panel.innerHTML += "<br>Locale: " + hol.toLocaleString() ;
 panel.innerHTML += "<br>Date: " + hol.toDateString() ;
 panel.innerHTML += "<br>Time: " + hol.toTimeString() ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;