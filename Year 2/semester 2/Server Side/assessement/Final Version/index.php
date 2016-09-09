<?php
	echo '<html>';
    echo '<head>';
    echo '<title>';
    echo '</title>';
    echo '<link rel="stylesheet" type="text/css" href="Css/mystyle.css">';
    echo '</head>';
    echo '<body>';
	echo '<div id="header">';
	echo '</div>';
    echo '<div id="box">';
    echo '<div id="left">';

    include_once'Php/config.php';
    include_once 'Php/contollor.php';


    include_once 'Php/delete.php';
    include_once 'Php/insert.php';
    echo '<div id="catch">';
    readCookie();
    echo '</div>';
    echo '</div>';

    echo '<div id="right">';
    echo "<h2>Dvd Menu</h2>";
    include_once 'Php/display.php';
    echo '</div>';
    echo '</div>';
    echo '</body>';
    echo '</html>';
?>