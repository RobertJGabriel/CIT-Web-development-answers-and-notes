<!-- example for PHP 5.0.0 final release -->

<?php 
	$num;

	function make_triple($arg)
	{
	  global $num;
	  $num = $arg + $arg +$arg;
	  thrice();

	}

	function thrice()
	{
	  global $num;
	  echo("The value is $num");
	} 
?>

<html>
 <head>
  <title>Variable Scope</title>
 </head>
 <body>

  <h3> <?php make_triple(4); ?> </h3>

 </body>
</html>