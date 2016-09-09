function init()
{
 var strA = "JavaScript" === "JAVASCRIPT" ;
 var strB = "JavaScript" === "JavaScript" ;
 var flt =  7.5 === 7.5 ; 
 var intA =  8 !== 8 ;
 var intB = 24 > 12 ;
 var intC = 24 < 12 ;
 var intD = 24 <= 24 ;
 var str = "String equality test 1: " + strA + "<br>" ;
 str += "String equality test 2: " + strB + "<br>" ;
 str += "Float equality test: " + flt + "<br>" ;
 str += "Integer inequality test: " + intA + "<br>" ;
 str += "Greater than test: " + intB + "<br>" ;
 str += "Less than test: " + intC + "<br>" ;
 str += "Less than or equal test: " + intD + "<br>" ;
 document.getElementById("panel").innerHTML = str;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;