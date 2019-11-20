CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    done boolean DEFAULT FALSE
);