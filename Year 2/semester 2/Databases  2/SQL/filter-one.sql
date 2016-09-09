# use the "my_database" database
USE my_database;

# create a table called "clock radios"
CREATE TABLE IF NOT EXISTS clock_radios
(
  code		CHAR(8)		PRIMARY KEY,
  make	 	VARCHAR(25)	NOT NULL,
  model		VARCHAR(25)	NOT NULL,	
  price		DECIMAL(4,2)	NOT NULL
);

# insert 5 records into the "clock_radios" table
INSERT INTO clock_radios (code, make, model, price) 
  VALUES ("512/4792", "Alba", "C2108", 6.75);
INSERT INTO clock_radios (code, make, model, price) 
  VALUES ("512/4125", "Hitachi", "KC30", 8.99);
INSERT INTO clock_radios (code, make, model, price) 
  VALUES ("512/1458", "Philips", "AJ3010", 19.99);
INSERT INTO clock_radios (code, make, model, price) 
  VALUES ("512/3669", "Morphy Richards", "28025", 19.99);
INSERT INTO clock_radios (code, make, model, price) 
  VALUES ("512/1444", "Sony", "C253", 29.49);

# show records in "clock_radios" if price is below 19.99
SELECT * FROM clock_radios WHERE price < 19.99;

# show records in "clock_radios" if price is above 19.99
SELECT * FROM clock_radios WHERE price > 19.99;

# show records in "clock_radios" if price is 19.99 or less
SELECT * FROM clock_radios WHERE price <= 19.99;

# delete this sample table
DROP TABLE IF EXISTS clock_radios;