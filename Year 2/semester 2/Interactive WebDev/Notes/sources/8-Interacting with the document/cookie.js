function init()
{
 var panel = document.getElementById( "panel" ) ;

 // Set a cookie.
 var user = escape( "Mike McGrath,000456" ) ;
 var expiry = new Date() ;
 expiry.setTime( expiry.getTime() + ( 7 * 24 * 60 * 60 * 1000 ) ) ;
 document.cookie = "myData="+user+";"+"expires="+expiry.toGMTString()+";" ;

 // Get a cookie.
 if( document.cookie )
 {
   var cookieString =  unescape( document.cookie ) ;
   var list = cookieString.split( "=" ) ;
   if( list[ 0 ] === "myData" ) 
   {
     var data = list[ 1 ].split( "," ) ;
     var userName = data[ 0 ] ;
     var userAcct = data[ 1 ] ;
   }
 }

 panel.innerHTML += "Cookie String: " + cookieString ;
 panel.innerHTML += "<br>Split List: " + list ;
 panel.innerHTML += "<br>User Name: " + userName ;
 panel.innerHTML += "<br>User Account: " + userAcct ;
 
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;