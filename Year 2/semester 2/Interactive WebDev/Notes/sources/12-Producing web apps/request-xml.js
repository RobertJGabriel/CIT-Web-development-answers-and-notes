var panel , request ;

function showXML()
{
  if ( (request.readyState === 4 ) && ( request.status === 200 ) )
  { 
    var i , names =  request.responseXML.getElementsByTagName( "name" ) ;
    for ( i = 0 ; i < names.length ; i++ )
    {
      panel.innerHTML += "<br>" + names[ i ].firstChild.data ;
    }
  }
}

function doRequest()
{
  request = new XMLHttpRequest() ;
  request.open( "GET" , "data.xml" , true ) ;
  request.send( null ) ;
  request.onreadystatechange = showXML ;
}

function init()
{
  panel = document.getElementById( "panel" ) ;
  document.getElementById( "btn" ).onclick = doRequest ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;