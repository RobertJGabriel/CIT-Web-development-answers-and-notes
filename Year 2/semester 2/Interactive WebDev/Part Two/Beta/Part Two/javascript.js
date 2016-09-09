$(document).ready(function ($) {

    var searches = "";
    var numItems;

var temp = localStorage.getItem("panel");
if ( temp == 1){
  flickr() ;
    
}else{
   

  canvas();
}


    // Removes the Menu and Logo
    $("#Logo").click(function () {
               $("#menu").remove();
        $("#right").empty();
        if (temp == 1){
         localStorage.setItem("panel", "0");   
              
        location.reload();
        }else{
            localStorage.setItem("panel", "1");
        location.reload();
        }
        
        
 
    });



    function flickr() {
        $("<div/>").attr("id", 'menu').appendTo("#left");

        $("<input/>").attr("class", 'searchterm').appendTo("#menu");
        $("<button/>").attr("id", 'search', 'value', 'search').appendTo("#menu");
        $("<button/>").attr("id", 'add', 'value', '++++++').appendTo("#menu");
        $("#add").html("+");
        $("#search").html("Search");
        
        
             $("<div/>").attr("id", 'results').appendTo("#right");
// Store
localStorage.setItem("panel", "1");
    }

    function canvas() {

        $("<div/>").attr("id", 'menu').appendTo("#left");
        $("<button/>").attr("id", 'download').appendTo("#menu");
        $("#download").html("Download");

        $("<br/>").appendTo("#menu");

        $("<button/>").attr("id", 'clear').appendTo("#menu");
        $("#clear").html("Clear");

        $("<br/>").appendTo("#menu");



  $("#menu").append(' <h2>Line : </h2>');
      $("#menu").append('<br/>');
        $("<select/>").attr("id", 'width2').appendTo("#menu");
        $("#width2").append('<option value="4" >Light</option>');
        $("#width2").append('<option value="10">Middle</option>');
        $("#width2").append('<option value="15">Thick</option>');
  $("#menu").append(' <h2>Colors: </h2>');
              $("#menu").append('<br/>');
        $("<select/>").attr("id", 'colors').appendTo("#menu");
        $("#colors").append(' <option value="#000000">Black</option>');
        $("#colors").append('  <option value="#e74c3c">Red</option>');
        $("#colors").append(' <option value="#2980b9">Blue</option>');
               $("#colors").append('<option value="#2ecc71">Green</option>');
               $("#colors").append('<option value="#d35400">Orange</option>');
               $("#colors").append('<option value="#8e44ad">Purple</option>');
               $("#colors").append('<option value="#ecf0f1">White</option>');
        
        

                  $("#menu").append(' <h2>Fill : </h2>');
               $("#menu").append('<br/>');
             $("<select/>").attr("id", 'fill').appendTo("#menu");
        $("#fill").append(' <option value="#000000">Black</option>');
        $("#fill").append('  <option value="#e74c3c">Red</option>');
        $("#fill").append(' <option value="#2980b9">Blue</option>');
               $("#fill").append('<option value="#2ecc71">Green</option>');
               $("#fill").append('<option value="#d35400">Orange</option>');
               $("#fill").append('<option value="#8e44ad">Purple</option>');
               $("#fill").append('<option value="#ecf0f1">White</option>');
    
        
        var newCanvas = $('<canvas/>');
        newCanvas[0].id ="imageView";
    newCanvas[0].height = 600;
    newCanvas[0].width = 400;

$('#right').append(newCanvas);
        
     
      
      localStorage.setItem("panel", "0");
    }





    //Add Input and Search buttons
    $("#add").click(function () {
        numItems = $('.searchterm').length;
        if (numItems == 5) {} else {
            $("<input/>").attr("class", 'searchterm').appendTo("#menu");
            $("<button/>").attr("class", 'remove').appendTo("#menu");
            $(".remove").html("-");
        }
    });

    //Removes input and buttons
    $(document).on('click', '.remove', function () {
        numItems = $('.searchterm').length;
        $(".searchterm:eq(" + 1 + ")").remove();
        $(".remove:eq(" + 0 + ")").remove();
    });



    $("#search").click(function () {
        searches = "";
        $(".searchterm").each(function (index) {

            searches += $(this).val() + "+";
        });
        $("#search").html("loading");

        $("h1").remove();
        $("img").remove();
        var term = searches;
        var url = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=8b010ef32af4006f4fac0249a746289e&tags=" + term + "&safe_search=1&per_page=1000";
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

                if (i == 12) {
                    return false;
                }

            });
            $("#search").html("Search");
        });
    });

});


















// Keep everything in anonymous function, called on window load.

    window.addEventListener('load', function () {
        var canvas, context, canvaso, contexto;
        // The active tool instance.
        var tool;
   

        function init() {
            // Find the canvas element.
            canvaso = document.getElementById('imageView');
            // Get the 2D canvas context
            context = canvaso.getContext('2d');
            

            // Activate the default tool.
            tool = new test();
            // Attach the mousedown, mousemove and mouseup event listeners.
            document.getElementById("clear").addEventListener('click', newish, false);
            document.getElementById("download").addEventListener('click', download, false);
        
           // canvaso.addEventListener('mousedown', isKeyPressed, false);
            canvaso.addEventListener('mousedown', ev_canvas, false);
            canvaso.addEventListener('mousemove', ev_canvas, false);
            canvaso.addEventListener('mouseup', ev_canvas, false);
        }

        function isKeyPressed(event, e) {
            if (event.altKey == 1) {
                var fill = document.getElementById('fill').value;
                var thickness = document.getElementById('width2').value;
                var colors = document.getElementById('colors').value;
                var canvas = document.getElementById('imageView');
                var context = canvas.getContext('2d');
                //draws a line with fills the path
                context.lineWidth = 5;
                context.fillStyle = fill; 
                context.strokeStyle = colors;
                context.lineTo(posx - findPos(canvas)[0], posy - findPos(canvas)[1]);
                context.fill();
                context.stroke();
            } else {
                // alert("The shift key was NOT pressed!");
            }
        }

        function newish() {
            var canvas = document.getElementById('imageView');
            var context = canvas.getContext('2d');
       
            
         
            context.clearRect(0, 0, canvas.width, canvas.height);
               context.drawImage(bgImg, 0, 0);   
			   init();
        }

        function download() {
 
            var c=document.getElementById('imageView');
            var d=c.toDataURL("image/png");
            window.open(c.toDataURL('image/png'));
        }
      
        
        
    

        // The general-purpose event handler. This function just determines the mouse 
        // position relative to the canvas element.

        function ev_canvas(ev) {
            ev._x = ev.offsetX;
            ev._y = ev.offsetY;
        
             if (!e) var e = window.event;
                // check if the browser supports the pageX and pageY properties.
                if (e.pageX || e.pageY) {
                    posx = e.pageX;
                    posy = e.pageY;
                } else if (e.clientX || e.clientY) {
                    posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
                    posy = e.clientY + document.body.scrollTop +  document.documentElement.scrollTop;
                }
            
            
            // Call the event handler of the tool.
            var func = tool[ev.type];

                func(ev);
            
        }

        // This function draws the #imageTemp canvas on top of #imageView, after which 
        // #imageTemp is cleared. This function is called each time when the user 
        // completes a drawing operation.



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


        // The drawing pencil.
    function test () {
            var tool = this;
            this.started = false;
            // This is called when you start holding down the mouse button.
            // This starts the pencil drawing.
            this.mousedown = function (ev) {
                context.beginPath();
                context.moveTo(ev._x, ev._y);
                tool.started = true;
            };
            // This function is called every time you move the mouse. Obviously, it only 
            // draws if the tool.started state is set to true (when you are holding down 
            // the mouse button).
            this.mousemove = function (ev,e) {
                if (tool.started) {
                                var bgImg = new Image();
bgImg.src = "mona.jpg";
            
            context.drawImage(bgImg, 0, 0);
                    var thickness = document.getElementById('width2').value;
                    var colors = document.getElementById('colors').value;
                    context.lineTo(ev._x, ev._y);
                    context.lineWidth = thickness;
                    context.strokeStyle = colors;
                    context.stroke();
                    isKeyPressed(event, e);
                }
            };
            // This is called when you release the mouse button.
            this.mouseup = function (ev) {
                if (tool.started) {
                    tool.mousemove(ev);
                    tool.started = false;
                    img_update();
                }
            };
        };
        init();}, false);

















