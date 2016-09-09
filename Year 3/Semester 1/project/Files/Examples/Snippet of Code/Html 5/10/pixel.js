/* HTML5 in easy steps:example 79.1 */

function init() 
{   
	var canvas = document.getElementById("canvas");
	if( canvas.getContext )
	{
 		var ctx = canvas.getContext("2d");
		var img = document.getElementById("pixel");
		ctx.drawImage(img, 0, 0); 			// Copy the image onto the canvas.

		/* 	IMPORTANT: This technique will only work if the image is
 			located on the same domain as this HTML document and served via the HTTP protocol.
			A security error will occur if the image is on a different domain or if served
			by the local FILE protocol. Both files must be located on a single web domain. */ 	

		// Read pixel color data into a CanvasPixelArray.
		var pixels = ctx.getImageData( 0, 0, img.width, img.height );
		// Note: Also now pixels.width=img.width and pixels.height=img.height. 	

		// Loop through each alpha pixel in every 4th array element, (ie. rgba).
		for(var i=3; i<pixels.data.length; i+=4)	
		{
			if(pixels.data[i-2]!==255)	// Where no g value (ie. not a white pixel 255,255,255).
			{
				pixels.data[i-1]=0;		// Set b=0 - to remove blue.
				pixels.data[i-3]=255;	// Set r=255 - to make red.
			}
		}
		ctx.putImageData(pixels, 0, 0);	// Paint the modified pixel colors onto the canvas.	
	}
 }  
onload=init;