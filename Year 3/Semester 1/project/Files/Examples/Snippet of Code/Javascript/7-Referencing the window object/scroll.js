
function init()
{
 var i, panel=document.getElementById("panel");  

 for(i=1; i<501; i++)
 {
   panel.innerHTML += i + " ";
 }

 window.scrollBy(0,500);
 window.moveTo(0,0);

}
window.onload=init;


