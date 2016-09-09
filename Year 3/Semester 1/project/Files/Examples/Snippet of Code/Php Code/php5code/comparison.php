<!-- example for PHP 5.0.0 final release -->

<?php 
	$a = ("PHP"=="PHP")? "true":"false";
	$b = ("PHP"=="PERL")?"true":"false";

	$c = (1.785==1.785)? "true":"false";
	$d = (5 != 5)?"true":"false";

	$e = (true == true)?"true":"false";
	$f = (false != false)?"true":"false";

	$g = (100<200)?"true":"false";
	$h = (100<100)?"true":"false";

	$i = (100<=100)?"true":"false";
	$j = ( -1 > 1 )?"true":"false";
	

	$result =  "TEST STRINGS \$a:$a  \$b:$b<br>";
	$result .= "TEST NUMBERS \$c:$c \$d:$d<br>";
	$result .= "TEST BOOLEANS \$e:$e \$f:$f<br>";
	$result .= "TEST LESS THAN \$g:$g \$h:$h<br>";
	$result .= "TEST LESS THAN OR EQUAL \$i:$i<br>";	
	$result .= "TEST GREATER THAN \$j:$j";

?>

<html>
 <head>
  <title>Comparison Operators</title>
 </head>
 <body>

  <?php echo( $result ); ?>

 </body>
</html>