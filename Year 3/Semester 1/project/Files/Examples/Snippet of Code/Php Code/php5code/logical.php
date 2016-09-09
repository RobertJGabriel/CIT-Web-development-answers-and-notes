<!-- example for PHP 5.0.0 final release -->

<?php 
	$a = true; $b = false;

	#test both operands for true
	$test1 = ( $a and $a )? "true":"false"; 
	$test2 = ( $a and $b )? "true":"false"; 
	$test3 = ( $b and $b )? "true":"false";

 	#test either operand for true
	$test4 = ( $a or $a )? "true":"false"; 
	$test5 = ( $a or $b )? "true":"false"; 
	$test6 = ( $b or $b )? "true":"false";

	#test for single operand is true
	$test7 = ( $a xor $a )? "true":"false"; 
	$test8 = ( $a xor $b )? "true":"false"; 
	$test9 = ( $b xor $b )? "true":"false";

	#invert values 
	$test10 = ( !$a )? "true":"false"; 
	$test11 = ( !$b )? "true":"false"; 

	$result = "AND - 1:$test1 2:$test2 3:$test3<br>";
	$result .= "OR &nbsp;&nbsp;- 1:$test4 2:$test5 3:$test6<br>";
	$result .= "XOR - 1:$test7 2:$test8 3:$test9<br>";
	$result .= "NOT - 1:$test10 2:$test11";
?>

<html>
 <head>
  <title>Logical Operators</title>
 </head>
 <body>

  <?php echo( $result ); ?>

 </body>
</html>