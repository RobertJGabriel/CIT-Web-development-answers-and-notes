<!-- example for PHP 5.0.0 final release -->

<?php 	
	function delete( $file )
	{
	  if( unlink( $file ) )
	  { 
	    echo( "$file<br>has been deleted<hr>" ); 
	  }
	  else
	  { 
	    echo( "Unable to delete $file<hr>" ); 
	  }
	}
?>

<html>

 <head>
  <title>Deleting files</title>
 <head>

 <body>

 <?php 
	# for Linux use...
	#$file_A = "/home/mike/Desktop/errlog_bak";
	#$file_B = "/home/mike/Desktop/errlog_not";

	# for Windows use...
	$file_A = "C:\\Documents and Settings\\All Users\\Desktop\\errlog.bak";
	$file_B = "C:\\Documents and Settings\\All Users\\Desktop\\errlog.not";

	delete($file_A);
	@delete($file_B);
  ?>

 </body>

</html>
