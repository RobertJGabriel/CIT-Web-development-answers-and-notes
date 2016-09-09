<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Greetings</title>
 </head>
 <body>

 <?php
	$hour = date( "G" );

	$now = date( "g:i a" );

	$msg = "Good Evening.";
	if( $hour < 18 ) { $msg = "Good Afternoon."; }
	if( $hour < 12 ) { $msg = "Good Morning."; }

	echo( "$msg The time is $now" );
 ?>

 </body>
</html>