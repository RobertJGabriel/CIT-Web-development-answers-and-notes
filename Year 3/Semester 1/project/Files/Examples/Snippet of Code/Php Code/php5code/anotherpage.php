<?php
	$auth = $_COOKIE['auth'];

	header( "Cache-Control:no-cache" ); 

	if( ! $auth == "ok" ) 
	{
	  header("Location:login.php" );
	  exit();
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Still Logged In</title>
 </head>

 <body>
  You are still logged in ...
 </body>

</html>