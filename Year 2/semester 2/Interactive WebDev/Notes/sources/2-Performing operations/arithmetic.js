function init()
{
 var sum = 80 + 20 ;
 var sub = sum - 50 ;
 var mul = sum * 5 ;
 var div = sum / 4 ;
 var mod = sum % 2 ;
 var inc = ++sum ;
 var dec = --sum ;
 var str = "Sum: " + sum + "<br>" ;
 str += "Subtraction: " + sub + "<br>" ;
 str += "Multiplication: " + mul + "<br>" ;
 str += "Division: " + div + "<br>" ;
 str += "Modulus: " + mod + "<br>" ;
 str += "Increment: " + inc + "<br>" ;
 str += "Decrement: " + dec + "<br>" ;
 document.getElementById( "panel" ).innerHTML = str ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;