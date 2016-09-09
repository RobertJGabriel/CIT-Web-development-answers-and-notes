window.onload = function () {
    document.getElementById("menuButton").addEventListener('click', menuStyle, false);
    document.getElementById("content").addEventListener('click', pushMessage, false);
$("#search").keypress(function(e) {
    if(e.which == 13) {
        alert('You pressed enter!');
    }
});
};


function menuStyle() {
    var id = $("ul#menu li:first").get(0).id;
    if (id == "menuButton") {
        $("#menuButton").attr('id', 'menuButtona');
        $(".menuItem").attr('class', 'menuItema');
    } else {
        $("#menuButtona").attr('id', 'menuButton');
        $(".menuItema").attr('class', 'menuItem');
    }
}





function pushMessage() {
    
    
    
    if (typeof (EventSource) !== "undefined") {
        var source = new EventSource("messages.php");
        source.onmessage = function (event) {
            document.getElementById("alertBox").style.display = "block";
            getLocation();
        };
    } else {
    getLocation();
    }
}



function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}



var longitdue = null;
var latatude = null;

function showPosition(position) {
    latatude = position.coords.latitude;
    longitdue = position.coords.longitude;
    geoToAddress();
}


function geoToAddress() {
    var geocoder = new google.maps.Geocoder();
    var latLng = new google.maps.LatLng(latatude, longitdue);
    if (geocoder) {
        geocoder.geocode({
            'latLng': latLng
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                document.getElementById("alertBox").innerHTML = results[0].formatted_address;
            } else {
                console.log("Geocoding failed: " + status);
            }
        });
    }
}