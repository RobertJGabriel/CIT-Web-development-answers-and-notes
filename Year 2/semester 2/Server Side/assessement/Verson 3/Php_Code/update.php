<?php

 include '../Config/config.php';
    $con=mysqli_connect($host,$username,$password,$database);


// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

mysqli_query($con,"UPDATE titles SET  releaseDate='$_POST[releaseDate]',filmDuration='$_POST[filmDuration]',director='$_POST[director]',description='$_POST[description]'
WHERE filmtitle='$_POST[filmtitle]'");



      
  header("Location: {$_SERVER['HTTP_REFERER']}");
	
?>