<!-- example for PHP 5.0.0 final release -->

<?php	

	# for Linux use...
	# $filename = "/home/mike/Desktop/newfile.txt";

	# for Windows use...
	$filename = "C:\\Documents and Settings\\All Users\\Desktop\\newfile.txt";

	$file = fopen( $filename, "a" );
	$string = "I went out to Charing Cross, to see Major-general Harrison hanged, drawn, and quartered, which was done there, he looked as cheerful as any man could do in that condition 
\t\t\tOctober 13,1660";

	fwrite( $file, $string );
	fclose( $file );
?>

<html>

 <head>
  <title>Appending to a file</title>
 </head>

 <body>

 <?php
	$file_length = filesize( $filename );
	echo( "$filename is now $file_length bytes" );
 ?>

 </body>

</html>



