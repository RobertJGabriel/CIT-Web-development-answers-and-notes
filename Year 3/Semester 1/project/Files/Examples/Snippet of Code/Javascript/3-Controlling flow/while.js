function init()
{
 var panel=document.getElementById("panel");
 var num = 70;

 while ( num > 10 )
 {
   num -= 5;
   panel.innerHTML +="Countdown value: " + num + "<br>";
 }


}
window.onload=init;