# show records where the name contains a "W"
SELECT * FROM glass_sets WHERE name REGEXP "W";

# show records where the name contains a "W" or a "N"
SELECT * FROM glass_sets WHERE name REGEXP "[WN]";

# show records where the name begins with a "B"
SELECT * FROM glass_sets WHERE name REGEXP "^B";

# show records where the name ends with a "H"
SELECT * FROM glass_sets WHERE name REGEXP "H$";

# show records where the name begins with a "B" or "C"
SELECT * FROM glass_sets WHERE name REGEXP "^[BC]";

# show records where the name has 6 characters
# SELECT * FROM glass_sets WHERE name REGEXP "^......$";
# alternatively this query could use REGEXP "^.{6}$";

# delete this sample table
DROP TABLE IF EXISTS glass_sets;