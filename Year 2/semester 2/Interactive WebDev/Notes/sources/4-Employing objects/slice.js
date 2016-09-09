function init()
{
  var panel = document.getElementById( "panel" ) ;
  var seasons = [ "Spring" , "Summer" , "Fall" , "Winter" ] ;
  panel.innerHTML = "Elements: " + seasons ;
  panel.innerHTML += "<br>Joined: " + seasons.join( " and " ) ;
  panel.innerHTML += "<hr>Popped: " + seasons.pop() ;
  panel.innerHTML += "<br>Elements: " + seasons ;
  panel.innerHTML += "<hr>Pushed: " + seasons.push( "Winter" ) ;
  panel.innerHTML += "<br>Elements: " + seasons ;
  panel.innerHTML += "<hr>Sliced: " + seasons.slice( 1 , 3 ) ;
  panel.innerHTML += "<br>Spliced: " + seasons.splice( 2 , 1 , "Autumn" ) ;
  panel.innerHTML += "<br>Elements: "+ seasons ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;