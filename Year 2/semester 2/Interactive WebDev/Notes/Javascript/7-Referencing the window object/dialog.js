
function init()
{
 var panel=document.getElementById("panel");
 
 window.alert("Here's a simple message");

 panel.innerHTML = "Confirm: " + window.confirm("Go or Stop?");

 panel.innerHTML += "<br>Prompt: " +  window.prompt("Yes or No?", "Yes");

}
window.onload=init;



