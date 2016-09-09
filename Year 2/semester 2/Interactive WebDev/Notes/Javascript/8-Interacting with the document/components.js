
function init()
{
 var panel=document.getElementById("panel");  

 panel.innerHTML = "Document Components...";
 panel.innerHTML += "<br>No. Forms: " + document.forms.length;
 panel.innerHTML += "<br>No. Links: " + document.links.length;
 panel.innerHTML += "<br>No. Anchors: " + document.anchors.length;
 panel.innerHTML += "<br>No. Images: " + document.images.length;
 panel.innerHTML += "<br>First Image URL: " + document.images[0].src;
 
}
onload=init;


