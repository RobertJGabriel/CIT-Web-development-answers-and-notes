<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Logic</title>
</head>
<body>
<?php

# Create and initialize variables.
$yes = TRUE ;
$no = FALSE ;

# Display the result of AND operations.
$result = ( $no && $no ) ? 'TRUE' : 'FALSE' ; echo "No AND No returns $result <br>" ;
$result = ( $yes && $no ) ? 'TRUE' : 'FALSE' ; echo "Yes AND No returns $result <br>" ;
$result = ( $yes && $yes ) ? 'TRUE' : 'FALSE' ; echo "Yes AND Yes returns $result <hr>" ;

# Display the result of OR operations
$result = ( $no || $no ) ? 'TRUE' : 'FALSE' ; echo "No OR No returns $result <br>" ;
$result = ( $yes || $no ) ? 'TRUE' : 'FALSE' ; echo "Yes OR No returns $result <br>" ;
$result = ( $yes || $yes ) ? 'TRUE' : 'FALSE' ; echo "Yes OR Yes returns $result <hr>" ;

# Display the result of NOT operation.
$result = ( ! $yes ) ? 'TRUE' : 'FALSE' ; echo "NOT Yes returns $result <br>" ;

?>
</body>
</html>