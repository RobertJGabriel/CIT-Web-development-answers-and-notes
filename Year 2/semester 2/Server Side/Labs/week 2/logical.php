<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>
</head>
<body>


<?php
$TrueValue = true;
$FalseValue = false;
$ReturnValue = ($TrueValue ? "true" : "false");
echo "<p>$ReturnValue<br />";
$ReturnValue = ($FalseValue ? "true" : "false");
echo "$ReturnValue<br />";
$ReturnValue = ($TrueValue || $FalseValue ? "true" : 
"false");
echo "$ReturnValue<br />";
$ReturnValue = ($TrueValue && $FalseValue ? "true" : 
"false");
echo "$ReturnValue<br />";
echo "</p>";

?>
</body>
</html>