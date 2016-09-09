CREATE TABLE IF NOT EXISTS paintings
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  artists		CHAR(32) ,
  titles		CHAR(64) ,
  styles		CHAR(16) 
) ;

INSERT INTO paintings ( artists , titles , styles   )
VALUES  
( "Salvador Dali" , "Meditative Rose" , "Surrealist" ) , 
( "Henri Matisse" , "Blue Hair" , "Impressionist" ) ,
( "Pablo Picasso" , "Girl Reading" , "Cubist" ) ,
( "Miarc Chegall" , "Bonjour Paris" , "Primitivist" ) ;

SELECT * FROM paintings ;

SELECT artists FROM paintings ;
SELECT titles FROM paintings ;
SELECT styles FROM paintings ;