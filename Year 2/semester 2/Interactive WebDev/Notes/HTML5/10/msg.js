/* HTML5 in easy steps:example 83.2 */

function init() 
{ 
  document.getElementById("host").innerHTML+=document.domain;
  addEventListener("message", readMsg, false);
}
onload=init;

function sendMsg()
{
	var win=document.getElementById("ifr").contentWindow;
	win.postMessage("Message Received from: "+document.domain,"http://xdoc.bravehost.com");
}

function readMsg(event)
{  
    if (event.origin === "http://localhost")
    document.getElementById("para").innerHTML=( event.data );
}