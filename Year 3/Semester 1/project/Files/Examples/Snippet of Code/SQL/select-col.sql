# use the "my_database" database
USE my_database;

# create a table called "microwaves"
CREATE TABLE IF NOT EXISTS microwaves
(
  id	INT		AUTO_INCREMENT PRIMARY KEY,
  maker	VARCHAR(20) 	NOT NULL,
  model	VARCHAR(20)	NOT NULL,
  power	INT		NOT NULL
);

# insert data into the "microwaves" table
INSERT INTO microwaves ( maker, model, power )
	VALUES ("Sharp", "R254SL", 800);
INSERT INTO microwaves ( maker, model, power )
	VALUES ("Sharp", "R33STM", 900);
INSERT INTO microwaves ( maker, model, power )
	VALUES ("Sanyo", "EMS3553", 900);
INSERT INTO microwaves ( maker, model, power )
	VALUES ("Panasonic", "NNE442", 900);
INSERT INTO microwaves ( maker, model, power )
	VALUES ("Daewoo", "KDR3000", 800);

# show all data in the "microwaves" database
SELECT * FROM microwaves;

# show all data in the "maker" column
SELECT maker FROM microwaves;

# show all data in the "model" column
SELECT model FROM microwaves;

# delete this sample table
DROP TABLE IF EXISTS microwaves;