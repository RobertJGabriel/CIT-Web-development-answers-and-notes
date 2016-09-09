<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Continue Statement</title>
 </head>
 <body>

 <?php
	$i = 0; 
	$passes = "";

	while ( $i < 5 )
	{
	  $i++;
	  if( $i == 3 ) continue;
	  $passes .= "$i ";
	}
	echo("Loop stopped at $i<br>");
	echo("Completed iterations:$passes");
 ?>

 </body>
</html>