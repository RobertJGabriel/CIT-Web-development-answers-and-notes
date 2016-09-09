
function init()
{
 var panel=document.getElementById("panel");
 
 var dfn = "JavaScript is the original dialect of the ECMAScript standard language whereas JScript is the dialect developed later by Microsoft.";

 panel.innerHTML = dfn.slice(0, 26); 			
 panel.innerHTML += dfn.slice(61,70) + "<br>";

 panel.innerHTML += dfn.split(" ", 4) + "<br>"; 	
 
 panel.innerHTML += dfn.substring(79, 94); 		
 panel.innerHTML += dfn.substring(121, 130); 

 panel.innerHTML += dfn.substr(61, 10);		

}
window.onload=init;


