<!-- example for PHP 5.0.0 final release -->

<?php 
	
	$a = "PHP "; $aa = "Script"; #assign string values
	$a .= $aa; #concatenate strings and assign to $a

	$b = 8; $bb = 4; #assign integer values
	$b += $bb; #add numbers and assign result to $b

	$c = 7.5; $cc = 2.25; #assign float values
	$c -= $cc; #subtract and assign result to $c

	$d = 8; $dd = 4; #assign integer values
	$d *= $dd; #multiply and assign result to $d

	$e = 8; $ee = 4; #assign integer values
	$e /= $ee; #divide and assign result to $e

	$f = 8; $ff = 4; #assign integer values
	$f %= $ff; #divide and assign remainder to $f

	$result =  "\$a ADD AND ASSIGN STRING: $a<br>";
	$result .= "\$b ADD AND ASSIGN INTEGER: $b<br>";
	$result .= "\$c SUBTRACT AND ASSIGN FLOAT: $c<br>";
	$result .= "\$d MULTIPLY AND ASSIGN: $d<br>";
	$result .= "\$e DIVIDE AND ASSIGN: $e<br>";	
	$result .= "\$f MODULO AND ASSIGN: $f";

?>

<html>
 <head>
  <title>Assignment Operators</title>
 </head>
 <body>

  <?php echo( $result ); ?>

 </body>
</html>