<?php

require ( '../connect_db.php' ) ;

$q = 'SELECT price FROM watches WHERE model = "Boston" ' ;
$r =  mysqli_query( $dbc , $q )  ;
while ($row = mysqli_fetch_array( $r , MYSQLI_NUM ) ) { $var = $row[0] ; }

$result= is_numeric( $var ) ? 'numeric' : 'not numeric';
echo "$var is $result<br>" ;

$result= is_int( $var ) ? 'an integer' : 'not an integer';
echo "$var is $result<br>" ;

$result= is_float( $var ) ? 'a float' : 'not a float';
echo "$var is $result<br>" ;

$result= is_string( $var ) ? 'a string' : 'not a string';
echo "$var is $result<br>" ;

$result= is_null( $var ) ? 'NULL' : 'not NULL';
echo "$var is $result<br>" ;

$result= is_scalar( $var ) ? 'a variable' : 'not a variable';
echo "$var is $result<br>" ;

$result= is_array( $var ) ? 'an array' : 'not an array';
echo "$var is $result<br>" ;

$result= is_bool( $var ) ? 'a boolean' : 'not a boolean';
echo "$var is $result<br>" ;

$result= is_resource( $var) ? 'a resource' : 'not a resource';
echo "$var is $result<br>" ;

mysqli_close( $dbc ) ;