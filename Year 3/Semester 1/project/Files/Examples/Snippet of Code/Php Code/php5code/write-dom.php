<!-- example for PHP 5.0.0 final release -->

<?php

#load an XML document into the DOM
$dom = new DomDocument();
$dom -> load("books.xml");

#create elements
$title =  $dom -> createElement("title");
$topic =  $dom -> createElement("topic");
$series = $dom -> createElement("series");
$pic =    $dom -> createElement("pic");

#create text nodes
$topictext =  $dom -> createTextNode("Linux");
$seriestext = $dom -> createTextNode("in easy steps");
$pictext =    $dom -> createTextNode("linux.gif");

#append the text nodes to the inner nested elements
$topic  -> appendChild($topictext);
$series -> appendChild($seriestext);
$pic    -> appendChild($pictext);

#append the inner nested elements to the <title> element
$title -> appendChild($topic);
$title -> appendChild($series);
$title -> appendChild($pic);

#append the <title> element to the root element
$dom -> documentElement -> appendChild($title);

#create a new enlarged xml document
$dom -> save("newbooks.xml");

?>