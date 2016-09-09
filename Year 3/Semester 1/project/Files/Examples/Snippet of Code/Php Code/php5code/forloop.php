<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>For Loop</title>
 </head>
 <body>

 <?php
	$a=0; $b=0;

	for( $i = 0; $i < 5; $i++ )
	{
	  $a += 10;
	  $b += 5;
	}

	echo("At the end of the loop a=$a and b=$b");
 ?>

 </body>
</html>