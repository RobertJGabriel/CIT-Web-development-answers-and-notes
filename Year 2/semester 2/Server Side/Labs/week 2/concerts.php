<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>
</head>
<body>
<?php
<h1>Central Valley Civic Center</h1>
<h2>Summer Concert Season</h2>

$Concerts = array("Jimmy Buffett", "Chris Isaak", 
"Bonnie Raitt", "James Taylor", "Alicia Keys");

$Concerts[] = "Bob Dylan";
$Concerts[] = "Ryan Cabrera";
$Concerts[2] = "Joe";
$Concerts[3] = "Van";

echo "<p>The following ", count($Concerts), " concerts are scheduled:</p><p>";
echo "$Concerts[0]<br />";
echo "$Concerts[1]<br />";
echo "$Concerts[2]<br />";
echo "$Concerts[3]<br />";
echo "$Concerts[4]<br />";
echo "$Concerts[5]<br />";

?>

</body>
</html>