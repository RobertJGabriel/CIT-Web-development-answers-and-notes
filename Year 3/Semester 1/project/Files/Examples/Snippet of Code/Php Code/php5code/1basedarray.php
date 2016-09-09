<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>One-based array index</title>
 </head>
 <body>
  <ol>

  <?php
	$arr = array( 1 => "1st", "2nd", "3rd", "4th", "5th" );
	
	for( $i = 1; $i <= sizeof($arr); $i++ )
	{
	  echo("Position $i - Element value: $arr[$i]<br>");
	}
  ?>

  </ol>
 </body>
</html>