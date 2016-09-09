var panel;

function keydownResponse()
{ 
  panel.innerHTML += "<br>Key Pressed: " ;  
}

function keyupResponse()
{  
  panel.innerHTML += "<br>Key Released" ;  
}

function keypressResponse( e )
{ 
 var keynum = (window.event) ? event.keyCode : e.which;
 panel.innerHTML += String.fromCharCode(keynum) ;
}


function init()
{
 panel=document.getElementById("panel");
 panel.innerHTML="Press a key..." ;
 
 document.onkeydown=keydownResponse;
 document.onkeyup=keyupResponse; 
 document.onkeypress=keypressResponse; 

}
onload=init;

