/* HTML5 in easy steps:example 72.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");

		ctx.fillStyle = "#F30";
		ctx.fillRect(20,40,100,100);
				
		ctx.fillStyle = "#3C0";
		ctx.transform(1, -0.3, 0, 1, 0, 0);  // Skew.
		ctx.fillRect(160,90,100,100);	
		
		ctx.setTransform(1, 0, 0, 1, 0, 0);  // Set transformation matrix back to (default) "identity" matrix.
		// m11, m12, m21, m22, dx, dy - where...
		// m11 controls width
		// m12 & m21 control skew
		// m22 controls height
		// dx, dy control position

		/*
		ctx.transform(1, 0, -0.3, 1, 0, 0);  // Skew. 
		ctx.fillRect(340,40,100,100);
		ctx.setTransform(1, 0, 0, 1, 0, 0);  // Set transformation matrix back to (default) "identity" matrix. 
		*/
		
		ctx.fillStyle = "#09F";
    	ctx.fillRect(350,10,130,130);

  		// Create a circular clipping path.
  		ctx.beginPath();
  		ctx.arc( 415,75,50,0,Math.PI*2,true);  
  		ctx.clip();

  		// Paint only circular clipped area of the rectangle.
  		ctx.fillStyle = "#FF0";
  		ctx.fillRect(350,10,130,130);
		ctx.fillStyle = "#F0F";
		ctx.fillRect(350,10,65,65);
	}
 }  
onload=init;