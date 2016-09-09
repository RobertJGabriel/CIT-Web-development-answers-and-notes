<?php  session_start(); ?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Session running</title>
  <style type="text/css">
    body { font-family:<?php echo $_SESSION['font']; ?> }
  </style>
 </head>

 <body>
 <h3>Preferred font family is 
 <?php echo $_SESSION['font']; ?></h3>
 <a href="prefs3.php?<?php echo( SID ); ?>">Next page</a>
 </body>

</html>