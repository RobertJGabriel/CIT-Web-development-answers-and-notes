<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Break Statement</title>
 </head>
 <body>

 <?php
	$i = 0;

	while ( $i < 6 )
	{
	  if( $i == 3) break;
	  $i++;
	}
	echo("Loop stopped at $i by break statement");
 ?>

 </body>
</html>