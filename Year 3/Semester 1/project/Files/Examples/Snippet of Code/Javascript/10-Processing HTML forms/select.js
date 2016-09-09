
function init()
{
  var panel=document.getElementById("panel");

  var list = document.getElementById("cityList");

  var elem = list.options.selectedIndex; 

  var city = list.options[ elem ].value;

  panel.innerHTML += "Selected: " + city + " - Index: " + elem;

}
onload=init;

