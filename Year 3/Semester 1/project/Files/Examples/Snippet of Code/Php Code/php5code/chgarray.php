<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Changing array values</title>
 </head>
 <body>

 <?php
	#create an array containing 3 strings
	$arr = array( "Red ", "Green ", "Blue" );

	echo( $arr[0] . $arr[1] . $arr[2] . "<hr>" );

	#assign new numeric values
	$arr[0] = 44;
	$arr[1] = 12.5;
	$arr[2] = $arr[0] + $arr[1];
		
	echo( "$arr[0] + $arr[1] = $arr[2]" );

 ?>

 </body>
</html>