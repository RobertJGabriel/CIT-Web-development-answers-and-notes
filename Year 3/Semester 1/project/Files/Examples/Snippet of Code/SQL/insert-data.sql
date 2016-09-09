# use the "my_database" database
USE my_database;

# create a table named "prints" with 3 columns
CREATE TABLE prints
(
  id	INT		NOT NULL,
  code	VARCHAR(8)	NOT NULL,
  name	VARCHAR(20)	NOT NULL,
  PRIMARY KEY(id)	
);

# insert 3 records into the "prints" table
INSERT INTO prints VALUES 
(
  1, "624/1636", "Lower Manhattan"
);

INSERT INTO prints VALUES 
(
  2, "624/1904", "Hill Town"
);

INSERT INTO prints VALUES 
(
  3, "624/1681", "Roscoff Trawlers"
);

# show all "prints" data
SELECT * FROM prints;

# delete this sample table
DROP TABLE prints;


