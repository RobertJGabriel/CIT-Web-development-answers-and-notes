 ljs.load(['assets/js/main/firebase_core.js', 'assets/js/main/jquery.js'], 'assets/js/main/jquery.serialize-object.compiled.js', 'assets/js/main/path.min.js', 'assets/js/main/bootstrap.js', 'assets/js/main/responisve.js', 'assets/js/main/firebase.js', 'assets/js/main/ripples.min.js', 'assets/js/main/material.min.js', function () {

     // This command is used to initialize some elements and make them work properly
     $.material.init();


     $(".responsive-calendar").responsiveCalendar({
         time: '2013-05',
         events: {
             "2013-04-30": {
                 "number": 5,
                 "url": "http://w3widgets.com/responsive-slider"
             },
             "2013-04-26": {
                 "number": 1,
                 "url": "http://w3widgets.com"
             },
             "2013-05-03": {
                 "number": 1
             },
             "2013-06-12": {}
         }
     });




     $("#menu-toggle").click(function (e) {
         e.preventDefault();
         $("#wrapper").toggleClass("toggled");
     });

 });



 function regexUrlextensioncheck(n) {

     var s = document.URL,
         e = new RegExp(n);


     return e.test(s);
 }
