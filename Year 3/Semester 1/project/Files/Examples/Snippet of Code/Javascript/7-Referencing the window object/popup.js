
function init()
{
 var panel=document.getElementById("panel");  
 panel.innerHTML = "Regular information in the main window";

 var winObject = window.open( "pop.html", "windowName", "top=200,left=100,width=400,height=100,status" );

}
window.onload=init;


