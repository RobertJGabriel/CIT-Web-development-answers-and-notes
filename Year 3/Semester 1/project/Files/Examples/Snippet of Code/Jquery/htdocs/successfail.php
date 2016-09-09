<?php 
$num = $_REQUEST['num'] ;
if( is_numeric($num) && ($num < 10) )
echo "Output: $num received" ;
else header('HTTP/1.1 500 Internal Server Error') ; 
?>