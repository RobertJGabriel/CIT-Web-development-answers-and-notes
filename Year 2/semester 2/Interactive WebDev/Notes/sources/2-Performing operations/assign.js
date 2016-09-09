function init()
{
  var msg = "JavaScript" ; msg += " Code" ;
  var flt = 7.5 ; flt += 2.25 ;
  var intA = 8 ; intA -= 4 ;
  var intB = 24 ; intB *= intA ;
  var intC = 24 ; intC /= intA ;
  var intD = 24 ; intD %= intA ;
  var str = "Add & assign string: " + msg + "<br>" ;
  str += "Add & assign float: " + flt + "<br>" ;
  str += "Subtract & assign: " + intA + "<br>" ;
  str += "Multiply & assign: " + intB + "<br>" ;
  str += "Divide & assign: " + intC + "<br>" ;
  str += "Modulus & assign: " + intD + "<br>" ;
  document.getElementById( "panel" ).innerHTML = str ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;