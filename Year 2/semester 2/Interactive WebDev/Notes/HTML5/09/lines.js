/* HTML5 in easy steps:example 69.1 */

function init() 
{ 
  	var canvas = document.getElementById("canvas");
	if(canvas.getContext)
	{
 		var ctx = canvas.getContext("2d");
	
		ctx.lineWidth = 20; ctx.strokeStyle="#F30";

		ctx.beginPath();
		ctx.moveTo(20,130); ctx.lineTo(70,30); ctx.lineTo(120,130);
		ctx.closePath();
		ctx.stroke();

		// Line caps
		ctx.strokeStyle="#3C0"; // the default lineCap.
		ctx.beginPath();
		ctx.lineCap = "butt"; ctx.moveTo(160,30); ctx.lineTo(160,120);
		ctx.stroke();

		ctx.beginPath();
		ctx.lineCap = "round"; ctx.moveTo(200,30); ctx.lineTo(200,120);
		ctx.stroke();

		ctx.beginPath();
		ctx.lineCap = "square"; ctx.moveTo(240,30); ctx.lineTo(240,120);
		ctx.stroke();
		
		ctx.strokeStyle="#09F";
		// Line joins
		ctx.beginPath();
		ctx.lineJoin = "miter"; ctx.moveTo(280,80);ctx.lineTo(330,30);ctx.lineTo(330,120);// the default lineJoin.
		ctx.stroke();

		ctx.beginPath();
		ctx.lineJoin = "round"; ctx.moveTo(370,80); ctx.lineTo(420,30); ctx.lineTo(420,120);
		ctx.stroke();
	
		ctx.beginPath(); 
		ctx.lineJoin = "bevel"; ctx.moveTo(460,80); ctx.lineTo(510,30); ctx.lineTo(510,120);
		ctx.stroke();
		
	}
 }  
onload=init;