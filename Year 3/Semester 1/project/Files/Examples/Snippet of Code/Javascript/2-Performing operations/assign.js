function init()
{
 var msg = "JavaScript";
 msg += " Code" ;	// Concatenate and assign string.

 var flt = 7.5;	
 flt += 2.25; // Add and assign float.
 
 var intA = 8;
 intA -= 4;	// Subtract and assign.

 var intB = 24;
 intB *= intA;	// Multiply and assign.

 var intC = 24; 
 intC /= intA;	// Divide and assign.

 var intD = 24;	
 intD %= intA;	// Modulus and assign.


 var str = "Add & assign string: " + msg + "<br>";
 str += "Add & assign float: " + flt + "<br>";
 str += "Subtract & assign: " + intA + "<br>";
 str += "Multiply & assign: " + intB + "<br>";
 str += "Divide & assign: " + intC + "<br>";
 str += "Modulus & assign: " + intD + "<br>";



document.getElementById("panel").innerHTML = str;



}
window.onload=init;