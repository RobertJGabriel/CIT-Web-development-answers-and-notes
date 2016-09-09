# use the "my_database" database
USE my_database;

# create a table called "tub"
CREATE TABLE IF NOT EXISTS tub
(
  id	INT		AUTO_INCREMENT  PRIMARY KEY,
  num	INT		NOT NULL,
  ref	VARCHAR(10)	NOT NULL,
  qty	INT		DEFAULT 1,
  col	CHAR(10)	NOT NULL
);

# insert 10 records into the "tub" table
INSERT INTO tub (num, ref, col) VALUES (8004, 101, "Red");
INSERT INTO tub (num, ref, col) VALUES (8004, 103, "Lime");
INSERT INTO tub (num, ref, col) VALUES (8004, 104, "Blue");
INSERT INTO tub (num, ref, col) VALUES (8003, 104, "Blue");
INSERT INTO tub (num, ref, col) VALUES (8002, 105, "Red");
INSERT INTO tub (num, ref, col) VALUES (8002, 102, "Lime");
INSERT INTO tub (num, ref, col) VALUES (8002, 103, "Pink");
INSERT INTO tub (num, ref, col) VALUES (8001, 104, "Red");
INSERT INTO tub (num, ref, col) VALUES (8001, 105, "Lime");
INSERT INTO tub (num, ref, col) VALUES (8004, 102, "Blue");

# display all data in the "tub" table
SELECT * FROM tub;

# get the order number and number of items ordered
# where the color is not Pink
# and the number of items ordered is fewer than 3
# sorted by order number
SELECT num, COUNT(*) AS num_items
FROM tub
WHERE col != "Pink"
GROUP BY num
HAVING COUNT(*) < 3
ORDER BY num;

# delete this sample table
DROP TABLE IF EXISTS tub;