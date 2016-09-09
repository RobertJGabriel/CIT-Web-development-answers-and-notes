/* HTML5 in easy steps:example 66.1 */

function init() 
{ 
	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");

  		var ctx = canvas.getContext("2d");
	
		ctx.lineWidth=6;							// note: stroke is 3px either side of path
	
		ctx.strokeStyle="#F30";
		ctx.strokeRect (75, 10, 100, 100); 			// Draw a square from top left x:y at 75:10 of 100 width, 100 height
	
		ctx.strokeStyle="#3C0";
		ctx.beginPath();
		ctx.arc(275,60, 50, 0,Math.PI*2, true);		// Draw circle centered x:y at 275:60, radius 50,starting at 0 to 360 radians, anticlockwise
		ctx.stroke();
		
		ctx.strokeStyle="#09F";
		ctx.beginPath();							// Draw a triangle from x:y 375,100 -> 425,10 -> 475,110 (-> 375,110)
		ctx.moveTo(375,110);
		ctx.lineTo(425,10);
		ctx.lineTo(475,110);
		ctx.closePath();
		ctx.stroke();
	}
 }  
onload=init;