
function init()
{
 var panel=document.getElementById("panel");  

 // MIKE'S NOTE: Conditional test added to remind that this example requires a HTTP request.
 if( location.protocol !== "http:" ) 
 { 
   panel.innerHTML = "Please run this example via a web server<br>using HTTP to see all document information."; 
 } 
 else 
 {
   panel.innerHTML += "Linked From: " + document.referrer; 
   panel.innerHTML += "<br>Title: " + document.title; 
   panel.innerHTML += "<br>URL: " + document.URL; 
   panel.innerHTML += "<br>Domain: " + document.domain; 
   panel.innerHTML += "<br>Last Modified: " + document.lastModified;
 } 

}
onload=init;


