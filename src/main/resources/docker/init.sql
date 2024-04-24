CREATE DATABASE planner;

\c planner;

CREATE TABLE users (
                       id UUID PRIMARY KEY NOT NULL,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL
);


CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY NOT NULL ,
                       title VARCHAR(255) NOT NULL ,
                       description VARCHAR(255),
                       created_at TIMESTAMP NOT NULL ,
                       due_date TIMESTAMP,
                       completed BOOLEAN DEFAULT FALSE
);

