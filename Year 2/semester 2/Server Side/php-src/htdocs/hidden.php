<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Hidden Form Data</title>
</head>
<body>

<?php

date_default_timezone_set('UTC') ;
$time = date( ' H:i , F j ' ) ;
$user = 'Mike' ;

echo '
<form action="hidden_handler.php" method="POST">
<fieldset>
<legend>Send us your comments</legend>
<textarea rows="5" cols="20" name="comment"></textarea>
<input type="hidden" name="user" value=" '. $user .' ">
<input type="hidden" name="time" value=" '. $time.' ">
</fieldset>
<p><input type="submit" ></p>
</form> ' ;

?>

</body>
</html>