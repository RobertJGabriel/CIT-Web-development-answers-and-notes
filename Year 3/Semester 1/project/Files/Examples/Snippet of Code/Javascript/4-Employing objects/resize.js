function init()
{
 var i, panel=document.getElementById("panel");
 
 var week = ["Mon", "Tue", "Wed", "Thu", "Fri" ] ;
 var weekend = [ "Sat", "Sun" ];

 panel.innerHTML = "Weekdays: " + week;
 panel.innerHTML += "<hr>Weekend days: " + weekend;


 for( i = 0; i < weekend.length; i ++ ) 
 {
    week[ week.length ] = weekend[i];
 }
 panel.innerHTML += "<hr>Increased with weekend days: " + week ;

 week.length -= 2;
 panel.innerHTML += "<hr>Reduced back to weekdays: " + week ;

}
window.onload=init;




