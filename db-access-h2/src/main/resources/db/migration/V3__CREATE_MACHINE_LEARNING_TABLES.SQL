CREATE SCHEMA IF NOT EXISTS machine_learning;

CREATE TABLE IF NOT EXISTS machine_learning.potato (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    "Date" TEXT,
    jd TEXT,
    DAP TEXT,
    TMIN TEXT,
    TMAX TEXT,
    TMEAN TEXT,
    "Rain days>0.1mm" TEXT,
    GDD TEXT,
    "Accu. GDD" TEXT,
    "Daily blight obsrv." TEXT,
    "Tmin>8" TEXT,
    "Tmax<25" TEXT,
    Rain TEXT,
    "Count of IP requirements" TEXT,
    "1 if count=3" TEXT,
    "IP Result" TEXT,
    Season TEXT
);

CREATE TABLE IF NOT EXISTS machine_learning.littoralis (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    "Date" TEXT,
    "Egg-masses" TEXT,
    Larves TEXT,
    Temp TEXT,
    RH TEXT,
    "Year" TEXT,
    "Count" TEXT,
    LastMasses TEXT,
    LastLarves TEXT
);

CREATE TABLE IF NOT EXISTS machine_learning.cotton_leaf_data (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Week TEXT,
    "Date" TEXT,
    "No. S. littoralis" TEXT,
    "H Temp." TEXT,
    "L Temp." TEXT,
    RH TEXT,
    "Biological control protocol" TEXT,
    weeknum TEXT,
    "year" TEXT
);

CREATE TABLE IF NOT EXISTS machine_learning.cotton_leaf_temp (
    "Date" TEXT,
    "H Temp." TEXT,
    "L Temp." TEXT,
    weeknum TEXT,
    "year" TEXT
);

