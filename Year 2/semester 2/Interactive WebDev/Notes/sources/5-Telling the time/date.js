// US DST 2013 is from Sunday March 10 to Sunday November 3.
function isDst(d)
{
  var dst = 60;	// DST on by default.

  // Up until March 9 switch off DST.
  if( (d.getMonth() < 3 ) && (d.getDate() < 10) ) { dst = 0 ; }
  
  // After November 2 switch off DST.
  if( (d.getMonth() > 9 ) && (d.getDate() > 2) ) { dst = 0 ; }

  return dst ;
}

function init()
{
 var panel = document.getElementById( "panel" ) ;
 var now = new Date() ; 
 var offset = now.getTimezoneOffset() ;
 var dst = isDst( now ) ;
 switch ( offset )
 {
  case (300 - dst ) : offset = "East Coast" ; break ;
  case (360 - dst ) : offset = "Central" ; break ;
  case (420 - dst ) : offset = "Mountain" ; break ;
  case (480 - dst ) : offset = "Pacific" ; break ;
  default : offset = "all" ;
 } 
 panel.innerHTML = "System Time: " + now.toString() ;
 panel.innerHTML += "<br>UTC (GMT) Time: "+ now.toUTCString() + "<hr>Welcome to "+offset+ " visitors" ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;




