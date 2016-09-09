
function init()
{
 var panel=document.getElementById("panel");
 var s1 = "JavaScript", s2 = " in Easy ", s3 = "Steps";
 var picName ="myPic", picFile = "poolballs.png";

 panel.innerHTML = s1.toUpperCase();
 panel.innerHTML += "<br>" + s1.toLowerCase();
 panel.innerHTML += "<br>" + s1.concat( s2, s3 );
 panel.innerHTML += "<br>" + s1 + " has " + s1.length + " characters";

 panel.innerHTML += "<br><img id='myPic'>"; 
 document.images[ picName ].src=picFile;

 // EQUATES TO...
 // document.images[ "myPic" ].src="poolballs.png"; 

 // DON'T USE...
 // eval("document.images." + picName + ".src='" + picFile +"'");

}
window.onload=init;


