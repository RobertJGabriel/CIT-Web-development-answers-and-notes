<?php 
	$user = $_POST['user'];
	$pass = $_POST['pass'];
	$self = $_SERVER['PHP_SELF'];

	if( ( $user != null ) and ( $pass != null ) )
	{
	  setcookie( "auth","ok" );
	  header( "Location:loggedin.php" );
	  exit();
}

?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Set Cookie Data</title>
 </head>

 <body>

  <form action="<?php echo( $self ); ?>" method="post">

  Name: <input type="text" name="user" size="10">

  Password: <input type="text" name="pass" size="10"><br><br>

  <input type="submit" value="Log Me In">

  </form>

 </body>

</html>