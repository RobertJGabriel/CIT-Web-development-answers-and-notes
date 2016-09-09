<?php 
	$user =  $_POST['user'];
	$color = $_POST['color'];
	$self =  $_SERVER['PHP_SELF'];

	if( ( $user != null ) and ( $color != null ) )
	{
	  setcookie( "firstname", $user , time() + 36000 );
	  setcookie( "fontcolor", $color, time() + 36000 );
	  header( "Location:getcookie.php" );
	  exit();
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Set Cookie Data</title>
 </head>

 <body>

  <form action ="<?php echo( $self ); ?>" method = "post">

  Please enter your first name:
  <input type = "text" name = "user"><br><br>

  Please choose your favorite font color:<br>
  <input type = "radio" name = "color" value = "#FF0000">Red
  <input type = "radio" name = "color" value = "#00FF00">Green
  <input type = "radio" name = "color" value = "#0000FF">Blue
  <br><br>
  <input type = "submit" value = "submit">
  </form>

 </body>

</html>