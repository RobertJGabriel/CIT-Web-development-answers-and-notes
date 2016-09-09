var i , imgs , pic ;

function rotate()
{
  pic.src = imgs[ i ] ;
  ( i === ( imgs.length -1 ) ) ? i=0 : i++ ;
  setTimeout( rotate , 1000 ) ; 
}

function init()
{
  pic = document.getElementById( "pic" ) ;
  imgs = [ "query.png" , "warn.png" , "stop.png" , "info.png" ] ;
  var preload = new Array() ;
  for( i = 0 ; i < imgs.length ; i++ )
  {
    preload[ i ] = new Image() ;
    preload[ i ].src = imgs[ i ] ;
  }
  i = 0 ;
  rotate() ;
}
document.addEventListener("DOMContentLoaded", init , false ) ;