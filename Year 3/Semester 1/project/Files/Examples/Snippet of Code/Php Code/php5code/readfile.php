<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Reading a file</title>
 </head>

 <body>
 
 <?php 
	$filename = "quote.txt";
	$filesize = filesize($filename);
	$file = fopen( $filename, "r" );
	$text = fread( $file, $filesize );
	fclose( $file ); 

	echo( "File Size: $filesize bytes" ); 
	echo( "<pre>$text</pre>" );
 ?>

 </body>

</html>