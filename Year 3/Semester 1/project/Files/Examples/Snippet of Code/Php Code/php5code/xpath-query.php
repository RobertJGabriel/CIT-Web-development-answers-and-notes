<!-- example for PHP 5.0.0 final release -->

<html>

<head>
 <title>XPath DOM Query</title>
</head>

<body>


<?php

$dom = new DomDocument();	#load an XML document into the DOM
$dom -> load( "food.xml" );

$xp = new DomXPath( $dom );	#create an XPath object

#print a list of all fruit items
echo( "Fruit items are:<ul>" );
$fruits = $xp -> query( "/food/fruit/item" );
foreach($fruits as $node)
{
  echo( "<li>" . $node -> textContent . "</li>" );
}
echo( "</ul>" );

#uncomment this block to print a node by id value
#echo( "ID 2 is " );
#$fruits = $xp -> query( "/food/fruit/item[@id='2']" );
#foreach( $fruits as $node ){ echo( $node -> textContent ); }

#uncomment this block to print a node by position
#echo( "<br>Position 1 is " );
#$fruits = $xp -> query( "/food/fruit/item[position() = 1]" );
#foreach( $fruits as $node ){ echo( $node -> textContent ); }

#uncomment this block to print a list of all veg items
#echo( "<br>Veg items are:<ul>" );
#$veg = $xp -> query( "/food/veg//item" );
#foreach( $veg as $node )
#{ echo( "<li>" . $node -> textContent . "</li>" ); }
#echo( "</ul>" );

?>

</body>
</html>