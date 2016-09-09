var panel , request ;

function showText()
{
  if ( request.readyState === 4 )
  {
    if ( request.status === 200 )
    { 
        panel.innerHTML += "<br>" + request.responseText ;  

	// Comment out the line above then uncomment the statement
	// below to see only the file headers.
	// panel.innerHTML += "<br>" + request.getAllResponseHeaders() ;
    }
  }
}

function doRequest()
{
  if ( XMLHttpRequest ) 
  { 
    request = new XMLHttpRequest() ; 
  }  
  else
  if ( ActiveXObject ) 
  { 
    request = new ActiveXObject( "Microsoft.XMLHTTP" ) ; 
  }
  else
  { 
    return false ; 
  }

  request.open( "GET" , "data.txt" , true ) ;
  request.send( null ) ;
  request.onreadystatechange = showText ;
}

function init()
{
  panel = document.getElementById( "panel" ) ;
  var btn = document.getElementById( "btn" ) ;
  btn.onclick = doRequest ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;