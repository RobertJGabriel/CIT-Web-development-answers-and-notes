# use the "my_database" database
USE my_database;

# create a table called "games"
CREATE TABLE IF NOT EXISTS games
(
  id		VARCHAR(10)	PRIMARY KEY,
  vendor	INT		NOT NULL,
  name		CHAR(20)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL
);

# insert 5 records into the "games" table
INSERT INTO games (id, vendor, name, price) 
  VALUES ("371/2209", 1, "Scrabble", 14.50);
INSERT INTO games (id, vendor, name, price) 
  VALUES ("373/2296", 2, "Jenga", 6.99);
INSERT INTO games (id, vendor, name, price) 
  VALUES ("360/9659", 1, "Uno", 11.99);
INSERT INTO games (id, vendor, name, price) 
  VALUES ("373/5372", 3, "Connect", 5.99);
INSERT INTO games (id, vendor, name, price) 
  VALUES ("370/9470", 3, "Bingo", 8.99);

# create a table called "vendors"
CREATE TABLE IF NOT EXISTS vendors
(
  id		INT	PRIMARY KEY,
  name		CHAR(20)	NOT NULL,
  location	CHAR(20)	NOT NULL
);

# insert 3 records into the "vendors" table
INSERT INTO vendors (id, name, location) 
  VALUES (1, "Mattel Inc", "El Segundo, Ca, USA");
INSERT INTO vendors (id, name, location) 
  VALUES (2, "Hasbro Inc", "Pawtucket, RI, USA");
INSERT INTO vendors (id, name, location) 
  VALUES (3, "J.W.Spear Plc", "Enfield, Middx, UK");

# display game code, name, price and vendor name 
# for each game in the two joined tables
SELECT 	games.id 	AS ProductCode, 
	games.name 	AS Game, 
	vendors.name	AS Vendor,
	games.price	AS Price 
FROM 	games, vendors
WHERE 	vendors.id = games.vendor;

# delete these sample tables
DROP TABLE games;
DROP TABLE vendors;