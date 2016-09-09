<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Form Action Handler</title>
</head>
<body>

<?php

# Replicate the HTML name attributes.
$name = $_POST['name'] ;
$mail = $_POST['mail'] ;
$comment = $_POST['comment'] ;

# Display the submitted data.
echo "<p>Thanks for this comment $name ...</p>" ;
echo "<p><i>$comment</i></p>" ;
echo "<p>We will reply to $mail</p>" ; 

?>

</body>
</html>