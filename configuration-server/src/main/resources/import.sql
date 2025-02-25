DROP TABLE IF EXISTS PROPERTIES;
CREATE TABLE PROPERTIES (id INT AUTO_INCREMENT  PRIMARY KEY, APPLICATION VARCHAR(25) NOT NULL, PROFILE VARCHAR(25) NOT NULL, LABEL VARCHAR(25) NOT NULL, PROP_KEY VARCHAR(25) NOT NULL, PROP_VALUE VARCHAR(200) NOT NULL);

INSERT INTO properties (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VALUE) VALUES
    ('payments-service', 'development', '06-Async-communication', 'secret', '123');
