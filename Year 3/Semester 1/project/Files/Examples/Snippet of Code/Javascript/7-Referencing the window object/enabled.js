
function init()
{
 var panel=document.getElementById("panel");
 

 if( navigator.javaEnabled() ) 
 {
   panel.innerHTML = "Java Support is Enabled"; 
 }

 if(navigator.plugins.length !== 0)
 {
   panel.innerHTML += "<hr>Total Plugins: " + navigator.plugins.length;
   panel.innerHTML += "<br>Example: " + navigator.plugins[15].name;
   panel.innerHTML += " - " + navigator.plugins[15].description; 


  // Uncomment this loop to write all plugins.
  // for (var i=0; i< navigator.plugins.length; i++)
  // { 
  //	panel.innerHTML += "<br>"+navigator.plugins[i].name; 
  //	panel.innerHTML += " - " + navigator.plugins[i].description; 
  // }

  }


  if(navigator.mimeTypes.length !== 0)
  {
     panel.innerHTML += "<hr>Total MIME Types: " + navigator.mimeTypes.length;
     panel.innerHTML += "<br>Example: " + navigator.mimeTypes[10].type;
     panel.innerHTML += " - " + navigator.mimeTypes[10].description; 

   // Uncomment this loop to write all mimeTypes.
   // for (i=0; i< navigator.mimeTypes.length; i++)
   // { 
   //	panel.innerHTML += "<br>"+navigator.mimeTypes[i].type;
   //   panel.innerHTML += " - "+ navigator.mimeTypes[i].description;
   // }

  }

}
onload=init;


