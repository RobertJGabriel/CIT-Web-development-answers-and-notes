function init()
{
 var panel = document.getElementById( "panel" ) ;
 var rad = 4 ;
 var area = Math.PI * ( rad * rad ) ; // PIr²
 var circ = 2 * ( Math.PI * rad ) ; // 2PIr
 panel.innerHTML = "Circle Radius:" + rad + " feet" ;
 panel.innerHTML += "<br>Area:" + area + " square feet" ;
 panel.innerHTML += "<br>Circumference:" + circ + " feet" ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;