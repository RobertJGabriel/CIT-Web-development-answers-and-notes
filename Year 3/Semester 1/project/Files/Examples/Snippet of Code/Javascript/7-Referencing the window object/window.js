
function init()
{
 var panel=document.getElementById("panel");
 var width, height, avWidth, avHeight, colors ;
 
 width = window.screen.width ;
 height = window.screen.height ;
 avWidth = window.screen.availWidth ;
 avHeight = window.screen.availHeight ;

 switch( window.screen.colorDepth )
 {
    case 8 : colors = "Low Color";  break ;
    case 16: colors = "High Color"; break ;
    case 32: colors = "True Color"; break ;
    default: colors = "Unknown";
 }

  panel.innerHTML = "Screen Resolution: " + width + " x " + height;
  panel.innerHTML+= "<br>Available Screen Size: " + avWidth+ " x " + avHeight;
  panel.innerHTML+= "<br>Color Capability: " + colors ;

  window.defaultStatus = "Screen Data by JavaScript";

}
window.onload=init;



