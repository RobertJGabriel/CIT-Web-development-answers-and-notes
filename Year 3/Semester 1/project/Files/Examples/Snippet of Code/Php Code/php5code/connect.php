<!-- example for PHP 5.0.0 final release -->

<?php

$domain = "localhost";
$user = "mike";	#note "MIKE" is unacceptable
$password = "bingo";

$conn = mysql_connect( $domain, $user, $password );

if($conn)
{
 $msg = "Congratulations $user, You connected to MySQL"; 
} 

?>

<html>

 <head>
  <title>Connecting user</title>
 </head>

 <body>
  <h3> 
   <?php echo( $msg ); ?> 
  </h3>
 </body>

</html>