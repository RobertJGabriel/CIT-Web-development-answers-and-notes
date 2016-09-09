function errorhandler( msg , url , ln )
{
  alert( "Error: " + msg + "\nIn File: " + url + "\nAt Line: " + ln ) ;
  return true ;
}
onerror = errorhandler ;

function init()
{
 var panel = document.getElementById( "panel" ) ;
 panel.innerHTML = "Handling an error..." ;
 document.getElementById( "btn" ).value = "myButton" ; 
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;s