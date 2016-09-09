# use the "my_database" database
USE my_database;

# create a table called "parts"
CREATE TABLE IF NOT EXISTS parts
(
  num	INT		PRIMARY KEY,
  name	CHAR(20)	NOT NULL
);

# insert 3 records into the "parts" table
INSERT INTO parts (num, name) 
  VALUES (382131, "Standard bracket");
INSERT INTO parts (num, name) 
  VALUES (382132, "Slide bracket");
INSERT INTO parts (num, name) 
  VALUES (382133, "Low-mount bracket");

# create a table called "parts_prices"
CREATE TABLE IF NOT EXISTS parts_prices
(
  num	INT		PRIMARY KEY,
  price	DECIMAL(6,2)	NOT NULL
);

# insert 3 records into the "parts_prices" table
INSERT INTO parts_prices (num, price) 
  VALUES (382131, 8.99);
INSERT INTO parts_prices (num, price) 
  VALUES (382132, 10.99);
INSERT INTO parts_prices (num, price) 
  VALUES (382133, 29.99);

# display all data in the "parts" and "parts_prices" table
SELECT * FROM parts;
SELECT * FROM parts_prices;

# display each part number, name and price
SELECT 	p.*, pp.price
FROM 	parts AS p, parts_prices AS pp
WHERE 	p.num = pp.num;

# delete these sample tables
DROP TABLE IF EXISTS parts;
DROP TABLE IF EXISTS parts_prices;