<!-- example for PHP 5.0.0 final release -->

<?php 	

	# for Linux
	#$dirname = "/usr/local/apache/bin";

	# for Windows
	$dirname = "C:\\Apache\\bin";


	$dir = opendir( $dirname );

	
	while( false != ( $file = readdir( $dir ) ) )
	{
	  if( ( $file != "." ) and ( $file != ".." ) )
	  {
	    $file_list .= "<li>$file</li>";
	  }
	}

	closedir( $dir );
?>

<html>

 <head>
  <title>Listing directory</title>
 <head>

 <body>
  <p>Files in <?php echo( $dirname ); ?> </p>
  <ul>
    <?php echo( $file_list ); ?>
  </ul>
 </body>

</html>