<?php session_start(); ?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Session starter</title>
 <head>

 <body>

 <a href="next.php?<?php echo( SID ); ?>">Next page</a>
 <hr>

 PHPSESSID = 
 <?php 

  # SID is a key=value pair -
  # use session_id() to get just the value
  echo ( session_id() ); 

 ?>

 </body>
 
</html>

