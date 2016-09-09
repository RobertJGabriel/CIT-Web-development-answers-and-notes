<?php
	$auth = $_COOKIE['auth'];

	header("Cache-Control:no-cache"); 

	if( ! $auth == "ok" ) 
	{
  	  header("Location:login.php" );
  	  exit();
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Logged In</title>
 </head>

 <body>

 You are logged in and can access all pages on this web site.
 <a href="anotherpage.php"><br>Visit another page on this site ?</a>

 </body>

</html>