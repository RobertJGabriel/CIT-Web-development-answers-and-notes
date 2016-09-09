<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>List array values</title>
 </head>
 <body> 
  <ol>

  <?php
	$arr = array( "Red", "Green", "Blue", "Cyan", "Magenta", "Black", "Yellow" );

	foreach( $arr as $value ) 
	{
	  echo("<li>Do you like $value ?</li>");
	}
  ?>

  </ol> 
 </body>
</html>