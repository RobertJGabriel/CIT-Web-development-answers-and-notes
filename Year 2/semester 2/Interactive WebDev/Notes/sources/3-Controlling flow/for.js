function init()
{
 var i , panel = document.getElementById( "panel" ) ;
 for ( i = 1 ; i < 11 ; i++ )
 {
   panel.innerHTML += "Iteration number: " + i + "<br>" ;
 }
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;