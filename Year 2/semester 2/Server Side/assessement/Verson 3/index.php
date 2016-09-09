<html>

<head>
    <title>
    </title>
    <link rel="stylesheet" type="text/css" href="Css/mystyle.css">

</head>

<body>
<div id="header"></div>
<div id="box">
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
echo '</ul>';
?>


</div>

<div id="right">
<?php
include 'Php_code/display.php';
?>
</div>
</div>
</body>

</html>