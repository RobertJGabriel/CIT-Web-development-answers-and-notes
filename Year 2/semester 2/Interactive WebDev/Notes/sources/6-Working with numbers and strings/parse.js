function init()
{
 var panel = document.getElementById( "panel" ) ;
 var num = "074.5 Input" ;
 panel.innerHTML += "Not a number?: " + isNaN( num ) ; 
 panel.innerHTML += "<br>This value is a " + typeof num ; 
 panel.innerHTML += "<hr>Octal: " + parseInt( num , 8 ) 
 + "<br>Decimal: " + parseInt( num , 10 )
 + "<br>Hexadecimal: " + parseInt( num , 16 ) ;
 panel.innerHTML += "<br>Float: " + parseFloat( num ) ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;