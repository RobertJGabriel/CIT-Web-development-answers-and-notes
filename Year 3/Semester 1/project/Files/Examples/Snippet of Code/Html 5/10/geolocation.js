/* HTML5 in easy steps:example 84.1 */

function init()
{
	if (navigator.geolocation)
	{ 
		document.getElementById("msg").innerHTML = 
		"Geolocation service is trying to find you...";
		navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
	}
	else { document.getElementById("msg").innerHTML = "Your browser does not support Geolocation service"; }
}
onload=init;

function errorFunction(position)
{
  document.getElementById("msg").innerHTML = "Geolocation service cannot find you at this time.";
}

function successFunction(position)
{
  var lat = position.coords.latitude;
  var lng = position.coords.longitude;
  document.getElementById("msg").innerHTML = "Found you at...<br>Latitude: "+lat+", Longitude: "+lng;
  var latlng = new google.maps.LatLng(lat, lng);
  var options = { zoom: 18, center: latlng, mapTypeId: google.maps.MapTypeId.ROADMAP };
  var map = new google.maps.Map(document.getElementById("map"), options);
  var marker = new google.maps.Marker({ position: latlng, map: map, title:"You are here" });
}