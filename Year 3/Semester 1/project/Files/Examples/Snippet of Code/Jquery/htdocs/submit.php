<?php

// jQuery in easy steps, Selecting forms [p58].
// Server-side action for submit.html example.
// [unlisted].

$name = $_POST['name'] ;
$city = $_POST['city'] ;
echo "<title>Web Server Response</title>" ;
echo "<h1>Hello $name!</h1>" ;
echo "<p>How are things in $city?</p></html>" ;

?>