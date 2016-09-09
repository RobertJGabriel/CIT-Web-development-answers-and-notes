# This SQL script is not listed in the book 
# - it merely creates the bottom table on page 163 

# use the "my_database" database
USE my_database;

# create a table called "vendors"
CREATE TABLE IF NOT EXISTS vendors
(
  id		INT	PRIMARY KEY,
  name		CHAR(20)	NOT NULL,
  location	CHAR(20)	NOT NULL
);

# insert 3 records into the "vendors" table
INSERT INTO vendors (id, name, location) 
  VALUES (1, "Mattel Inc", "El Segundo, CA, USA");
INSERT INTO vendors (id, name, location) 
  VALUES (2, "Hasbro Inc", "Pawtucket, RI, USA");
INSERT INTO vendors (id, name, location) 
  VALUES (3, "J.W.Spear Plc", "Enfield, Middx, UK");

# display all data in the "vendors" table
SELECT * FROM vendors;