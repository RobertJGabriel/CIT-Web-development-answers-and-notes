<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Do-While Loop</title>
 </head>
 <body>

 <?php
	$i = 0; $num = 50;

	do
	{
	  $num--;
	  $i++;
	}
	while ( $i<1 );
	echo("Loop stopped at $i<br>\$num is now $num");
 ?>

 </body>
</html>