<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Variables</title>
</head>
<body>
<?php

# Create and initialize a variable.
$body_temp = 98.6 ;

# Display the variable value.
echo $body_temp ;

# Display the variable value in a mixed string.
echo "<p>Body temperature is $body_temp degrees Fahrenheit " ;

# Assign a new value to the variable.
$body_temp = 37.0 ;

# Display the new variable value in a mixed string.
echo "( $body_temp degrees Celsius )</p>" ;

?>
</body>
</html>