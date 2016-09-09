# use the "my_database" database
USE my_database;

# create a table called "hers" and insert 3 records
CREATE TABLE IF NOT EXISTS hers
( id INT AUTO_INCREMENT PRIMARY KEY, name CHAR(20) );
INSERT INTO hers ( name ) VALUES ("Linda");
INSERT INTO hers ( name ) VALUES ("Donna");
INSERT INTO hers ( name ) VALUES ("Kay");

# create a table called "his" and insert 3 records
CREATE TABLE IF NOT EXISTS his
( id INT AUTO_INCREMENT PRIMARY KEY, name CHAR(20) );
INSERT INTO his ( name ) VALUES ("Michael");
INSERT INTO his ( name ) VALUES ("David");
INSERT INTO his ( name ) VALUES ("Andrew");

# display all data in the "hers" table
SELECT * FROM hers;

# display all data in the "his" table
SELECT * FROM his;

# display all data in "hers" and "his" 
# sorted by id
SELECT * FROM hers
UNION
SELECT * FROM his
ORDER BY id;

# display all data in "hers" and "his" 
# sorted by name
SELECT * FROM hers
UNION
SELECT * FROM his
ORDER BY name;

# delete these sample tables
DROP TABLE IF EXISTS hers;
DROP TABLE IF EXISTS his;
