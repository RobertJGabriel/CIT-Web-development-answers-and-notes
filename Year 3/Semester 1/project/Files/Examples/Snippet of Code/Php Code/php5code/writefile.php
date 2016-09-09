<!-- example for PHP 5.0.0 final release -->

<?php	
	# for Linux use...
	# $filename = "/home/mike/Desktop/newfile.txt";

	# for Windows use...
	$filename = "C:\\Documents and Settings\\All Users\\Desktop\\newfile.txt";
	
	$file = fopen( $filename, "w" );
	fwrite( $file, "Samuel Pepys 1633-1703

	");
	fclose( $file );
?>

<html>

 <head>
  <title>Writing a new file</title>
 </head>

 <body>

 <?php
	
	if( file_exists( $filename ) )
	{   
	  $file_length = filesize( $filename );
	  $msg ="File created at $filename ";
	  $msg.="containing $file_length bytes";
	  echo( $msg );
 	}
	else { echo( "Unable to create file" ); }
 ?>

 </body>

</html>



