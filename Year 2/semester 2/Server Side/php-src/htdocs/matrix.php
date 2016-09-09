<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Matrix</title>
</head>
<body>
<?php

# Create and initialize array variables.
$letters = array( 'A' , 'B' , 'C' ) ;
$numbers = array( 1 , 2 , 3 ) ;
$matrix = array( 'Letter' => $letters , 'Number' => $numbers ) ;

# Display the value of the first array element.
echo "<p>Start : {$matrix['Letter'][0]} </p>" ;

# Display the value of every element.
foreach( $matrix as $array => $list )
{ 
  echo '<ul>' ;
  foreach( $list as $key => $value ) { echo "<li>$array [ $key ] = $value " ; }
  echo '</ul>' ;
} 

?>
</body>
</html>