CREATE TABLE IF NOT EXISTS coffee_machines
(
  id	INT AUTO_INCREMENT PRIMARY KEY ,
  make	CHAR(16) ,
  model	CHAR(16) ,
  price	DECIMAL(6,2)
) ;

INSERT INTO coffee_machines ( make , model , price )
VALUES
( "Krups" , "Dolce Gusto" , 140.00 ) ,
( "Gaggia" , "Cubika" , 170.00 ) ,
( "DeLonghi" , "Magnifica" , 500.00 ) ,
( "Magimix" , "Nespresso" , 300.00 ) ,
( "Cuisinart" , "Espresso" , 280.00 ) ;

SELECT * FROM coffee_machines WHERE id = 3 ;

SELECT * FROM coffee_machines WHERE make IN ( "Krups" , "Gaggia" ) ;

SELECT * FROM coffee_machines WHERE make NOT IN ( "Krups" , "Gaggia" ) ;