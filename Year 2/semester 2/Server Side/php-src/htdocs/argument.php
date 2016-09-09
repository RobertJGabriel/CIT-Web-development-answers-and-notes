<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Arguments</title>
</head>
<body>
<?php

# Create a global variable.
$number = 5 ;

function square( $number )
{
 $result = $number * $number ;
 echo "$number Squared = $result<br>" ;
}

function cube( $number )
{
 $result = $number * $number * $number ;
 echo "$number Cubed = $result<br>" ;
}

square( 3 ) ;
cube( 4 ) ;
square( $number ) ;
cube( $number ) ;

?>
</body>
</html>