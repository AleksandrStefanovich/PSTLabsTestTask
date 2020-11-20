CREATE TABLE users(
id VARCHAR PRIMARY KEY,
name VARCHAR,
age INT
);
CREATE TABLE account(
id VARCHAR PRIMARY KEY,
status VARCHAR
);
CREATE TABLE wallet(
id VARCHAR PRIMARY KEY,
balance DOUBLE
);
CREATE TABLE role(
id VARCHAR,
name VARCHAR PRIMARY KEY
);