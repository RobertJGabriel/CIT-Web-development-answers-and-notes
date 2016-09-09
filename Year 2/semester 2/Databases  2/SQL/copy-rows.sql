# use the "my_database" database
USE my_database;

# create a table called "800w_microwaves" and
# copy all 800w  microwave data from "microwaves"
CREATE TABLE IF NOT EXISTS 800w_microwaves
SELECT * FROM microwaves WHERE power = 800;

# create a table called "900w_microwaves" and
# copy all 900w  microwave data from "microwaves"
CREATE TABLE IF NOT EXISTS 900w_microwaves
SELECT * FROM microwaves WHERE power = 900;

# show all existing tables
SHOW TABLES;

# show all data in the "microwaves" database
SELECT * FROM microwaves;

# show all data in the "800w_microwaves" database
SELECT * FROM 800w_microwaves;

# show all data in the "900w_microwaves" database
SELECT * FROM 900w_microwaves;

# delete sample tables
DROP TABLE IF EXISTS 800w_microwaves;
DROP TABLE IF EXISTS 900w_microwaves;

# show all existing tables
SHOW TABLES;
