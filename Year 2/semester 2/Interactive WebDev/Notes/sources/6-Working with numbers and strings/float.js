function init()
{
 var panel = document.getElementById( "panel" ) ;
 panel.innerHTML = "Ceiling: " + Math.ceil( 7.5 ) 
 + "<br>Floor: " + Math.floor( 7.5 ) 
 + "<br>Round+: " + Math.round( 7.5 ) 
 + "<br>Round-: " + Math.round( -7.5 ) ;
 panel.innerHTML += "<hr>Inprecision: " + ( 81.66 * 15 )  
 + "<br>Corrected: " + ( ( 81.66 * 100 ) * 15 ) / 100 ;
 panel.innerHTML += "<hr>Float: " + Math.PI 
 + "<br>Commuted: " + ( Math.round( Math.PI * 100 ) ) / 100 ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;