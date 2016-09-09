<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP While Loops</title>
</head>
<body>
<dl>
<?php

# Create an array.
$numbers = array( 10 , 20 , 30 ) ;

# Output each element value.
echo '<dt>While Loop :' ;
$i = 0 ;
while ( $i < 3 )
{
  echo "<dd>Element $i = $numbers[$i]";
  $i++ ;
}
echo '<dt>Do While Loop :' ;
$i = 0 ;
do
{
  echo "<dd>Element $i = $numbers[$i]";
  $i++ ;
}
while ( $i < 3 ) ;

?>
</dl>
</body>
</html>