<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Manipulating arrays</title>
 </head>
 <body>
  <ol>

  <?php
	$arr1 = array( "Alpha", "Bravo", "Charlie" );
	$arr2 = array( "Delta", "Echo", "Foxtrot" );

	$arr = array_merge( $arr1, $arr2 );

	foreach( $arr as $value ) { echo( "$value " ); }
	echo( "<hr>" );

	$arr = array_slice( $arr, 1, 4 );
	foreach( $arr as $value ) { echo( "$value " ); }
	echo( "<hr>" );

	srand( (float)microtime() * 1000000 );
	shuffle( $arr );
	foreach( $arr as $value ) { echo( "$value " ); }
  ?>

  </ol>
 </body>
</html>