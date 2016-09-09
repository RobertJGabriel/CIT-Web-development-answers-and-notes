<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Hidden Data Handler</title>
</head>
<body>

<?php

# Empty check.
if ( !empty ( $_POST['comment'] ) )
{
  $comment = $_POST['comment'] ; 
}
else
{ 
  $comment = NULL ;
  echo 'You must enter a comment' ;
 }

 # Set checks.
 $time = ( !isset ( $_POST['time'] ) ) ? NULL : $_POST['time']  ;
 $user = ( !isset ( $_POST['user'] ) ) ? NULL : $_POST['user']  ;
  
 # Success response.
 if ( ( $comment != NULL )  && ( $time != NULL ) && ( $user != NULL )  ) 
 { 
   echo "<p>Comment received : \" $comment \"<br>From $user at $time </p>" ; 
 }
 

?>

</body>
</html>