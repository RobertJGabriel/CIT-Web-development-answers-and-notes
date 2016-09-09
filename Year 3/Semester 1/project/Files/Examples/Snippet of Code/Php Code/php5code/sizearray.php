<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Getting array size</title>
 </head>
 <body> 
  <ul>

  <?php
	$arr = array();

	for( $i = 0; $i < 3; $i++ )
	{
	  $arr[ $i ] ="<li>This is element $i</li>";
	}

	foreach( $arr as $value )
	{
	  echo($value);
	}

	$size = count( $arr );
	echo( "<li>Total number of elements is $size </li>" );
  ?>

  </ul>
 </body>
</html>