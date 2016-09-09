<!-- example for PHP 5.0.0 final release -->

<?php 
	function show_number($num)
	{
	  $new_number = make_double($num);
	  echo("The value is $new_number");
	}

	function make_double($arg)
	{
	  return $arg + $arg;
	} 
?>

<html>
 <head>
  <title>PHP Functions</title>
 </head>
 <body>

  <h3> <?php show_number(4); ?> </h3>

 </body>
</html>