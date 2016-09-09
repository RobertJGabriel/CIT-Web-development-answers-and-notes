<!-- example for PHP 5.0.0 final release -->

<?php 
	function is_odd()
	{ 
	  global $num;
	  echo("$num is an odd number<hr>");
	}

	function is_even()
	{ 
	  global $num;
	  echo("$num is an even number<hr>");
	}
?>

<html>
 <head>
  <title>Conditional Operator</title>
 </head>
 <body>
	
 <?php
	$num = 57;
	( $num % 2 != 0 ) ? is_odd() : is_even();

	$num = 44;
	( $num % 2 != 0 ) ? is_odd() : is_even();
 ?>

 </body>
</html>