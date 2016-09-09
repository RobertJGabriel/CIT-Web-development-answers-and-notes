<?php date_default_timezone_set( 'UTC' ) ; 
$p = $_REQUEST['page'] ; $d = date( 'H:i:s' ) ;
$t = $_SERVER['REQUEST_METHOD'] ;
echo json_encode( 
array( "page"=> $p,"date"=> $d , "type"=> $t ) ) ; ?>