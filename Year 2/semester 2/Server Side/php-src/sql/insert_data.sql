CREATE TABLE IF NOT EXISTS prints
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  name	VARCHAR(32) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO prints ( name , price )
VALUES  ( "Merry Structure" , 29.99 ) , ( "Heavy Red" , 24.99 ) , ( "Black Lines" , 45.99 ) ;

SELECT * FROM prints ;