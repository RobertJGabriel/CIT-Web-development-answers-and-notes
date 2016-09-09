
function init()
{
 var panel=document.getElementById("panel");

 var sq = Math.pow( 5, 2 ) ;	// 5 to the power 2 (5x5).
 var cb = Math.pow( 4, 3 ) ;	// 4 to the power 3 (4x4x4).

 panel.innerHTML = "Largest Positive: " + Math.max( sq, cb ) ;
 panel.innerHTML += "<br>Smallest Positive: " + Math.min( sq, cb ) ;
 panel.innerHTML += "<br>Largest Negative: " + Math.max( -5, -4.75 ) ;

}
window.onload=init;


