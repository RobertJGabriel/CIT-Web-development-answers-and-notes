/* HTML5 in easy steps:example 68.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
		
		ctx.font = "bold 70px Arial, sans-serif";
		ctx.fillStyle = "#F30"; 
		ctx.fillText("HTML5", 10, 60);
		
		ctx.font = "32px Arial";
		ctx.fillStyle = "#09F"; 	
		ctx.fillText("with Context 2D", 10, 130);

    	ctx.font = "italic bold 60px Fantasy";	
		ctx.strokeStyle = "#3C0";
		ctx.strokeText("Canvas Fun", 10, 100);

		ctx.shadowOffsetX = 2;
  		ctx.shadowOffsetY = 2;
  		ctx.shadowBlur = 3;
  		ctx.shadowColor = "black";
		ctx.font = "italic bold 90px Fantasy";
		ctx.fillStyle = "#FF0";
		ctx.fillText("Drop", 310, 60);
  		ctx.fillText("Shadow", 310, 130);		
	}
 }  
onload=init;