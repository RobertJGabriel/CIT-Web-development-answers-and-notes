<?php

 include '../Config/config.php';
    $con=mysqli_connect($host,$username,$password,$database);

  
            if (mysqli_connect_errno())
            {
                echo "Failed to connect to MySQL: " . mysqli_connect_error();
            }

            $sql="INSERT INTO titles (cert, filmtitle,releaseDate,filmDuration,director,description)
VALUES
('$_POST[cert]','$_POST[filmtitle]','$_POST[releaseDate]','$_POST[filmDuration]','$_POST[director]','$_POST[description]')";

			if (!mysqli_query($con,$sql))
				{
				    die('Error: ' . mysqli_error($con));
				}
                else{
                    include 'lastused.php';
                    writeCookie();
				}
            mysqli_close($con);

      
    header("Location: {$_SERVER['HTTP_REFERER']}");
	
?>