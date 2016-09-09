# use the "my_database" database
USE my_database;

# create a table called "ps_games" and insert 2 records
CREATE TABLE IF NOT EXISTS ps_games
( title CHAR(20), ages VARCHAR(3) );
INSERT INTO ps_games ( title, ages) 
  VALUES ("Grand Theft Auto", "18+");
INSERT INTO ps_games (title, ages) 
  VALUES ("Colin McRae Rally", "11+");

# create a table called "xbox_games" and insert 2 records
CREATE TABLE IF NOT EXISTS xbox_games
( title CHAR(20), ages VARCHAR(3) );
INSERT INTO xbox_games (title, ages) 
  VALUES ("Splinter Cell", "15+");
INSERT INTO xbox_games (title, ages) 
  VALUES ("Colin McRae Rally", "11+");

# display all data in the "ps_games" table
SELECT * FROM ps_games;

# display all data in the "xbox_games" table
SELECT * FROM xbox_games;

# display unique data in "ps_games", "xbox_games" 
SELECT * FROM ps_games
UNION
SELECT * FROM xbox_games;

# display all data in "ps_games", "xbox_games" 
SELECT * FROM ps_games
UNION ALL
SELECT * FROM xbox_games;

# delete these sample tables
DROP TABLE IF EXISTS ps_games;
DROP TABLE IF EXISTS xbox_games;
