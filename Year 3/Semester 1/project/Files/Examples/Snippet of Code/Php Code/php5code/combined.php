<!-- example for PHP 5.0.0 final release -->

<html>
<head><title>Combined Feedback Form</title></head>
<body>

<?php

$self = $_SERVER['PHP_SELF'];
$username = $_POST['username'];
$useraddr = $_POST['useraddr'];
$comments = $_POST['comments'];
$sent = $_POST['sent'];


#the HTML form that can be written dynamically
$form ="<form action=\"$self\" method=\"post\">";
$form.="Name:<input type=\"text\" name=\"username\"";
$form.=" size=\"30\" value=\"$username\" ><br/><br/>";
$form.="Email:<input type=\"text\" name=\"useraddr\"";
$form.=" size=\"30\" value=\"$useraddr\"><br/><br/>";
$form.="Comments:<textarea name=\"comments\" cols=\"30\" rows=\"5\">";
$form.="$comments</textarea><br/>";
$form.="<input type=\"submit\" name=\"sent\" value=\"Send Form\">";
$form.="</form>";

if($sent)
{
  #set variable default value
  $valid=true;

  #check username field is not blank
  if( !$username )
  { $errmsg.="Enter your name...<br />"; $valid = false; }

  #check email useraddr field is not blank
  if( !$useraddr )
  { $errmsg .="Enter your email address...<br />"; $valid = false; }

  #check comments field is not blank
  if( !$comments )
  { $errmsg.="Enter your comments...<br />"; $valid = false; }

  #check validity of email format
  $useraddr = trim($useraddr);
  #permitted patterns for name,domain and top-level domains
  $_name = "/^[-!#$%&\'*+\\.\/0-9=?A-Z^_`{|}~]+";
  $_host = "([-0-9A-Z]+\.)+";
  $_tlds = "([0-9A-Z]){2,4}$/i";
  if( !preg_match( $_name."@".$_host .$_tlds,$useraddr ) )
  { 
    $errmsg.="Email address has incorrect format!<br />";
    $valid=false;
  }
}

#if not valid write the error message/s and repeat the form
if($valid != true)
{
  echo( $errmsg.$form );
}
else
#if the form is valid send the email
{
  #recipient's email address
  $to = "php5ineasysteps@hotmail.com";

  #subject of the message
  $re = "Feedback from $username";

  #message from the feedback form
  $msg = $comments;

  #set the Content-type header for HTML mail
  $headers  = "MIME-Version: 1.0\r\n";
  $headers .= "Content-type: text/html;";   
  $headers .= "charset=\"iso-8859-1\"\r\n";

  #set the From header
  $headers .= "From: $useraddr \r\n";

  #send the email now...
  if(mail($to,$re,$msg, $headers))
  { echo("Your comments have been sent - thanks $username");}

}
?>

</body></html>