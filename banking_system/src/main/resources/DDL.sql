CREATE DATABASE banking_system;

CREATE TABLE banks
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100)
);

CREATE TABLE users
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(100),
    surname VARCHAR(100)
);

CREATE TABLE accounts
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number  INT,
    bank_id INT NOT NULL REFERENCES banks (id),
    user_id INT NOT NULL REFERENCES users (id),
    balance numeric(19, 2)
);

CREATE TABLE dep_with_transactions
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type       VARCHAR(20),
    amount     numeric(19, 2),
    date       date,
    time       time without time zone,
    account_id INT NOT NULL REFERENCES accounts (id)
);

CREATE TABLE transfer_transactions
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type       VARCHAR(20),
    amount     numeric(19, 2),
    date       date,
    time       time without time zone,
    account_of_sender_id INT NOT NULL REFERENCES accounts (id),
    account_of_getter_id INT NOT NULL REFERENCES accounts (id)
)

