function init()
{
  var panel = document.getElementById( "panel" ) ;
  panel.innerHTML = "<a name='frag'>Fragment Anchor</a>" ;
  var jump = confirm( "Jump to fragment?" ) ;
  if ( jump ) { location = location.href + "#frag" ; }
  panel.innerHTML += "<hr>Href: " + location.href ;
  panel.innerHTML += "<br>Protocol: " + location.protocol ;
  panel.innerHTML += "<br>Host: " + location.host ;
  panel.innerHTML += "<br>Path: " + location.pathname ;
  panel.innerHTML += "<br>Hash: " + location.hash ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;