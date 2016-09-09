<!-- example for PHP 5.0.0 final release -->

<?php 
		
	#for Linux use...
	#$source = "/usr/local/apache/logs/error_log";
	#$dest = "/home/mike/Desktop/error_bak";
	
	# for Windows use...
	$source = "C:\\Apache\\logs\\error.log";
	$dest = "C:\\Documents and Settings\All Users\Desktop\error.bak";

	if( copy( $source, $dest ) )
	{
	  $msg = "Copied $source<br>to $dest";
	}
	else
	{
	  $msg = "Unable to copy $source";
	}
?>

<html>

 <head>
  <title>Copying files</title>
 <head>

 <body>

  <?php echo($msg); ?>

 </body>

</html>
