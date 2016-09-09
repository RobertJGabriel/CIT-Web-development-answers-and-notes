<!-- example for PHP 5.0.0 final release -->

<html><head><title>SimpleXML</title></head><body>

<?php

#create a table of books data
echo( "<table width='375px' cellpadding='5px' border='1px' cellspacing='0px'>" );

#load an xml document into a SimpleXML object
$sx = simplexml_load_file( "books.xml" );
foreach( $sx -> title as $title )
{  
  echo( "<tr>" );
  echo( "<td align='right'>" );
  echo( "<img src='" . $title -> pic . "'>" );
  echo( "</td>" );
  echo( "<td valign='top'>" );
  echo( "<b>"  . $title -> topic . "</b>" ); 
  echo( "<br>" . $title -> series );
  echo( "</td>" );
  echo( "</tr>" );
}
echo( "</table>" );

?>
 
</body></html>