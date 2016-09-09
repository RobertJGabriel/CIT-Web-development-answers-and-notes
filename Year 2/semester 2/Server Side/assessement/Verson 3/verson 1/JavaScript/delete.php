<?php

//Includes the connection information
include 'Config/config.php';

$con=mysqli_connect($host,$username,$password,$database);

if ($con)
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
else{

    //Selects all the titles from where the id is selected.
    mysqli_query($con,"DELETE FROM titles WHERE ID='$_POST[id]'");
    
    //Includes the lastused file.
    include 'lastused.php';

    //Writes Cookie to show who edited last.
    writeCookie();

    //Closes SQL Connection
    mysqli_close($con);
}
    //Brings them to the preveices page.
    header("Location: {$_SERVER['HTTP_REFERER']}");
	
?>




