function init()
{
  var yes = true, no = false ;
  var blnA = yes && yes ;
  var blnB = yes && no ;
  var blnC = no || yes ;
  var blnD = no || no ;
  var tog = !yes ;
  var str = "Are both values true?: " + blnA ;
  str += "<br>Are both values true now?: " + blnB ;
  str += "<br>Is either value true?: " + blnC ;
  str += "<br>Is either value true now?: " + blnD ;
  str += "<br>Initial value: " + yes ;
  str += "<br>Toggled value: " + tog ;
  document.getElementById("panel").innerHTML = str;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;