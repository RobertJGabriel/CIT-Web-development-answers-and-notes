/* HTML5 in easy steps:example 71.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var i, j, ctx = canvas.getContext("2d");

		// translate
		ctx.fillStyle="#F30";
  		for(i=0;i<3;i++)
		{
    		for(j=0;j<3;j++)
			{
				ctx.save();
				ctx.translate(50*j,50*i);
				ctx.fillRect(0,0,30,30);
				ctx.restore();
    		}
  		}
		
		// scale
		ctx.fillStyle="#3C0";
		ctx.translate(150,0);
		for( i=0; i<3; i++)
		{
		  ctx.fillRect(0,0,100,100);
		  ctx.translate(110,0);
		  ctx.scale(0.75,0.75)
		}
		
		// rotate
		ctx.fillStyle="#09F";
		ctx.translate(180,120);

  		for (i=1;i<6;i++)
		{ 
    		for (j=0;j<i*6;j++)					// Loop through circles from inside to out.
			{ 
				// Draw individual dots
      			ctx.rotate(Math.PI*2/(i*6));	// Rotate the transformation matrix by radians specified as argument.
      			ctx.beginPath();
      			ctx.arc(0,i*22.5,8,0,Math.PI*2,true);	// i*22.5 increases the radius on each iteration.
      			ctx.fill();
    		}
  		}
	}
 }  
onload=init;