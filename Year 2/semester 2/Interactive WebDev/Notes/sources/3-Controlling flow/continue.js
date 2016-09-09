function init()
{
 var panel = document.getElementById( "panel" ) ;
 var i , j ;
 for ( i = 1 ; i < 11 ; i++ )
 {
  if ( i === 2 || i === 7 ) { continue ; }
  panel.innerHTML += "Loop iteration: " + i + "<br>" ;
 }
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;