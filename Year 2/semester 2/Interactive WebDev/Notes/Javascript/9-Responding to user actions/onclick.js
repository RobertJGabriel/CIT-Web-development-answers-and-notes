function clickResponse()
{ 
  this.innerHTML += "Click detected<hr>";  
}

function doubleclickResponse()
{ 
  this.innerHTML += "Double-click detected<br>"; 
}

function mousedownResponse()
{ 
  this.innerHTML += "Mouse button down<br>"; 
}

function mouseupResponse()
{ 
  this.innerHTML += "Mouse button up<br>"; 
}

function init()
{
 var panel=document.getElementById("panel"); 
 panel.innerHTML="Click Here &gt;<br>";

 panel.onclick=clickResponse;
 panel.ondblclick=doubleclickResponse;
 panel.onmouseup=mouseupResponse;
 panel.onmousedown=mousedownResponse; 

}
onload=init;

