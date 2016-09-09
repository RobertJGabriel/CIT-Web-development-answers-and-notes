/* HTML5 in easy steps:example 65.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
	
		ctx.fillStyle="#F30";						// Equivalent to rgb(255,51,0)
		ctx.fillRect (75, 10, 100, 100); 			// Draw a square from top left x:y at 75:10 of width, height
		
		ctx.fillStyle="#3C0";
		ctx.beginPath();							// Equivalent to rgb(51,204,0)
		ctx.arc(275,60, 50, 0,Math.PI*2, true);		// Draw circle centered x:y at 275:60, radius 50,starting at 0 to 360 radians, anticlockwise
		ctx.fill();
		
		ctx.fillStyle="#09F";						// Equivalent to rgb(0,153,255)
		ctx.beginPath();							// Draw a triangle from x:y 375,100 -> 425,10 -> 475,110 (-> 375,110)
		ctx.moveTo(375,110);
		ctx.lineTo(425,10);
		ctx.lineTo(475,110);
		ctx.closePath();
		ctx.fill();
	}
 }
  
onload=init;