<!-- example for PHP 5.0.0 final release -->

<?php

#this example is not included in the book but shows
#how to create a new class and create an instance
#then use its property and method

#define a new class
class Dog
{
  #declare a class variable property
  var $name;

  #define a class method
  function bark()
  {
    print "Woof!";
  }
}

#create an instance of the Dog class
$pooch = new Dog;

#assign a value to the class $name property
$pooch -> name = "Fido";

#retrieve the class $name property value
print $pooch -> name . " says ";

#call the class method
$pooch -> bark();

?>