<?php session_start(); 

($_SESSION['count']) ? $_SESSION['count']++ : $_SESSION['count'] = 1;

?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Session running</title>
 </head>

 <body>

 <a href="session_start.php?<?php echo( SID ); ?>">Go to previous page</a>
 <hr>
 PHPSESSID = <?php echo session_id(); ?>
 <br>
 You have been here <?php echo( $_SESSION['count'] ); ?> times in this session

 </body>

</html>