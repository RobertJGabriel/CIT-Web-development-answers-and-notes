<html>
<head>
<title>Musical Scale </title>
</head>
<body>

<?php
$MusicalScale = array("do", "re", "mi", "fa", "so", 
"la", "ti");
$OutputString="The notes of the musical scale are: ";
foreach ($MusicalScale as $number => $CurrentNote)
 $OutputString .= "<br> Note " .$number . " " . $CurrentNote ;
 
 echo "<p>$OutputString</p>";
 
?>
</body>
</html>