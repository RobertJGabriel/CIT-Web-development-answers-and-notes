<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Arithmetic</title>
</head>
<body>
<?php

# Create and initialize variables.
$a = 4 ; 
$b = 8 ;

# Display the result of arithmetical operations.
$result = $a + $b ; echo "Addition : $result <br>" ;
$result = $b - $a ; echo "Subtraction : $result <br>" ;
$result = $a * $b ; echo "Multiplication : $result <br>" ;
$result = $b / $a ; echo "Division : $result <br>" ;
$result = $a % $b ; echo "Modulo : $result <br>" ;

# Display the result of increment/decrement operations.
$a++ ; echo "Increment : $a <br>" ;
$b-- ; echo "Decrement : $b <br>" ;

?>
</body>
</html>