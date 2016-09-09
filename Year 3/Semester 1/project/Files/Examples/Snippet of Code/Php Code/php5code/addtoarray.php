<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Adding array elements</title>
 </head>
 <body>
 <ol>

 <?php
	#create an array containing 3 strings
	$arr = array( "Red ","Green ","Blue" );

	#add elements at beginning of the array
	array_unshift( $arr, "Cyan", "Magenta" );

	#add elements at end of the array
	array_push( $arr, "Yellow", "Black" );
	
	foreach( $arr as $value)
	{
	  echo( "<li>Do you like $value ?</li>");
	}
  ?>

  </ol>
 </body>
</html>