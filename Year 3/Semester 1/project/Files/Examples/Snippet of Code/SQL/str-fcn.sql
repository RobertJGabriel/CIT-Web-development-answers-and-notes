# use the "my_database" database
USE my_database;

# create a table called "party"
CREATE TABLE IF NOT EXISTS party
(
  id	INT	AUTO_INCREMENT PRIMARY KEY,
  dept CHAR(10), name CHAR(25)
);

# insert 3 records into the "party" table
INSERT INTO party (dept, name) 
  VALUES ("accounts", "Graham Miller");
INSERT INTO party (dept, name) 
  VALUES ("sales", "Gary Miller");
INSERT INTO party (dept, name) 
  VALUES ("production", "Graham Wallace");

# get 3-letter substrings from the dept column
SELECT SUBSTRING(dept, 1, 3) FROM party;

# get cases name and length in the production dept
SELECT UPPER(name), LOWER(name), LENGTH(name) FROM party
WHERE dept = "production"; 

# get names that sound like "Gary Miller"
SELECT SOUNDEX(name),name FROM party 
WHERE SOUNDEX(name) = SOUNDEX("Gary Miller");

# delete this sample table
DROP TABLE IF EXISTS party;