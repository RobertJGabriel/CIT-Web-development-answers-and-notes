<html>
<head>
<title></title>
</head>
<body>
<?php

$FaceNamesSingular = array("one", "two", "three", 
"four", "? ve", "six");
$FaceNamesPlural = array("ones", "twos", "threes", 
"fours", "? ves", "sixes");


function CheckForDoubles($Die1, $Die2) {
 global $FaceNamesSingular;
 global $FaceNamesPlural;
 
 $ReturnValue =false;
 if ($Die1 == $Die2) {// Doubles
 echo "The roll was double ", $FaceNamesPlural[$Die1-1], ".<br />";
  $ReturnValue = true;
 }else{
 echo "The roll was a ", $FaceNamesSingular[$Die1-1]," and a ", $FaceNamesSingular[$Die2-1],
 ".<br />";
 $ReturnValue = false;
 }
 return $ReturnValue;
}



function DisplayScoreText($Score, $Doubles) {
switch ($Score) {
 case 2:
 echo "You rolled snake eyes!<br />";
 break;
 case 3:
 echo "You rolled a loose deuce!<br />";
 break;
 case 5:
 echo "You rolled a fever ? ve!<br />";
 break;
 case 7:
 echo "You rolled a natural!<br />";
 break;
 case 9:
 echo "You rolled a nina!<br />";
 break;
 case 11:
 echo "You rolled a yo!<br />";
 break;
 case 12:
 echo "You rolled boxcars!<br />";
 break;
 default:
 if ($Score % 2 == 0) { 
 if ($Doubles) {
 echo "You rolled a hard 
 $Score!<br />";
 }
 else { /* Not doubles */
 echo "You rolled an easy 
 $Score!<br />";
 }
 }
 break;
}



}



$Dice = array();

$DoublesCount =0;
$RollNumber = 1;
while ($RollNumber  <=5) {
$Dice[0] = rand(1,6);
$Dice[1] = rand(1,6);
$Score = $Dice[0] + $Dice[1];
echo "<p>";

 echo "The total score for roll $RollNumber was 
 $Score.<br />";
 $Doubles = CheckForDoubles($Dice[0],$Dice[1]);
 DisplayScoreText($Score, $Doubles);
 echo "</p>";
 if ($Doubles)
 ++$DoublesCount;
 ++$RollNumber;
} // End of the while loop

 echo "<p> Doubles occurred on the $DoublesCount of the five rolls</p>";


?>

</body>
</html>