/* HTML5 in easy steps:example 64.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
		ctx.fillStyle="#F30";								// Equivalent to rgb(255,51,0)
		ctx.fillRect (0, 0, canvas.width , canvas.height ); 			// Draw a square from top left x:y at 0:0 of width, height
		ctx.clearRect(225,25, 100,100);
	}
 }  
onload=init;