
function init()
{
 var panel=document.getElementById("panel");


 // MIKE'S NOTE: Conditional test added to remind that this example requires HTTP request.
 if( location.protocol !== "http:" ) 
 { 
   panel.innerHTML = "Please run this example via a web server<br>using HTTP to see all parts of the location."; 
 } 
 else 
 {

   panel.innerHTML = "<a name='frag'>Fragment Anchor</a>";

   var jump = confirm( "Jump to fragment?");
   if (jump) { location = location.href + "#frag"; }

   panel.innerHTML += "<hr>Href: " + location.href;
   panel.innerHTML += "<br>Protocol: " + location.protocol;
   panel.innerHTML += "<br>Host: " + location.host;
   panel.innerHTML += "<br>Path: " + location.pathname;
   panel.innerHTML += "<br>Hash: " + location.hash;

 }

}
onload=init;


