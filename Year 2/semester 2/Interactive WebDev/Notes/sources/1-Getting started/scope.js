var global = "This is Worldwide Global news<hr>" ;

function us( obj )
{
  var local = "*** This is United States Local news ***<br>" ;
  obj.innerHTML += local ; 
  obj.innerHTML += global ;
}

function eu( obj )
{
  var local = "--- This is European Local news ---<br>" ;
  obj.innerHTML += local ;
  obj.innerHTML += global ;
}

function init()
{
  var obj = document.getElementById( "panel" ) ;
  obj.innerHTML = global ;
  us( obj ) ;
  eu( obj ) ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;