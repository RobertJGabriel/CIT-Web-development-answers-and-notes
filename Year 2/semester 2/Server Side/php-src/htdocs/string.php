<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Strings</title>
</head>
<body>
<?php

# Create and initialize two variables.
$phrase = 'The truth is rarely pure' ;
$author = 'Oscar Wilde' ;

# Display the first variable value.
echo $phrase ;

# Display the variable value in a mixed string.
echo "<p>It is often said that <q>$phrase</q> </p>" ;

# Concatenate two strings.
$phrase = $phrase . ' and never simple' ;

# Display a concatenated string.
echo "<p> <q>$phrase</q> <cite>$author</cite> </p>" ;

?>
</body>
</html>