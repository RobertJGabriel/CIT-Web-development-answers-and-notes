function init()
{
 var state = 7 > 5 ? "Greater" : "Smaller" ;
 var str = "7 is " + state + " than 5";

 state = 7 > 10 ? "Greater" : "Smaller" ;
 str += "<br>7 is " + state + " than 10";

 state = 7 === 8 ? "Equal" : "Not Equal" ;
 str += "<br>7 is " + state + " to 8";

 state = 7 % 2 === 0 ? "Even" : "Odd" ;
 str += "<br>7 is an " + state + " number" ;

 document.getElementById("panel").innerHTML = str;

}
window.onload=init;