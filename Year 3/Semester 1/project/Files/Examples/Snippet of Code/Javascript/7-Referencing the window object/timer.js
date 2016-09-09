var counter=0;

function init()
{
 var timerId, panel=document.getElementById("panel");

 counter++; 

 panel.innerHTML += "<span style='background:black;color:white;margin:2px;'>"+counter+ "</span>";

 if( counter > 19 ) 
 { 
   window.clearTimeout( timerId ); 
 }

 else timerId = window.setTimeout( init, 1000);
}
window.onload=init;


