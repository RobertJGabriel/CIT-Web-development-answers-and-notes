<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Functions</title>
</head>
<body>
<?php

# Create a global variable.
$result = 5 + 5 ;

function square()
{
 $result = 5 * 5 ;
 echo "Local Square Result = $result<br>" ;
}

function cube()
{
 $result = 5 * 5 * 5 ;
 echo "Local Cube Result = $result<br>" ;
}

echo "Global Sum Result = $result<br>" ;
square();
cube();

?>
</body>
</html>