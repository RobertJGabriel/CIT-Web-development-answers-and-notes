/* HTML5 in easy steps:example 80.1 */

/* Mike's Note: At the time of writing Opera refuse to implement the DragNdrop API */

function init()
{ 
	var bin = document.getElementById("bin");
	var gfx = document.getElementsByTagName("img");
	var msg = document.getElementById("msg");					
	
	for(var i=0; i< gfx.length; i++)						
	{
		gfx[i].ondragstart = function(event) { event.dataTransfer.setData("Text", this.id); };
	}
	
	bin.ondragover = function(event) { return false; };		

	bin.ondrop = function(event)							
	{ 
		var did = event.dataTransfer.getData("Text"); 		
		var tag = document.getElementById(did);				
		
		if(did === "bin") { return false;}					
		else { msg.innerHTML += "<li>"+did+"</li>"; }		
		
		tag.parentNode.removeChild(tag); 					
		return false; 										
	};								
}
onload=init;