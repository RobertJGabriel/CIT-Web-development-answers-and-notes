<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>If Statement</title>
 </head>
 <body>

 <?php
	$num = 7;
	if ( $num % 2 != 0 ) 
	{
		$msg = "$num is an odd number.";
		echo( $msg );
	}
 ?>

 </body>
</html>