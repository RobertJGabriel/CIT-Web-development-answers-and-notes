<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Constant</title>
</head>
<body>
<?php

# Create and initialize an array variable.
define( 'USER' , 'Mike' ) ;

# Display the constant value.
echo '<p>Hello ' . USER . '</p>' ;

# Display the PHP version.
echo '<p>You are using PHP version ' . PHP_VERSION ;

# Display the host operating system.
echo ' running on ' . PHP_OS . '</p>' ;

?>
</body>
</html>