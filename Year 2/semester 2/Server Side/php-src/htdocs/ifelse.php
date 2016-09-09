<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Conditional Branching</title>
</head>
<body>
<?php

# Test one expression.
if ( 4 > 2 ) { echo '<p>Yes, 4 is greater than 2 <br>' ; } 

# Test two expressions.
if ( ( 4 > 2 ) && ( 8 > 4 ) ) { echo '4 is greater than 2 AND 8 is greater than 4<br>' ; }

# Test with a default output.
if ( 4 > 8 ) { echo '4 is greater than 8 <br>' ; } 
else { echo '4 is less than 8 <br>' ; } 

# Test with alternative.
if ( 4 > 8 ) { echo 'This test is True </p>' ; }
elseif ( 8 > 4 ) { echo 'Alternative test is True </p>' ; }
else { echo 'Both tests are False </p>' ; } 

?>
</body>
</html>