<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Security</title>
</head>
<body>

<form action="secure.php" method="POST">
<p>New Name : <input type="text" name="name"><input type="submit"></p>
</form>


<?php

# Connect to MySQL.
require ( '../connect_db.php' ) ;

# Validate field is not empty and not numeric.
if ( !empty( $_POST['name'] )  && !is_numeric( $_POST[ 'name'] ) )
{
  $name = $_POST['name']  ;
  
  # Make string safe for queries by allowing quotes.
  $name = mysqli_real_escape_string( $dbc , $name ) ;
  
  # Disallow HTML tags.
 $name = strip_tags( $name ) ;
  
  # Execute update.
  $q = 'UPDATE towels SET name = "' . $name . '" WHERE id = 1' ;
  mysqli_query( $dbc , $q )  ;
   }
 else { echo 'No valid new name submitted' ; }

 $q = 'SELECT * FROM towels WHERE id = 1 ' ;
 $r =  mysqli_query( $dbc , $q )  ;
 while ($row = mysqli_fetch_array( $r , MYSQLI_NUM) ) 
{ echo  "<p>Name : $row[1] </p>" ; }
mysqli_close( $dbc ) ;

?>

</body>
</html>