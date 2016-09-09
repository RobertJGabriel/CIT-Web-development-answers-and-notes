<!-- example for PHP 5.0.0 final release -->

<?php 
	$addnum = 20+30;
	$addstr="I love "."PHP";
	$sub = 35.75 - 28.25;
	$mul = 8 * 50;
	$mod = 65 % 2;
	$inc = 5; $inc = ++$inc;
	$dec = 5; $dec = --$dec;

	$result = "addnum:$addnum  <br>";
	$result .= "addstr:$addstr <br>";
	$result .= "sub:$sub       <br>";
	$result .= "mul:$mul       <br>";
	$result .= "mod:$mod       <br>";
	$result .= "inc:$inc       <br>";
	$result .= "dec:$dec       <br>";
?>

<html>
 <head>
  <title>Arithmetical Operators</title>
 </head>
 <body>

  <h3> <?php echo($result); ?> </h3>

 </body>
</html>