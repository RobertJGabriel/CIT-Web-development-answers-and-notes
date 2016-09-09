<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Get Browser &amp; Platform</title>
 </head>
 <body>

 <?php
	$viewer = getenv( "HTTP_USER_AGENT" );
	echo( "Browser details:<br>$viewer" );
 ?>

 </body>
</html>