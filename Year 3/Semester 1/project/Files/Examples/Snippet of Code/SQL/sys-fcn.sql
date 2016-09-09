# get the version number and current user
SELECT VERSION(), USER();

# get the thread identity
SHOW PROCESSLIST;

# create a new user with full privileges
GRANT ALL PRIVILEGES ON *.* TO monty@localhost
IDENTIFIED BY "some_pass" WITH GRANT OPTION;

# confirm privileges for the new user
SHOW GRANTS FOR monty@localhost;


