<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Random Numbers</title>
 </head>
 <body>

 <?php
	srand( microtime() * 1000000 );
	$num = rand( 1, 100 );

	echo( "Microtime:" . microtime() . "<br>");
	echo( "A random number: " . $num . "<br>");
	$num = rand( 1, 100 );
	echo( "Another random number:" . $num );
 ?>

 </body>
</html>