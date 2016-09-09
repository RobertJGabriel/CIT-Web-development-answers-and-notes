# use the "my_database" database
USE my_database;

# create a table called "members"
CREATE TABLE IF NOT EXISTS members
(
  id		INT	AUTO_INCREMENT	PRIMARY KEY,
  name		CHAR(10)	NOT NULL,
  email		VARCHAR(30)
);

# insert 5 records into the "members" table
INSERT INTO members (name) 
  VALUES ("Abraham");
INSERT INTO members (name, email) 
  VALUES("Homer", "homer@mailserver.usa");
INSERT INTO members (name) 
  VALUES ("Marge");
INSERT INTO members (name, email) 
  VALUES("Bart", "bart@mailserver.usa");
INSERT INTO members (name) VALUES ("Lisa");

# count the total number of rows
SELECT COUNT(*) AS total_number_of_rows 
FROM members;

# count the total number of rows
SELECT COUNT(email) AS rows_with_email_addresses
FROM members;

# delete this sample table
DROP TABLE IF EXISTS members;