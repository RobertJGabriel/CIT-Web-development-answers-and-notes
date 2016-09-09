function init()
{
 var flag = true ;

 if ( flag )
 {
  document.getElementById( "panel" ).innerHTML = "Power is On" ;
 }

 if ( 7 > 2 )
 {
   document.getElementById( "panel" ).innerHTML += "<br>Success" ;
 }

 if ( 7 < 2 )
 {
   document.getElementById( "panel" ).innerHTML += "<br>Failure" ;
 }

}
document.addEventListener( "DOMContentLoaded" , init , false ) ;