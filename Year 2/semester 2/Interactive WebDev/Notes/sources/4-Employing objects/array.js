function init()
{
 var panel = document.getElementById( "panel" ) ;
 var summer = [ "June" , "July" , "August" ] ;
 for( month in summer ) 
 { 
   if( summer[ month ] !== "" )
   {
     panel.innerHTML += ( month + ": " + summer[ month ] + "<br>" ) ;
   }
 }
 panel.innerHTML += "Vacation month: " + summer[ 2 ] ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;

