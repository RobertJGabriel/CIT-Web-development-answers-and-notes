var panel , request ;

function showStatus()
{
  if ( request.readyState === 4 )
  {
    if ( request.status === 200 )
    { 
      panel.innerHTML += "<br>Request Succeeded" ; 
    }
    
    if( request.status === 404 )
    { 
      panel.innerHTML += "<br>Request Failed" ; 
    }
  }
}

function doRequest()
{
  request = new XMLHttpRequest() ;
  request.open( "GET" , "data.txt" , true ) ;
  request.send( null ) ;
  request.onreadystatechange = showStatus ;
}

function init()
{
  panel = document.getElementById( "panel" ) ;
  var btn = document.getElementById( "btn" ) ;
  btn.onclick = doRequest ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;