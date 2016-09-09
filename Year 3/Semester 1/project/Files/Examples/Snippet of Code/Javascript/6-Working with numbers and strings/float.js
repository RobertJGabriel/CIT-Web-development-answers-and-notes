
function init()
{
 var panel=document.getElementById("panel");

 panel.innerHTML = "Ceiling: " + Math.ceil( 7.5 ) ;
 panel.innerHTML += "<br>Floor: " + Math.floor( 7.5 ) ;
 panel.innerHTML += "<br>Round+: " + Math.round( 7.5 ) ;
 panel.innerHTML += "<br>Round-: " + Math.round( -7.5 ) ;


 panel.innerHTML += "<hr>Inprecision: " + (81.66 * 15)  ;
 panel.innerHTML += "<br>Corrected: " + ((81.66 * 100) * 15) /100;

 panel.innerHTML += "<hr>Float: " + Math.PI ;
 panel.innerHTML += "<br>Commuted: " + (Math.round( Math.PI * 100 ) ) / 100;

}
window.onload=init;


