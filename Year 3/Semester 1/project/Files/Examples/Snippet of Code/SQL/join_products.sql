# This SQL script is not listed in the book 
# - it merely creates the table on page 162 

# use the "my_database" database
USE my_database;

# create a table called "products"
CREATE TABLE IF NOT EXISTS products
(
  id		VARCHAR(10)	PRIMARY KEY,
  game		CHAR(20)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL,
  vendor	CHAR(20)	NOT NULL,
  location	CHAR(20)	NOT NULL
);

# insert 5 records into the "products" table
INSERT INTO products (id, game, price, vendor, location) 
  VALUES ("371/2209", "Scrabble", 14.50, "Mattel Inc", "El Segundo, CA, USA");
INSERT INTO products (id, game, price, vendor, location) 
  VALUES ("373/2296", "Jenga", 6.99, "Hasbro Inc", "Pawtucket, RI, USA");
INSERT INTO products (id, game, price, vendor, location) 
  VALUES ("360/9659", "Uno", 11.99, "Mattel Inc", "El Segundo, CA, USA");
INSERT INTO products (id, game, price, vendor, location) 
  VALUES ("373/5372", "Connect", 5.99, "J.W.Spear Plc", "Enfield, Middx, UK");
INSERT INTO products (id, game, price, vendor, location) 
  VALUES ("370/9470", "Bingo", 8.99, "J.W.Spear Plc", "Enfield, Middx, UK");

# display all data in the "products" tables
SELECT * FROM products;

# delete this sample table
DROP TABLE IF EXISTS products;