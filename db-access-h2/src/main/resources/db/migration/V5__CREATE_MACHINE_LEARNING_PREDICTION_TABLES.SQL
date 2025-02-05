CREATE SCHEMA IF NOT EXISTS machine_learning;

CREATE TABLE IF NOT EXISTS machine_learning.accuracy (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    rmse DOUBLE PRECISION NOT NULL,
    rrmse DOUBLE PRECISION NOT NULL,
    me DOUBLE PRECISION NOT NULL,
    rme DOUBLE PRECISION NOT NULL,
    class VARCHAR(60) NOT NULL
);