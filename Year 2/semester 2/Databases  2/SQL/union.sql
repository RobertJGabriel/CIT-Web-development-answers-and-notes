# use the "my_database" database
USE my_database;

# create a table called "ps_games" and insert 2 records
CREATE TABLE IF NOT EXISTS ps_games
( code 	VARCHAR(10), title CHAR(20), ages VARCHAR(3) );

# insert 2 records into the "ps_games" table
INSERT INTO ps_games (code, title, ages) 
  VALUES ("567/3573", "Crash Bash Platinum", "3+");
INSERT INTO ps_games (code, title, ages) 
  VALUES ("567/0301", "The Italian Job", "11+");

# create a table called "xbox_games" and insert 2 records
CREATE TABLE IF NOT EXISTS xbox_games
( code 	VARCHAR(10), title CHAR(20), ages VARCHAR(3) );

# insert 2 records into the "xbox_games" table
INSERT INTO xbox_games (code, title, ages) 
  VALUES ("567/2660", "Blinx", "3+");
INSERT INTO xbox_games (code, title, ages) 
  VALUES ("567/0569", "Turok Evolution", "15+");

# create a table called "cube_games" and insert 2 records
CREATE TABLE IF NOT EXISTS cube_games
( code 	VARCHAR(10), title CHAR(20), ages VARCHAR(3) );

# insert 2 records into the "cube_games" table
INSERT INTO cube_games (code, title, ages) 
  VALUES ("567/0428", "Scooby-Doo", "3+");
INSERT INTO cube_games (code, title, ages) 
  VALUES ("567/0411", "Resident Evil", "15+");

# display all data in "ps_games", "xbox_games" 
# and "cube_games" as a single result set
SELECT * FROM ps_games
UNION
SELECT * FROM xbox_games
UNION
SELECT * FROM cube_games;

# delete these sample tables
DROP TABLE IF EXISTS ps_games;
DROP TABLE IF EXISTS xbox_games;
DROP TABLE IF EXISTS cube_games;