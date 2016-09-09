<!-- example for PHP 5.0.0 final release -->

<?php

$address = $_SERVER['REMOTE_ADDR'];
$referer = $_SERVER['HTTP_REFERER'];
$browser = $_SERVER['HTTP_USER_AGENT'];

#open the log file
$file = fopen("log.html",  "a");  

#write the time of access
$time = date("H:i dS F");
fwrite( $file, "<b>Time:</b> $time<br>" );

#write the user's IP address if available
if( $address != null)
{
  fwrite( $file, "<b>IP Address:</b> $address <br>");
}  

#write the URL of the forwarding page if available
if( $referer != null)
{
  fwrite( $file, "<b>Referer:</b> $referer<br>");  
}

#write the user's browser details
fwrite( $file, "<b>Browser:</b> $browser<br/><hr>");  

#close the log file
fclose($file);

?> 
