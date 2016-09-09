<html>
<head>
<title></title>
</head>
<body>

<?php
$id = 0 ;
if(isset ($_GET['id']))
{
$id = $_GET['id'];
switch($id)
{
case 1: echo 'Cow Selected';break;
case 2: echo 'Dog Selected';break;
case 3: echo 'Goat Selected';break;
}
}
echo '<h1> Select a buddy</h1>';
echo '<p> <a href="HyperLink.php?id=1">Cow</a>|';
echo '<a href="HyperLink.php?id=2">Dog</a>|';
echo '<a href="HyperLink.php?id=3">Cat</a>|</p>';
?>



</body>
</html>