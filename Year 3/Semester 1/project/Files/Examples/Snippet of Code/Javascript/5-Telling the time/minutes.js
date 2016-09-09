
function init()
{
 var panel=document.getElementById("panel");

 var now = new Date();
	
 var hh = now.getHours();	
 var mn = now.getMinutes();	
 var ss = now.getSeconds();	
 var ms = now.getMilliseconds();

 // Add leading zero to single minutes and seconds.
 if (mn < 10) { mn = "0" + mn; }
 if (ss < 10) { ss = "0" + ss; }
 
 var tim = hh+":"+mn+":"+ss+":"+" and "+ms+ " milliseconds";
 panel.innerHTML += "It's now: " + tim ;

 // Create appropriate hour greeting.
 var hi="Good Morning.";
 if (hh > 11) { hi = "Good Afternoon."; }
 if (hh > 17) { hi = "Good Evening."; }
 panel.innerHTML += "<br>"+hi ;

 // Create appropriate suffix for 12-hour time.
 var suffix = ( hh > 11 ) ? " P.M." : " A.M." ;
 if ( hh > 12 ) { hh -= 12; }
 panel.innerHTML += "<br>Time is: " +hh+":"+mn+suffix ;

}
window.onload=init;


