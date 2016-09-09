<! DOCTYPE html>

<html>
<head>
<title></title>
</head>
<body>

<?php
$WorldVar = "World";
$SunVar = "Sun";
$MoonVar = "Moon";

//define constants

define("WORLD_INFO",92897000);
define("SUN_INFO",70000000);
define("MOON_INFO",3456);

echo "<p> Heloo ". $WorldVar ."!<br/>";
echo "The ".$WorldVar."  is ". WORLD_INFO. " miles from the ". $SunVar."<br>";


echo "<p> Heloo ". $SunVar  ."!<br/>";
echo "The ".$SunVar ."  cores temperature is approximately ". SUN_INFO. "degrees fahrenheit<br/>";

echo "<p> Heloo ". $MoonVar  ."!<br/>";
echo "The ".$MoonVar." is ". SUN_INFO. "miles in diamator<br/>";


?>
</body>
</html>