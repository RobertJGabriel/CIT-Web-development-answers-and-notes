CREATE TABLE IF NOT EXISTS towels
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  name	CHAR(16) ,
  color	CHAR(16) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO towels ( name , color , price )
VALUES  
( "Dolphin" , "Blue" , 9.99 ) , 
( "Wine" , "Maroon" , 12.99 ) ,
( "Harvest" , "Beige" , 7.99 ) ,
( "Daisy" , "Lilac" , 8.99 ) ,
( "Starburst" , "Yellow" , 10.99 ) ;

SELECT * FROM towels ;

UPDATE towels SET color = "White" ;
SELECT * FROM towels ;

UPDATE towels SET price = 5.99 ;
SELECT * FROM towels ;