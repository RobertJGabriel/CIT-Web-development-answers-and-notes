function init()
{
  document.getElementById( "panel" ).innerHTML = "Page Intialized" ;
  window.alert( "DOM Loaded" ) ;
}


function loaded()
{
  window.alert( "Window Loaded" ) ;
}

document.addEventListener( "DOMContentLoaded" , init , false ) ;
window.onload = loaded ;

