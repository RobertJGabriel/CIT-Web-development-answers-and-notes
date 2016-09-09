# use the "my_database" database
USE my_database;

# create a table called "sharp_ovens"
CREATE TABLE sharp_ovens
(
  id		INT	AUTO_INCREMENT PRIMARY KEY,
  model		VARCHAR(20)	NOT NULL,
  power		INT		NOT NULL,
  grill		VARCHAR(3)	DEFAULT "No"
);

# insert data into the "sharp_ovens" table
INSERT INTO sharp_ovens (model, power, grill)
	VALUES ("R654", 800, "Yes");
INSERT INTO sharp_ovens (model, power, grill)
	VALUES ("R64ST", 800, "Yes");

# show all data in the "microwaves" table
SELECT * FROM microwaves;

# show all data in the "sharp_ovens" table
SELECT * FROM sharp_ovens;

# copy data from "microwaves" into "sharp_ovens"
INSERT INTO sharp_ovens (model, power) SELECT model, power FROM microwaves WHERE maker = "Sharp";

# show all data in the "sharp_ovens" table
SELECT * FROM sharp_ovens;

# delete sample tables used in this chapter
DROP TABLE microwaves;
DROP TABLE sharp_ovens;
