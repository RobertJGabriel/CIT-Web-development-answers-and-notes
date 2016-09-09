<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Read DOM</title>
 </head>

 <body>

 <?php

 #load an xml document into the DOM
 $dom = new DomDocument;
 $dom -> load( "books.xml" );

 #print out the root element name
 echo( "Root element name is: " ); 
 echo( $dom -> documentElement -> nodeName );
 echo( "<hr>" );

 #print a list of all topics
 echo( "Topics include:<ul>" );
 $topics = $dom -> getElementsByTagName( "topic" );
 foreach( $topics as $node )
 {
  echo "<li>" . $node -> textContent . "</li>";
 }
 echo( "</ul>" );
 ?>

 
 </body>

</html>