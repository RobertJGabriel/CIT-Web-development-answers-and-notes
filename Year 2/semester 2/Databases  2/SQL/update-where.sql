# use the "my_database" database
USE my_database;

# show all "bath_towels" data
SELECT * FROM bath_towels;

# update specific fields in the "color" column
UPDATE bath_towels SET color = "Beige"
WHERE name = "Harvest";

UPDATE bath_towels SET color = "Blue"
WHERE name = "Dolphin";

UPDATE bath_towels SET color = "Lilac"
WHERE name = "Daisy";

UPDATE bath_towels SET name = "Tempest", color = "Maroon"
WHERE code = "821/9628";

# show all "bath_towels" data
SELECT * FROM bath_towels;


