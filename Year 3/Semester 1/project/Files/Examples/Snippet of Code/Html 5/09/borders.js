/* HTML5 in easy steps:example 66 supplemental */

function init() 
{ 
	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
	
		ctx.fillStyle="#F30";
		ctx.strokeStyle = "rgba( 0,0, 255, 0.5)";
		ctx.lineWidth=20;
		
		ctx.fillRect (75, 20, 100, 100); 
		ctx.strokeRect(75, 20, 100, 100);
		
		ctx.fillRect (375, 20, 100, 100); 
		ctx.strokeRect(365, 10, 120, 120);
	}
 }  
onload=init;