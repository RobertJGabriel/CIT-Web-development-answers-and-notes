<!-- example for PHP 5.0.0 final release -->

<?php 

	function addup( $a = 32, $b = 32, $c = 32)
	{
	  $total = $a + $b + $c;
	  echo("$a + $b + $c = $total");

	}

?>
 
<html>
 <head>
  <title>Function Arguments</title>
 </head>
 <body>

  <h3> <?php addup(8, 16, 24); ?> </h3>

  <h3> <?php addup(8, 16); ?> </h3>

 </body>
</html>