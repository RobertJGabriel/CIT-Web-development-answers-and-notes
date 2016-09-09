<!-- example for PHP 5.0.0 final release -->

<?php

$username = $_POST['username'];
$useraddr = $_POST['useraddr'];
$comments = $_POST['comments'];

$to = "php5ineasysteps@hotmail.com";				#recipient
$re = "Website Feedback"; 					#subject
$msg = $comments;						#message

$headers  = "MIME-Version: 1.0\r\n";				#html hdr
$headers .= "Content-type: text/html; charset=iso-8859-1\r\n";
$headers .= "From: $useraddr \r\n";				#from
$headers .= "Cc: another@hotmail.com \r\n"; 			#cc

mail( $to, $re, $msg, $headers );				#send mail

?>

<html>

 <head>
  <title>Message Received</title>
 </head>

 <body>
 <h3>Thanks for your comments</h3>
 Message received from <?php echo($username); ?><br>
 Reply to <?php echo($useraddr); ?>
 </body>

</html>




