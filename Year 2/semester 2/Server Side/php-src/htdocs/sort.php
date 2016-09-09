<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Sorted Arrays</title>
</head>
<body>
<?php

# Create and initialize an array variable.
$cars = array( 'Dodge' => 'Viper' , 'Chevrolet' => 'Camaro' , 'Ford' => 'Mustang' ) ;

# Display the value of every element.
echo '<dl><dt>Original Element Order :<dd>' ;
foreach( $cars as $key => $value ) { echo ' &bull; ' , $key . ' ' . $value ; } 

# Display the value of every element.
asort( $cars ) ;
echo '<dt>Sorted Into Value Order :<dd>' ;
foreach( $cars as $key => $value ) { echo ' &bull; ' , $key . ' ' . $value ; }  

# Display the value of every element.
ksort( $cars ) ;
echo '<dt>Sorted Into Key Order :<dd>' ;
foreach( $cars as $key => $value ) { echo ' &bull; ' , $key . ' ' . $value ; } 
echo '</dl>' ;

?>
</body>
</html>