<?php


function writeCookie()
{
// Expires in ten years :)
$expire= time() + (10 * 365 * 24 * 60 * 60);

//Storing user and time
$user = "Sam Carter";

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
  echo "Lasted Edited by : <br>" . $_COOKIE["user"] . "!<br>
  <br>";
echo "Last edited on " . $_COOKIE["time"] . "<br>";

}else{
  echo "Welcome guest!<br>";
  writeCookie();
}}
?>