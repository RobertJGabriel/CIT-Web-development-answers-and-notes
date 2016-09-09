CREATE TABLE IF NOT EXISTS pens
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  maker	CHAR(32) ,
  model	CHAR(32) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO pens ( maker , model , price )
VALUES  
( "Montegrappa" , "Silver Dragon" , 950.00 ) , 
( "Paul Rossi" , "Egyptian Lady" , 650.00 ) ,
( "Michel Perchin" , "Red Cross" , 450.00 ) ,
( "Bexley" , "Poseidon Magnum" , 350.00 ) ;

SELECT * FROM pens ;

DELETE FROM pens WHERE maker = "Montegrappa" ;
SELECT * FROM pens ;

DELETE FROM pens WHERE model = "Egyptian Lady" ;
SELECT * FROM pens ;

DELETE FROM pens WHERE price = 450.00 ;
SELECT * FROM pens ;

DELETE FROM pens WHERE id = 4 ;
SELECT * FROM pens ;