CREATE TABLE IF NOT EXISTS top_5_films
(
  position	INT  PRIMARY KEY ,
  title	CHAR(32) 	NOT NULL,
  year	INT	NOT NULL 
) ;

INSERT INTO top_5_films ( position , title , year )
VALUES
( 1 , "Citizen Kane" , 1941 ) ,
( 2 , "Casablanca" , 1942 ) ,
( 3 , "The Godfather" , 1972 ) ,
( 4 , "Gone With The Wind" , 1939 ) ,
( 5 , "Lawrence Of Arabia" , 1962 ) ;

SELECT * FROM top_5_films ORDER BY position DESC ;

SELECT year , title FROM top_5_films ORDER BY year ASC ;

SELECT title , year FROM top_5_films ORDER BY  title ASC;