<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Your submission</title>
 </head>
 <body>
 <img src="foodbnr.jpg" width="368" height="54">
 <br>

 <?php
	$username = $_POST['username'];
	$color =    $_POST['color'];
	$dish =     $_POST['dish'];

	if( $username != null )
	{
	  echo( "Thanks for your selection $username <hr>" );
	}

	if( ( $color != null ) && ( $dish != null ) )
	{
	  $msg = "You really enjoy $dish <br>";
	  $msg .= "- especially with a nice $color wine";
	  echo( $msg );
	}
 ?>

 </body>
</html>