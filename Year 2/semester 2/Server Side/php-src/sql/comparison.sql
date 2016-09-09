CREATE TABLE IF NOT EXISTS items
(
  id				INT AUTO_INCREMENT PRIMARY KEY ,
  name			CHAR(16) NOT NULL ,
  quantity		INT NOT NULL ,
  pass			CHAR(40) NOT NULL ,
  stamp			DATETIME NOT NULL
) ;

INSERT INTO items ( name , quantity , pass , stamp   )
VALUES  
( "Alan" , 10 , SHA1("m00nriver" ) , NOW() ) , 
( "Dean" , 20 , SHA1("bluem00n") ,  NOW() ) ,
( "Gary" , 30 , SHA1("m00nlight") , NOW() ) ,
( "Mike" , 40 ,  SHA1("m00nshine") , NOW() ) ;

SELECT name , quantity FROM items WHERE quantity BETWEEN 15 AND 35 ;

SELECT stamp , pass FROM items WHERE quantity < 15 ;