CREATE TABLE IF NOT EXISTS watches
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  model	CHAR(16) ,
  style	CHAR(16) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO watches ( model , style , price )
VALUES
( "Boston" , "Gents" , 70.00 ) ,
( "Avante" , "Ladies" , 90.00 ) ,
( "Club" , "Gents" , 60.00 ) ,
( "Denver" , "Ladies" , 80.00 ) ,
( "Eton" , "Gents" , 50.00 ) ;

SELECT * FROM watches ORDER BY price ;

SELECT model , price FROM watches ORDER BY model ;

SELECT model , style FROM watches ORDER BY  style ;