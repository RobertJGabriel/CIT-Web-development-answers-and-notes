<!-- example for PHP 5.0.0 final release -->

<?php
	$user  = $_COOKIE['firstname'];
	$color = $_COOKIE['fontcolor'];
?>

<html>

 <head>
  
  <title>Get Cookie Data</title>
  
  <style type = "text/css">

   body { color: <?php echo( $color ); ?> }
 
  </style>

 </head>

 <body>

  <h1>Hello <?php echo( $user ); ?>! </h1>

 </body>

</html>