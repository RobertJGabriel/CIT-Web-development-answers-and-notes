var panel, flag;

function mousemoveResponse(e)
{ 
 var x, y;

  if( window.event )
  { 
    x= event.x; y=event.y; 
  }
  else if(e)
  { 
    x=e.pageX; y=e.pageY; 
  }

  if(flag)
  {
    panel.innerHTML = "Mouse is at X: " + x + ", Y: " + y ; 
  } 
}

function mouseoverResponse()
{  
  flag = false; 
  panel.innerHTML = "Mouse is Over"; 
}

function mouseoutResponse()
{ 
  flag = true ;
}

function init()
{
 panel=document.getElementById("panel");
 flag = true;
 panel.innerHTML="Move the mouse..." ;
 
 document.onmousemove=mousemoveResponse;
 panel.onmouseover=mouseoverResponse; 
 panel.onmouseout=mouseoutResponse; 

}
onload=init;

