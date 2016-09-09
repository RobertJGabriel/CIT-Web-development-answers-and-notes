<?php	session_start();

	if($_POST['font'] != null)
	{	
	  $_SESSION['font'] = $_POST['font'];
	  header( "Location:prefs2.php?" . SID );
	  exit();
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Session preferences</title>
 </head>

 <body>

  <h3>Select Your Preferred Font Family</h3>

  <form action = "<?php echo( $_SERVER['PHP_SELF'] ); ?>" method = "post">
   <input type = "radio" name = "font" value = "serif">Serif
   <input type = "radio" name = "font" value = "sans-serif">Sans
   <input type = "radio" name = "font" value = "monospace">Mono
   <input type = "radio" name = "font" value = "cursive">Cursive
   <input type = "radio" name = "font" value = "fantasy">Fantasy
   <br><br>
   <input type="submit" value="Submit">
  </form>

 </body>

</html>