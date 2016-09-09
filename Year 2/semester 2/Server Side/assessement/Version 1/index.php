
<html>
<head>
<title>
</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script src="javascript.js"></script>
</head>
<body onload="resize()">


<div id="left">
<div id="logo">

</div>
<?php
include 'lastused.php';

echo '<div id="catch">';

readCookie();

 echo '</div>';
include 'delete.html.php';


echo '<ul id="overflow">';
echo '<li>';
include 'insertHTML.php';
echo '</li>';
echo '<li>';
include 'insertHTML.php';
echo '</li>';

echo '</ul>';
?>

</div>

<div id="right">
<?php
include 'display.php';


?>
</div>



</body>
</html>