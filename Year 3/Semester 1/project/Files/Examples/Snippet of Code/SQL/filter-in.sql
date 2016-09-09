# use the "my_database" database
USE my_database;

# create a table called "coffee_makers"
CREATE TABLE IF NOT EXISTS coffee_makers
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  make		VARCHAR(25)	NOT NULL,
  model		VARCHAR(25)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL
);

# insert 5 records into the "coffee_makers" table
INSERT INTO coffee_makers (make, model, price) 
  VALUES ("Cookworks", "TSK-182", 19.99);
INSERT INTO coffee_makers (make, model, price) 
  VALUES ("Morphy Richards", "Europa", 38.25);
INSERT INTO coffee_makers (make, model, price) 
  VALUES ("Krups", "Vivo", 79.50);
INSERT INTO coffee_makers (make, model, price) 
  VALUES ("DeLonghi", "EC410", 139.00);
INSERT INTO coffee_makers (make, model, price) 
  VALUES ("Gaggia", "DeLuxe", 199.00);

# show all data in the "coffee_makers" table
SELECT * FROM coffee_makers;

# show records where make is "Krups", "Gaggia" or "DeLonghi"
# and the model is not "TSK-182" or "EC410"
SELECT * FROM coffee_makers 
WHERE make IN ("Krups", "Gaggia", "DeLonghi")
AND model NOT IN ("TSK-182", "EC410");

# delete this sample table
DROP TABLE IF EXISTS coffee_makers;