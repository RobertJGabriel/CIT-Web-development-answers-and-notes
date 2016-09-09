<?php

    function writeCookie()
    {
        // Expires in ten years :)
        $expire= time() + (10 * 365 * 24 * 60 * 60);

        //Storing user and time
        $user = "Sam Carter";
        //Gets time and stores it 
        $dt = new DateTime();
        $time = $dt->format('H:i:s');

        //setting cookie for time and user
        setcookie("user", $user, $expire);
        setcookie("time", $time, $expire);
    }

    function readCookie()
    {
        // reads if cookie is there 
        if (isset($_COOKIE["user"])){
            echo "Lasted Edited by : <br>" . $_COOKIE["user"] . "!<br><br>";
            echo "Last edited on " . $_COOKIE["time"] . "<br>";
        }
        else{
            //Displays a message if the cookie isnt set
            echo "Welcome Guest!<br>";
            writeCookie();
        }
    }
?>