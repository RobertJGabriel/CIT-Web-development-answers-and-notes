<html>

<head>
    <title>
    </title>
    <link rel="stylesheet" type="text/css" href="Css/mystyle.css">
    <script src="Javascript/javascript.js"></script>
</head>

<body onload="resize()">


    <div id="left">
        <div id="logo">

        </div>


<?php
include 'Php_Code/lastused.php';

echo '<div id="catch">';

readCookie();

 echo '</div>';
include 'delete.html.php';


echo '<ul id="overflow">';
echo '<li>';
include 'insertHTML.php';
echo '</li>';
echo '<li>';
include 'update.html';
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