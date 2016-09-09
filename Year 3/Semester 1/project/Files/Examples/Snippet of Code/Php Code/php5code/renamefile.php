<!-- example for PHP 5.0.0 final release -->

<?php 
	# for Linux use...
	# $oldname = "/home/mike/Desktop/error_bak";
        # $newname = "/home/mike/Desktop/errlog_bak";

	# for Windows use...
	$oldname = "C:\\Documents and Settings\\All Users\\Desktop\\error.bak";
	$newname = "C:\\Documents and Settings\\All Users\\Desktop\\errlog.bak";

	if( rename( $oldname, $newname ) )
	{
	  $msg = "Renamed $oldname<br> as $newname";
	}
	else
	{
	  $msg = "Unable to rename $oldname";
	}
?>

<html>

 <head>
  <title>Renaming files</title>
 </head>

 <body>
  <?php echo( $msg ); ?>
 </body>

</html>