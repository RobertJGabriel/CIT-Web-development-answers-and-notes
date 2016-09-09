function sendMsg()
{
  var win=document.getElementById( "ifr" ).contentWindow ;
  win.postMessage( "Message Received from: " + document.domain , "http://www.example.com" ) ;
}

function readMsg( event )
{  
  if ( event.origin === "http://localhost" )
  document.getElementById( "para" ).innerHTML = ( event.data ) ;
}

function init() 
{ 
  document.getElementById( "host" ).innerHTML += document.domain ;
  window.addEventListener( "message" , readMsg , false ) ;
  document.getElementById( "btn" ).onclick = sendMsg ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;