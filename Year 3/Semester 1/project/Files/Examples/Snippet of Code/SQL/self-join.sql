# use the "my_database" database
USE my_database;

# create a table called "staff"
CREATE TABLE IF NOT EXISTS staff
(
  dept	CHAR(20)	NOT NULL,
  name	CHAR(20)	PRIMARY KEY
);

# insert 6 records into the "staff" table
INSERT INTO staff (dept, name) VALUES ("Sales", "Jo Brown");
INSERT INTO staff (dept, name) VALUES ("Legal", "Max Tiler");
INSERT INTO staff (dept, name) VALUES ("Works", "Ed Frost");
INSERT INTO staff (dept, name) VALUES ("Sales", "Sue Ebner");
INSERT INTO staff (dept, name) VALUES ("Works", "Al Morris");
INSERT INTO staff (dept, name) VALUES ("Sales", "Tony West");

# display all data in the "staff" table
SELECT * FROM staff;

# display the all members of staff 
# in the same department as Tony West
SELECT 	s1.dept AS Department, s1.name AS Name
FROM 	staff AS s1, staff AS s2
WHERE 	s1.dept = s2.dept AND s2.name = "Tony West";

# delete this sample table
DROP TABLE IF EXISTS staff;