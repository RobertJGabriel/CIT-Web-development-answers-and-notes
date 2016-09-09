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
            
            var bgImg = new Image();
bgImg.src = "mona.jpg";
            
            context.drawImage(bgImg, 0, 0);
            // Activate the default tool.
            tool = new test();
            // Attach the mousedown, mousemove and mouseup event listeners.
            document.getElementById("clear").addEventListener('click', newish, false);
            document.getElementById("download").addEventListener('click', download, false);
              document.getElementById("Logo").addEventListener('click', switchSides, false);
           // canvaso.addEventListener('mousedown', isKeyPressed, false);
            canvaso.addEventListener('mousedown', ev_canvas, false);
            canvaso.addEventListener('mousemove', ev_canvas, false);
            canvaso.addEventListener('mouseup', ev_canvas, false);
        }
function switchSides(){
        
 alert("switch");       
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
