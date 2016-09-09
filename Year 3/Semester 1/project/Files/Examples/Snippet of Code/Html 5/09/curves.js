/* HTML5 in easy steps:example 70.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
		ctx.lineWidth=15;
		ctx.strokeStyle="#F30";
		ctx.fillStyle="#FF0";							
		ctx.fillRect(70,20, 100, 100);
		ctx.fillRect(200,20, 100, 100);
		
		ctx.beginPath();
		ctx.arc(70, 70, 50, 0, Math.PI/180*90, true);	// #1. Arc 0-90 degrees anticlockwise
		ctx.stroke();
		
		ctx.beginPath();								// #2. Arc 0-90 degrees clockwise
		ctx.arc(120, 70, 50, 0, Math.PI/180*90, false);
		ctx.stroke();

		ctx.beginPath();					// #3. Arc 90-270 degrees anticlockwise filled semi-circle
		ctx.arc(250, 70, 50, Math.PI/180*90, Math.PI/180*270, true);
		ctx.fillStyle="#3C0";
		ctx.fill();
		
		ctx.strokeStyle="#09F";	// Quadratic curve using ONE control point.
		ctx.beginPath();
		ctx.moveTo(350,10);
		ctx.quadraticCurveTo(350,100, 440,100);
		ctx.stroke();
		
		ctx.beginPath();		// Bezier curve, using TWO control points.	
		ctx.moveTo(450,10);
		ctx.bezierCurveTo(550,10, 450,100, 550,100);
		ctx.stroke();

	}
 }  
onload=init;