


/*  
Loads and checks what the past panel was
*/

window.onload = function () {
    document.getElementById("Logo").addEventListener('click', logoSwitch, false);

    var temp = localStorage.getItem("panel");
    if (temp == 1) { flickr();} else {canvas2();}
    
};


/*  Stores the varables */

var paint = false;
var canvas;
var cntxt;
var canvastop;
var left;
var fill;
var thickness;
var colors;
var canvas;
var context;
var temp;
var searches = "";
var numItems;


/* when mouse is pressed down */

function doMouseDown(event) {
    paint = true;
    x = event.clientX;
    y = event.layerY;

    cntxt.moveTo(x - left, y - canvastop);
    cntxt.beginPath();
    cntxt.stroke();
}

/* when mouse is pressed up */

function doMouseUp(event) {
    paint = false;
    x = event.clientX;
    y = event.clientY;

    cntxt.lineTo(x - left + 1, y - canvastop + 1);
    cntxt.stroke();
    cntxt.closePath();
}

/* when mouse is pressed moved */

function doMouseMove(event) {
    fill = document.getElementById('fill').value;
    thickness = document.getElementById('width2').value;
    colors = document.getElementById('colors').value;
    cntxt = canvas.getContext("2d");
    cntxt.strokeStyle = colors;
    cntxt.lineWidth = thickness;
    cntxt.lineCap = 'round';

    rect = canvas.getBoundingClientRect();
    canvastop = rect.top;
    left = rect.left;

    if (paint) {
        x = event.clientX;
        y = event.clientY;
        cntxt.lineTo(x - left, y - canvastop);
        cntxt.stroke();
        isKeyPressed(event);
    }
}



/* 
    Downloads the canvas in a new window
    Will work in firefox locally but not chrome
*/

function downloadCanvas() {
    var d = canvas.toDataURL("image/png");
    window.open(canvas.toDataURL('image/png'));
}

/* This is for when the shift key is pressed and draws the cricle to the users movement */

function isKeyPressed(event) {
    if (event.shiftKey == 1) {
        var fill = document.getElementById('fill').value;
        var thickness = document.getElementById('width2').value;
        var colors = document.getElementById('colors').value;
        
        //draws a line with fills the path
          cntxt.lineWidth = 5;
          cntxt.fillStyle = fill;
          cntxt.strokeStyle = colors;
          cntxt.lineTo(x - findPos(canvas)[0], y - findPos(canvas)[1]);
          cntxt.fill();
          cntxt.stroke();
    }
}



function findPos(obj) {
    //finds mouse coordinates relatively to canvas begin (0,0) 
    var curleft = curtop = 0;
    if (obj.offsetParent) {
        do {
            curleft += obj.offsetLeft;
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent);
        return [curleft, curtop];
    }
}


/*
Clears the left and right panel
sets the localstoage and reloads the panels

*/
function logoSwitch() {

    if (!document.getElementById("menu")) {
        }else {
    var parent = document.getElementById("left");
    var child = document.getElementById("menu");
        parent.removeChild(child);   
    
     var mydiv = document.getElementById('right');
        while (mydiv.firstChild){ mydiv.removeChild(mydiv.firstChild);}    
}
    temp = localStorage.getItem("panel");
    if (temp == 1) {canvas2();localStorage.clear();
    } else {flickr();localStorage.setItem("panel", "1");
    }
}


/*
Loads the flicker information tot he left and right panel
Theres alot here sorry
*/

function flickr() {
     document.getElementById('Logo').style.background = 'url(images/' + 'dj.png' + ')center';
    var leftDiv = document.getElementById('left');
    var rightDiv = document.getElementById('right');

    var Div = document.createElement('div');
        Div.setAttribute('id', 'menu');
        leftDiv.appendChild(Div);

    var menuDiv = document.getElementById('menu');

    var searchTerm = document.createElement('input');
        searchTerm.setAttribute('class', 'searchterm');
        searchTerm.setAttribute('name', 'searchterm');
        searchTerm.setAttribute('value',localStorage.getItem('searchterm'));
        menuDiv.appendChild(searchTerm);

    var searchButton = document.createElement('button');
        searchButton.setAttribute('id', 'search');
        searchButton.innerHTML = "Search";
        menuDiv.appendChild(searchButton);


    var addButton = document.createElement('button');
        addButton.setAttribute('id', 'add');
        addButton.innerHTML = "+";
        menuDiv.appendChild(addButton);


    var resultsDiv = document.createElement('div');
        resultsDiv.setAttribute("id", 'results');
        menuDiv.appendChild(resultsDiv);

    document.getElementById("search").addEventListener('click', searchButtonrun, false);
    document.getElementById("add").addEventListener('click', addbuttons, false);
 
}

/*
Loads the canvas information to the left and right panel
Theres alot here sorry
*/
function canvas2() {
      
     document.getElementById('Logo').style.background = 'url(images/' + 'w.png' + ')center';
    var leftDiv = document.getElementById('left');
    var rightDiv = document.getElementById('right');

    var Div = document.createElement('div');
        Div.setAttribute('id', 'menu');
        leftDiv.appendChild(Div);

    var menuDiv = document.getElementById('menu');

    var downloadButton = document.createElement('button');
        downloadButton.setAttribute('id', 'download');
        downloadButton.innerHTML = "Download";
        menuDiv.appendChild(downloadButton);

    var clearButton = document.createElement('button');
        clearButton.setAttribute('id', 'clear');
        clearButton.innerHTML = "Clear";
        menuDiv.appendChild(clearButton);

    var lineH2 = document.createElement('h2');
        lineH2.innerHTML = "Line";
        menuDiv.appendChild(lineH2);

    var lineWidth = document.createElement('select');
        lineWidth.setAttribute("id", "width2");

    var value = ["4", "10", "15"];
    var text = ["Light", "Middle", "Thick"];

    for (var i = 0; i < text.length; i++) {
        var options = document.createElement('option');
            options.setAttribute("value", value[i]);
            options.innerHTML = text[i];
            lineWidth.appendChild(options);
    }

    menuDiv.appendChild(lineWidth);

    
    var colorH2 = document.createElement('h2');
        colorH2.innerHTML = "Colors";
        menuDiv.appendChild(colorH2);

    var lineWidth2 = document.createElement('select');
        lineWidth2.setAttribute("id", "colors");

    var value1 = ["#000000", "#e74c3c", "#2980b9", "#2ecc71", "#d35400", "#8e44ad", "#ecf0f1"];
    var text1 = ["Black", "Red", "Blue", "Green", "Orange", "Purple", "White"];

    for (var i = 0; i < text1.length; i++) {
        var optionss = document.createElement('option');
            optionss.setAttribute("value", value1[i]);
            optionss.innerHTML = text1[i];
            lineWidth2.appendChild(optionss);
    }
    menuDiv.appendChild(lineWidth2);


    var colorH2 = document.createElement('h2');
        colorH2.innerHTML = "Fill";
        menuDiv.appendChild(colorH2);


    var lineWidth3 = document.createElement('select');
        lineWidth3.setAttribute("id", "fill");

    for (var i = 0; i < text1.length; i++) {
        var options = document.createElement('option');
            options.setAttribute("value", value1[i]);
            options.innerHTML = text1[i];
            lineWidth3.appendChild(options);
    }
    menuDiv.appendChild(lineWidth3);

    var newCanvas = document.createElement('canvas');
        newCanvas.width = 400;
        newCanvas.height = 600;
        newCanvas.setAttribute("id", "canvas_1");
        document.getElementById("right").appendChild(newCanvas);
    

        canvas = document.getElementById("canvas_1");
        canvas.addEventListener("mousedown", doMouseDown, false);
        canvas.addEventListener("mouseup", doMouseUp, false);
        canvas.addEventListener("mousemove", doMouseMove, false);
        document.getElementById("clear").addEventListener('click', cleanStart, false);
        document.getElementById("download").addEventListener('click', downloadCanvas, false);
        
    cleanStart();
    
}

/*
Clears the canvas and resets it
*/

function cleanStart() {
   var canvas = document.getElementById('canvas_1');
   var  cntxt = canvas.getContext('2d');
   var imageObj = new Image();

    imageObj.onload = function () {
         cntxt.drawImage(imageObj, 0, 0);
    };
    imageObj.src = 'images/mona.jpg';
}

/*
Adds the search terms and the input limits on how many
*/

function addbuttons() {

    numItems = document.getElementsByName("searchterm").length;
    if (numItems == 5) {} else {
        
        var menuDiv = document.getElementById('menu');
      
        var inputsearch = document.createElement('input');
            inputsearch.setAttribute('class', 'searchterm');
            inputsearch.setAttribute('name', 'searchterm');
            inputsearch.innerHTML = "Line";
        menuDiv.appendChild(inputsearch);

        var removeButton = document.createElement('button');
            removeButton.setAttribute('class', 'remove');
            removeButton.setAttribute('name', 'remove');
            removeButton.addEventListener('click', removebutton, false);
            removeButton.innerHTML = "-";
        menuDiv.appendChild(removeButton);
   
    }
}


// Removes the button

function removebutton() {
    numItems = document.getElementsByName("searchterm").length;
    var numremove = document.getElementsByName("remove").length;
    var menuDiv = document.getElementById('menu');

    if (numItems == 0) {} else {
        menuDiv.removeChild(document.getElementsByName('searchterm')[numItems - 1]);
        menuDiv.removeChild(document.getElementsByName('remove')[numremove - 1]);
    }
}

/*

This was used using jquert , just to show i could use it and the api key.
:)

*/

function searchButtonrun() {
    localStorage.setItem("searchterm",document.getElementsByName("searchterm")[0].value);
    $(document).ready(function ($) {
        searches = "";
        $(".searchterm").each(function (index) {

            searches += $(this).val() + "+";
        });
        $("#search").html("loading");

        $("h1").remove();
        $("img").remove();
        var term = searches;
        var url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=8b010ef32af4006f4fac0249a746289e&tags=toy&safe_search=1&per_page=1";
        var src;
        $.getJSON(url + "&format=json&jsoncallback=?", function (data) {

            if (data.photos.photo.length === 0) {

                $("<h1/>").attr("class", 's').appendTo("#right");
                $(".s").html("None found :-o");
            }
            $.each(data.photos.photo, function (i, item) {
                src = "http://farm" + item.farm + ".static.flickr.com/" + item.server + "/" + item.id + "_" + item.secret + "_m.jpg";
                $("<img/>").attr("src", src).appendTo("#right");
                $('img').css('opacity', 0);
                $('img').show();
                $('img').animate({
                    opacity: 1
                }, {
                    queue: false,
                    duration: 'slow'
                });
                $('img').animate({
                    top: "-10px"
                }, 'slow');

                if (i == 1000) {
                    return false;
                }

            });
            $("#search").html("Search");
        });
    });
}