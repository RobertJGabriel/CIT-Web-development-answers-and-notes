CREATE TABLE IF NOT EXISTS tiles
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  pattern	CHAR(16) ,
  color	CHAR(16) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO tiles ( pattern , color , price )
VALUES  
( "Spring" , "Green" , 9.99 ) , 
( "Summer" , "Yellow" , 12.99 ) ,
( "Autumn" , "Brown" , 9.99 ) ,
( "Winter" , "Blue" , 8.99 ) ;

SELECT * FROM tiles ;

UPDATE tiles SET pattern = "Fall" WHERE pattern = "Autumn" ;
UPDATE tiles SET color = "Gold" WHERE color = "Yellow" ;
UPDATE tiles SET price = 10.99 WHERE price = 9.99 ;
UPDATE tiles SET price = 11.99 WHERE id = 4 ;

SELECT * FROM tiles ;