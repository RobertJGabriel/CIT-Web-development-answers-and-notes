function init()
{
 var panel = document.getElementById( "panel" ) ;
 var item1 = document.getElementById( "item1" ) ; 
 var elems = document.getElementsByTagName( "li" ) ;
 var item2 = elems[ 4 ] ;
 panel.innerHTML += "Total No. List Items: " + elems.length ;
 panel.innerHTML += "<br>Specific Item One: " + item1.innerHTML + "<br>Specific Item Two: " + item2.innerHTML ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;