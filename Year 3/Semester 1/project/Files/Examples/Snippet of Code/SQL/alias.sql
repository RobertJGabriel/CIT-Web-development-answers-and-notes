# use the "my_database" database
USE my_database;

# create a table called "fishermans_wharf"
CREATE TABLE IF NOT EXISTS fishermans_wharf
(
  street	VARCHAR(20) 	PRIMARY KEY, 
  city 		CHAR(20)	DEFAULT "San Francisco", 
  state 	CHAR(2) 	DEFAULT "CA", 
  zip 		INT		DEFAULT 94133
);

# insert 2 records into the "fishermans_wharf" table
INSERT INTO fishermans_wharf (street) 
  VALUES ("145 Jefferson St.");
INSERT INTO fishermans_wharf (street) 
  VALUES ("175 Jefferson St.");

# show all data in the "fishermans_wharf" table
SELECT * FROM fishermans_wharf;

# retrieve 2 concatenated calculated fields
SELECT CONCAT_WS(", ", street, city, state, zip) 
AS Wax_Museum
FROM fishermans_wharf
WHERE street = "145 Jefferson St.";

SELECT CONCAT_WS(", ", street, city, state, zip) 
AS "Ripley's Believe It Or Not! Museum"
FROM fishermans_wharf 
WHERE street = "175 Jefferson St.";

# display the table format
EXPLAIN fishermans_wharf;

# delete this sample table
DROP TABLE fishermans_wharf;