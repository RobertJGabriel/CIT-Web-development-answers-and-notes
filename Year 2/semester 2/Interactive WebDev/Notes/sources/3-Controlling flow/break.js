function init()
{
 var panel = document.getElementById( "panel" ) ;
 var i , j ;

 quitLoop:

 for( i = 1 ; i < 4 ; i++ )
 {
  panel.innerHTML += "<dt>Outer loop: " + i ;
  for( j = 1 ; j < 4 ; j++ )
  {
    // if( j === 2 ) { break ; }

    // if( j === 2 ) { break quitLoop ; }

    panel.innerHTML += "<dd>Inner loop: " + j ;
  }
 }
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;