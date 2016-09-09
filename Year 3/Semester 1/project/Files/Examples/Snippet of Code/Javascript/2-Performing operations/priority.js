function init()
{

 var sum = 2 * 9 + 12 / 3;	// 2*9=18, 12/3=4, 18+4=22.
 var str= "18 + 4 = " + sum;

 sum = ((2 * 9) + 12) / 3; 	// 2*9=18, 18+12=30, 30/3=10.
 str += "<br>30 / 3 = " + sum ;

 sum = (2 * (9 + 12)) / 3;	// 9+12=21, 2*21=42, 42/3=14.
 str += "<br>42 / 3 = " + sum ;

 sum = 2 * (9 + (12 / 3));	// 12/3=4, 9+4=13, 2*13=26.
 str += "<br>2 * 13 = " + sum ;

 document.getElementById("panel").innerHTML = str;

}
window.onload=init;