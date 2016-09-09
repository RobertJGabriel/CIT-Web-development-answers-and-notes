# This SQL script is not listed in the book 
# - it merely creates the top table on page 163 

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

# show all data in the "games" table
SELECT * FROM games;