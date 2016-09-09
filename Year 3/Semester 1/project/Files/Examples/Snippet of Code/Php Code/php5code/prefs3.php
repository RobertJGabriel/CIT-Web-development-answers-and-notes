<?php session_start(); ?> 

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Session still running</title>
  <style type = "text/css">
    body { font-family:<?php echo( $_SESSION['font'] ); ?>; }
  </style>
 </head>

 <body>
  <h3>Preferred font family is still
  <?php echo( $_SESSION['font'] ); ?> </h3>
  <a href="prefs1.php">Change font?</a>	
 </body>

</html>