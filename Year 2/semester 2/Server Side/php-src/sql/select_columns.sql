CREATE TABLE IF NOT EXISTS phones
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  make		CHAR(16) ,
  model		CHAR(16) ,
  platform	CHAR(16) 
) ;

INSERT INTO phones ( make , model , platform   )
VALUES  
( "Apple" , "iPhone" , "iOS" ) , 
( "RIM" , "Curve" , "BlackBerry" ) ,
( "HTC" , "Desire" , "Android" ) ,
( "Nokia" , "Lumia" , "Windows" ) ;

SELECT * FROM phones ;

SELECT id , make FROM phones ;
SELECT make , model FROM phones ;
SELECT id , platform FROM phones ;