function init()
{
 var panel = document.getElementById( "panel" ) ;
 var day ;
 switch ( 5 - 2 )
 {
  case 1 : day = "Monday" ; 	break ;
  case 2 : day = "Tuesday" ; 	break ;
  case 3 : day = "Wednesday" ; 	break ;
  case 4 : day = "Thursday" ; 	break ;
  case 5 : day = "Friday" ; 	break ;
  case 6 : day = "Saturday" ; 	break ;
  case 7 : day = "Sunday" ;	break ;
  default : day = "There are only 7 days per week" ;
  }
  panel.innerHTML = "It's " + day ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;