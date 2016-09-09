<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Remove array elements</title>
 </head>
 <body>
 <ol>

 <?php
	#create an array containing 5 strings
	$arr = array( "Orange", "Cherry", "Apple", "Banana", "Lemon" );

	#remove element at beginning of the array
	$first = array_shift( $arr );

	#remove element at end of the array
	$last = array_pop( $arr );

	#sort elements alphabetically
	sort( $arr );
	
	#write out values
	foreach( $arr as $value){ echo("$value, ");}
	echo("<br>Removed first element: $first");
	echo("<br>Removed last element: $last");
  ?>

  </ol>
 </body>
</html>