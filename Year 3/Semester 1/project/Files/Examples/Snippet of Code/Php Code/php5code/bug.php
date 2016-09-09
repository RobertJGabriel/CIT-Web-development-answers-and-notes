<?php

  #Script to test for object serialization/deserialization bug in PHP 5 
  #Author: Mike McGrath
  #Date:  July 1-14, 2004

  #create 2 classes with nested instances
  class Red
  {
    public $blu;
  }

  class Blu
  { 
    public $red;
  }

  #identify the PHP version
  echo "PHP version is: " . phpversion() . "<hr><br>";

  #create a Red object
  $a = new Red;
  echo "<font color='red'>Red: </font>" . $a . "<br>";

  #create a Blue object as Red->blu
  $a->blu = new Blu;
  echo "<font color='red'>Red->blu: </font>" .$a->blu . "<hr>";

  #assign Red object to Red->blu->red
  $a->blu->red = $a;

  #initialize number value in Red object
  echo "Assigning value of 1 to Red->number creates: <br>";
  $a->number = 1;

  #display number values 
  echo $a . "<font color='red'> Red->number value: </font>" . $a->number . "<br>";
  echo $a . "<font color='red'> Red->blu->red->number value: </font>" . $a->blu->red->number . "<br>";
 
  #display serialized Red object components
  $series = serialize($a);
  echo $a . "<font color='red'> serialized:  </font>" . $series . "<hr>";

  #create a new object from serialized Red object components
  $b = unserialize($series);
  echo $a . "<font color='red'> unserialized creates:  </font>" . $b . "<hr>";

  #change the number value in the new object from 1 to 2
  echo "Assigning value of 2 to Red->number creates: <br>";
  $b->number = 2;

  #display number values in the new object
  echo $b . "<font color='red'> Red->number value: </font>" . $b->number . "<br>";
  echo $b . "<font color='red'> Red->blu->red->number value: </font>" . $b->blu->red->number . "<br>";

  # !!! bug shows Red->blu->red in the new object as 1, not 2, in PHP 5.0.0RC3
  # happily shows Red->blu->red in the new object correctly as 2 in PHP 5.0.0 Final Release
 
  #display serialized new object components
  $series = serialize($b);
  echo $b ."<font color='red'> serialized:  </font>" . $series;
?>
