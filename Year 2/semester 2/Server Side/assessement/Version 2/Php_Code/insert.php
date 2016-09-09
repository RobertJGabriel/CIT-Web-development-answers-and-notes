<?php

    include 'Config/config.php';
    $con=mysqli_connect($host,$username,$password,$database);

    $db_handle = mysqli_connect($host,$username,$password,$database);
    $db_found = mysql_select_db($database, $db_handle);

    if ($db_found) {
        $result =mysql_query("SELECT 1 FROM my_table WHERE `Username` = '$username'");
           
        if ($result && mysql_num_rows($result) > 0)
        {
            echo 'Username and Password Found'; 
        }
        else {
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

        }
    }
    else{
        print "Database NOT Found.";
        mysql_close($db_handle);
    }
    header("Location: {$_SERVER['HTTP_REFERER']}");
	
?>