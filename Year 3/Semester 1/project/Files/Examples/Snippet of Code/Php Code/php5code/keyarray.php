<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Key-value array elements</title>
 </head>
 <body>
  <ol>

  <?php
	$arr = array( 'version' => 10, 'OS'=> "Linux", 'os' => " Mandrake ");
	
	echo("Platform: ". $arr['OS']. $arr['os']. $arr['version']);
  ?>

  </ol>
 </body>
</html>