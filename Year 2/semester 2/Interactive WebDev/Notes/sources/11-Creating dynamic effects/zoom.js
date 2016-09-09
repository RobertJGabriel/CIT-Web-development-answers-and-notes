var box;

function zoomIn()
{
  var filename = this.src.split( "_thumb.png");
  box.style.background="url("+filename[0]+ ".png)";
}


function zoomOut()
{
    box.style.background="inherit";
}


function init()
{
 box=document.getElementById("zoomBox");
 
 var heli = document.getElementById("heli");
 heli.onmouseover=zoomIn;
 heli.onmouseout=zoomOut;

 var car = document.getElementById("car");
 car.onmouseover=zoomIn;
 car.onmouseout=zoomOut;
}
document.addEventListener("DOMContentLoaded", init , false ) ;

