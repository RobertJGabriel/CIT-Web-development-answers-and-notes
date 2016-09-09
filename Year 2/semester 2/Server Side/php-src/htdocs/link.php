<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Link Data</title>
</head>
<body>

<?php

# Handle the submitted link data.
if ( isset( $_GET['id'] ) ) 
{
  $id = $_GET['id'] ;
  
  switch( $id )
  {
    case 1 : echo 'Cow selected<hr>' ; break ;
    case 2 : echo 'Dog selected<hr>' ; break ;
    case 3 : echo 'Goat selected<hr>' ; break ;
  }
}

# Display hyperlinks with appended id values.
echo '<h1>Select a buddy</h1>' ;
echo ' <p><a href="link.php?id=1">Cow</a> |' ;
echo ' <a href="link.php?id=2">Dog</a> | ' ;
echo ' <a href="link.php?id=3">Goat</a></p>' ;

?>

</body>
</html>