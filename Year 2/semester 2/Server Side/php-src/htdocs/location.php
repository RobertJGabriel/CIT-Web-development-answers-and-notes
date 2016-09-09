<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Location Header</title>
</head>
<body>

<?php

session_start() ;

if ( isset( $_SESSION['id']  ) )
{
  $id = $_SESSION['id'] ;
 echo "Welcome user ID #$id" ;
 }


?>

</body>
</html>