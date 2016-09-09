<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP For Loops</title>
</head>
<body>
<dl>
<?php

# Output loop counter values.
for ( $i = 1 ; $i < 4 ; $i++ )
{
  echo "<dt>Outer loop iteration $i" ;
  
  for ( $j = 1 ; $j < 4 ; $j++ )
  {
    echo "<dd>Inner loop iteration $j" ;
  }
  
}

?>
</dl>
</body>
</html>