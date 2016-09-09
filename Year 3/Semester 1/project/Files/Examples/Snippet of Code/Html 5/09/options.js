/* HTML5 in easy steps:example 67.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
	
		ctx.fillStyle="rgb(255,51,0)";						// Equivalent to #F30
		ctx.fillRect (50, 10, 80, 80); 			// Draw a square from top left x:y at 50:10 of width, height
		ctx.fillStyle="rgba(0,153,255,0.5)";			// 50% opacity (globalAlpa tip) 0.0-1.0 range
		ctx.fillRect(100,30,80,80);
		
		var linear = ctx.createLinearGradient(0,10, 0,110); 	// Arguments are start and finish x y coords of gradient.
  		linear.addColorStop(0,"yellow");
  		linear.addColorStop(1,"green");
  		ctx.fillStyle = linear;
  		ctx.fillRect(200,10,100, 100);
		
		var radial = ctx.createRadialGradient(370,60, 0,  370, 60, 70 );	// Arguments are start and finish x, y, radius of gradient circles.
		radial.addColorStop(0,"yellow");
  		radial.addColorStop(1,"green");
  		ctx.fillStyle = radial;
  		ctx.fillRect(320,10,100, 100);
		
		var image=new Image(); image.src="options.png";
		var pattern=ctx.createPattern( image ,"repeat");
		ctx.fillStyle = pattern;  
 		ctx.fillRect(440,10,100,100); 
		
	}
 }  
onload=init;