<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>
</head>
<body>

<?php



$Value1 = "ﬁ rst text string";
$Value2 = "second text string";
$ReturnValue = ($Value1 == $Value2 ? "true" : 
"false");
echo '<p>$Value1 equal to $Value2: ', $ReturnValue, 
"<br />";
$Value1 = 50;
$Value2 = 75;
$ReturnValue = ($Value1 == $Value2 ? "true" : 
"false");
echo '$Value1 equal to $Value2: ', $ReturnValue, 
"<br />";
$ReturnValue = ($Value1 != $Value2 ? "true" : 
"false");
echo '$Value1 not equal to $Value2: ', $ReturnValue, 
"<br />";
$ReturnValue = ($Value1 <> $Value2 ? "true" : 
"false");
echo '$Value1 not equal to $Value2: ', $ReturnValue, 
"<br />";
$ReturnValue = ($Value1 > $Value2 ? "true" : 
"false");
53
Building Expressions