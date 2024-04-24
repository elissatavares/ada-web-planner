CREATE DATABASE planner;

\c planner;

CREATE TABLE users (
                       id UUID PRIMARY KEY NOT NULL,
                       username TEXT NOT NULL,
                       email TEXT NOT NULL UNIQUE,
                       password TEXT NOT NULL,
                       created_at TIMESTAMP NOT NULL
);


CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY NOT NULL ,
                       title TEXT NOT NULL ,
                       description TEXT,
                       created_at TIMESTAMP NOT NULL ,
                       due_date TIMESTAMP,
                       completed BOOLEAN DEFAULT FALSE
);