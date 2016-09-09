<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Arrays</title>
</head>
<body>
<?php

# Create and initialize an array variable.
$days = array( 'Monday' , 'Tuesday' , 'Wednesday' ) ;

# Display the value in all elements as a bulleted list.
foreach( $days as $value ) { echo "&bull; $value " ; } 

# Create and initialize an array variable with keys.
$months = array( 'jan' => 'January' , 'feb' => 'February' , 'mar' => 'March' ) ;

# Display the key and value of each element.
echo '<dl>' ;
foreach( $months as $key => $value ) { echo "<dt>$key<dd>$value" ; } 
echo '</dl>' ;

?>
</body>
</html>