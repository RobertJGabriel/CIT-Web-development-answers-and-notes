# use the "my_database" database
USE my_database;

# create a table called "hotels"
CREATE TABLE IF NOT EXISTS hotels
(
  name VARCHAR(25) PRIMARY KEY, street VARCHAR(25), 
  city VARCHAR(25), state VARCHAR(25), zip INT
);

# insert a record into the "hotels" table
INSERT INTO hotels (name, street, city, state, zip) 
  VALUES ("Las Vegas Hilton", "3000 Paradise Road", 
		"Las Vegas", "Nevada", 89109);

# retrieve 2 concatenated calculated fields
SELECT CONCAT(name, ", ", state) FROM hotels;
SELECT CONCAT_WS(",\n", name, street, city, state, zip)
FROM hotels;

# delete this sample table
DROP TABLE hotels;