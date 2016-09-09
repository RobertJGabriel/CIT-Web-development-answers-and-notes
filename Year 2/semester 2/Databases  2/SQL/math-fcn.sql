# get some square roots
SELECT SQRT(144), SQRT(125), ROUND(SQRT(125));

# get Pi and round it up and down
SELECT PI(), CEILING(PI()), FLOOR(PI());

# get some random numbers
SELECT RAND(), RAND();

# get some random numbers in the range 1-100
SELECT CEILING(RAND() * 100), CEILING(RAND() * 100);