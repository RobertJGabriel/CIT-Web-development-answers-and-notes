function init()
{
 var panel = document.getElementById( "panel" ) ;
 var flag = false ;
 var num = 10;

 if ( flag )
 {
  panel.innerHTML = "Power is On" ;
 }
 else
 { 
  panel.innerHTML = "Power is Off" ;
 }

 if ( num === 5 )
 { 
  panel.innerHTML += "<br>Number is Five" ; 
 }
 else if ( num === 10 )
 { 
  panel.innerHTML += "<br>Number is Ten" ; 
 }
 else 
 { 
  panel.innerHTML += "<br>Number is Neither Five or Ten" ; 
 }

}
document.addEventListener( "DOMContentLoaded" , init , false ) ;