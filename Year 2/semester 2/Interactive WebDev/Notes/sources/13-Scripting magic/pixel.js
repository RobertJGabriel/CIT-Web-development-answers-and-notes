function init() 
{   
  var canvas = document.getElementById( "canvas" ) ;
  if ( canvas.getContext )
  {
    var ctx = canvas.getContext( "2d" ) ;
    var img = document.getElementById( "blue_flag" ) ;
    ctx.drawImage( img , 0 , 0 ) ;
    // Read pixel color data into a CanvasPixelArray.
    var pixels = ctx.getImageData( 0 , 0 , img.width , img.height ) ;
    // Note: Also now pixels.width=img.width and pixels.height=img.height. 	
    // Loop through each alpha pixel in every 4th array element, (ie. rgba).
    for( var i = 3 ; i < pixels.data.length ; i += 4 )	
    {
      if ( pixels.data[ i-2 ] !== 255 ) // Where no g value (ie. not a white pixel 255,255,255).
      {
        pixels.data[ i-1 ] = 0 ; // Set b=0 - to remove blue.
        pixels.data[ i-3 ] = 255 ; // Set r=255 - to make red.
      }
    }
    ctx.putImageData( pixels , 0 , 0 ) ; // Paint the modified pixel colors onto the canvas.	
  }
}  
document.addEventListener( "DOMContentLoaded" , init , false ) ;