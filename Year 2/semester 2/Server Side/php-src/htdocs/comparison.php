<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Comparison</title>
</head>
<body>
<?php

# Create and initialize variables.
$zero = 0 ;
$nil = 0 ;
$one = 1 ;
$upr = 'A' ;
$lwr = 'a' ;

# Display the result of equality operations.
$result = ($zero == $nil) ? 'TRUE' : 'FALSE' ; echo "0 == 0 is $result <br>" ;
$result = ($zero == $one) ? 'TRUE' : 'FALSE' ; echo "0 == 1 is $result <br>" ;
$result = ($upr == $lwr) ? 'TRUE' : 'FALSE' ; echo "A == a is $result <br>" ;
$result = ($upr != $lwr) ? 'TRUE' : 'FALSE' ; echo "A != a is $result <hr>" ;

# Display the result of greater/lesser operations.
$result = ($one > $nil) ? 'TRUE' : 'FALSE' ; echo "1 > 0 is $result <br>" ;
$result = ($zero >= $nil) ? 'TRUE' : 'FALSE' ; echo "0 >= 0 is $result <br>" ;
$result = ($one <= $nil) ? 'TRUE' : 'FALSE' ; echo "1 <= 0 is $result <br>" ;

?>
</body>
</html>