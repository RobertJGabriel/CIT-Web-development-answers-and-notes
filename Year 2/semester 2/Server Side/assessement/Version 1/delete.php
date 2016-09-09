<?php
include 'congfig.php';
$con=mysqli_connect($host,$username,$password,$database);

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

mysqli_query($con,"DELETE FROM titles WHERE ID='$_POST[id]'");
			include 'lastused.php';

writeCookie();
mysqli_close($con);
header("Location: {$_SERVER['HTTP_REFERER']}");
	
?>




