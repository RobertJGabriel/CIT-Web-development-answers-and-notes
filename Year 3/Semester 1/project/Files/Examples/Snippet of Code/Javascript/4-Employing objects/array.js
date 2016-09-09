

function init()
{
 var panel=document.getElementById("panel");

 var summer = ["June", "July", "August"]; // Same as  = new Array( "June", "July", "August" ) ;


 for( month in summer ) 
 { 
   if(summer[month] !== "" )
   {
     panel.innerHTML += ( month + ": " + summer[month] + "<br>");
   }
 }
 
 panel.innerHTML += "Vacation month: " + summer[2] ;

}
window.onload=init;

