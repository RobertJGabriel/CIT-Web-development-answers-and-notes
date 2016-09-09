<html>
<head>
<title>

</title>

</head>
<body>
<?php

function displayRequired($fieldName) {
 echo "The field \"$fieldName\" is required.<br />n";
}
function validateInput($data, $fieldName) {
 global $errorCount;
 if (empty($data)) {
 displayRequired($fieldName);
 ++$errorCount;
 $retval = "";
 } else { // Only clean up the input if it isn't 
 // empty
 $retval = trim($data);
 $retval = stripslashes($retval);
 }
 return($retval);
}
function redisplayForm($firstName, $lastName) {
?>
<h2 style = "text-align:center">Scholarship Form</h2>
<form name="scholarship" action="process_
Scholarship.php"
 method="post">
 <p>First Name: <input type="text" name="fName" 
value="<?php echo $firstName; ?>" /></p>
<p>Last Name: <input type="text" name="lName" 
value="<?php echo $lastName; ?>" /></p>
<p><input type="reset" value="Clear Form" />&nbsp; 
&nbsp;<input type="submit" name="Submit" value="Send 
Form" />
</form>
<?php
}



$errorCount = 0;
$firstName = validateInput($_POST['fName'], 
"First name");
$lastName = validateInput($_POST['lName'], 
"Last name");
if ($errorCount>0){
 echo "Please use the \"Back\" button to 
 re-enter the data.<br />\n";
}else{
 echo "Thank you for ? lling out the scholarship 
 form, " . $firstName . " " . $lastName . ".";
}
?>

</body>

</html>