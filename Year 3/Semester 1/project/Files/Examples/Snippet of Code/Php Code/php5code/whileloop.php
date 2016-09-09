<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>While Loop</title>
 </head>
 <body>

 <?php
	$i=0; $num=50;

	while( $i<10 )
	{
	  $num--;
	  $i++;
	}
	echo("Loop stopped at $i<br>\$num is now $num");
 ?>

 </body>
</html>