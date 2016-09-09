/*
  ____                                                               
 |  _ \                                                              
 | |_) |_   _                                                        
 |  _ <| | | |                                                       
 | |_) | |_| |                                                       
 |____/ \__, |_               _      _____       _          _      _ 
 |  __ \ __/ | |             | |    / ____|     | |        (_)    | |
 | |__) |___/| |__   ___ _ __| |_  | |  __  __ _| |__  _ __ _  ___| |
 |  _  // _ \| '_ \ / _ \ '__| __| | | |_ |/ _` | '_ \| '__| |/ _ \ |
 | | \ \ (_) | |_) |  __/ |  | |_  | |__| | (_| | |_) | |  | |  __/ |
 |_|  \_\___/|_.__/ \___|_|   \__|  \_____|\__,_|_.__/|_|  |_|\___|_|
                                                                     
                                                                     
*/





function load() {

    // Adds the effects handlers to the buttons and select tags
    document.getElementById("save").addEventListener('click', save, false);
    document.getElementById("title").addEventListener('click', title, false);
    document.getElementById('oneInput').addEventListener('keyup', enterkey, false);	
    var articles = document.getElementsByTagName('select');
    for (var i = 0; i < articles.length; i++) {
        articles[i].addEventListener('change', drawCanvas, false);
    }
    //Draws Canavas
    drawCanvas();
    loadImages();
}

function save() {

    // Gets canavas and the dataURL and saves it to the local storge.
    var canvas = document.getElementById("graph");
    localStorage.setItem(localStorage.length + 1, canvas.toDataURL());

    // reload from localStorage
    loadImages();

}

function title() {
    //Redraws canavas with the title
    drawCanvas();
}


function dele(id) {
    //delete image from array
    localStorage.removeItem(id);

    var temp = new Array();
    for (var i = 0; i < localStorage.length; i++) {
        temp.push(localStorage.getItem(localStorage.key(i)));
    }
    localStorage.clear();

    for (var i = 0; i < temp.length; i++) {
        localStorage.setItem(i, temp[i]);
    }
    loadImages();
}

function loadImages() {

    //Gets localStorge amount
    var ul = document.getElementById('imageList');

    //Removes all the Li tages form the imageList
    while (ul.firstChild) {
        ul.removeChild(ul.firstChild);
    }
    var x = 0;
    for (var x = 0; x <= localStorage.length ; x++) {
        if (localStorage.getItem(x) === null) {
        } else {
            //  alert(x + "im there");
            var img = new Image();
            img.onload = function () {
                context.drawImage(img, 0, 0);
            }
            
            // Button
            var divo = document.createElement('div');
            divo.setAttribute("class", "badge");
            divo.setAttribute("id", x);
            divo.setAttribute("onclick", "dele(this.id)");

            //image
            var img = document.createElement('img');
            img.setAttribute("height", "200");
            img.setAttribute("alt", "image" + x);
            img.setAttribute("width", "270");
            img.src = localStorage.getItem(x);
//Image link for light box
            var link = document.createElement('a');
            link.setAttribute("href", "#image-" + x);
            link.appendChild(img);
// Link to view Image
            var source = document.createElement('a');
            source.setAttribute("href", localStorage.getItem(x));
            source.innerHTML = "Link is here ";
//Banner/div for the bottom at thr bottom
            var banner = document.createElement("div");
            banner.setAttribute("id", "linko");
            banner.appendChild(source);
//Creates light box div
            var BigDiv = document.createElement('div');
            BigDiv.setAttribute("class", "lightBox");
            BigDiv.setAttribute("id", "image-" + x);
//Creates bigger image
            var imgBig = document.createElement('img');
            imgBig.src = localStorage.getItem(x);
            imgBig.setAttribute("alt", "image" + x);

            BigDiv.appendChild(imgBig);
            BigDiv.appendChild(banner);

            var list = document.createElement('li');
            list.appendChild(divo);
            list.appendChild(link);
            list.appendChild(BigDiv);
            ul.appendChild(list);


        }
    }

}



(function () {
    if (window.addEventListener) {
        //close lightbox when key let up
        window.addEventListener('keyup', function () {

            var appBanners = document.getElementsByClassName('lightBox'),i;
            for (var i = 0; i < appBanners.length; i++) {
                appBanners[i].style.display = 'none';
            }
        }, false);
    }
})();

function drawCanvas() {
    var graph = document.getElementById("graph");
    var graphHeight = 400;
    var graphWidth = 690;
    var xPadding = 40;

    var mondayValue = parseInt(document.getElementById('one').value);
    var tuesdayValue = parseInt(document.getElementById('two').value);
    var wednesdayValue = parseInt(document.getElementById('three').value);
    var thursdayValue = parseInt(document.getElementById('four').value);
    var fridayValue = parseInt(document.getElementById('five').value);
    var saturdayValue = parseInt(document.getElementById('six').value);
    var sundayValue = parseInt(document.getElementById('seven').value);
    var titleValue = document.getElementById("oneInput").value;

    var yValues = new Array();
    yValues[0] = mondayValue;
    yValues[1] = tuesdayValue;
    yValues[2] = wednesdayValue;
    yValues[3] = thursdayValue;
    yValues[4] = fridayValue;
    yValues[5] = saturdayValue;
    yValues[6] = sundayValue;
    var x = 0;

    var daysOfWeek = new Array();
    daysOfWeek[0] = "Monday";
    daysOfWeek[1] = "Tuesday";
    daysOfWeek[2] = "Wedensday";
    daysOfWeek[3] = "Thursday";
    daysOfWeek[4] = "Friday";
    daysOfWeek[5] = "Saturday";
    daysOfWeek[6] = "Sunday";
    // Returns the max yValues value in our data list

    function getMaxY() {
        var max = 100;
        for (var i = 0; i < yValues.length; i++) {
            if (yValues[i] > max) {
                max = yValues[i];
            }
        }
        max += 10 - max % 10;

        return max;
    }
    // Return the x pixel for a graph point

    function getXPixel(val) {
        return ((graphWidth - xPadding) / daysOfWeek.length) * val + (xPadding * 2);
    }
    // Return the y pixel for a graph point

    function getYPixel(val) {
        return graphHeight - (((graphHeight ) / getMaxY()) * val) ;
    }
    var c = graph.getContext('2d');
    c.clearRect(0, 0, graph.width, graph.height);
    //Title Value
    c.lineWidth = 2;
    c.strokeStyle = 'black';
    c.fillText(titleValue, 600, 100);
    
// Draw the axises
    c.beginPath();
    c.moveTo(xPadding, 0);
    c.lineTo(xPadding, graphHeight );
    c.lineTo(graphWidth, graphHeight );
    c.stroke();

    // Draw the daysOfWeek value 
    for (var i = 0; i < yValues.length; i++) {
        c.font = 'italic 11pt sans-serif';
        c.textAlign = "center";
        c.fillStyle = 'Black';
        c.fillText(daysOfWeek[i], getXPixel(i), graphHeight + 30);
        c.clearRect(0, 0, c.width, c.height);
    }
    c.strokeStyle = 'black';
    c.clearRect(0, 0, c.width, c.height);
    // Draw the line graph
    c.beginPath();
    c.moveTo(getXPixel(0), getYPixel(yValues[0]));
    for (var i = 1; i < yValues.length; i++) {
        c.lineTo(getXPixel(i), getYPixel(yValues[i]));
        c.clearRect(0, 0, c.width, c.height);
    }
    c.stroke();
    // Draw the dots
    c.fillStyle = 'black';
    for (var i = 0; i < yValues.length; i++) {
        c.beginPath();
        c.arc(getXPixel(i), getYPixel(yValues[i]), 10, 0, Math.PI * 2, true);
        c.fill();
        c.clearRect(0, 0, c.width, c.height);
    }

}
// eNTER KEY FOR TITLE.
function enterkey(e){
var code = e.keyCode;
if(code==13){
title();
}
}





