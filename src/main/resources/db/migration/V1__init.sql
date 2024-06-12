CREATE TABLE IF NOT EXISTS film (
    id SERIAL NOT NULL ,
    title VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL,
    duration DECIMAL(10,2) NOT NULL ,
    release_year INT NOT NULL ,
    genre VARCHAR(30) NOT NULL ,
    language VARCHAR(30) NOT NULL ,
    country VARCHAR(50) NOT NULL ,
    synopsis VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL NOT NULL,
    description VARCHAR(60) NOT NULL,
    budget DECIMAL(10,2) NOT NULL,
    minutes DECIMAL(10,2) NOT NULL,
    location VARCHAR(100) NOT NULL ,
    date_shot DATE NOT NULL ,
    actors_involved VARCHAR(200) NOT NULL,
    film_id INT,
    FOREIGN KEY (film_id) REFERENCES film(id),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS character(
  id SERIAL NOT NULL,
   name VARCHAR(50) NOT NULL,
  description VARCHAR(60) NOT NULL,
  cost DECIMAL(10,2) NOT NULL,
  actor VARCHAR(50),
  stock VARCHAR(60) NOT NULL,
  scene_id INT,
  FOREIGN KEY (scene_id) REFERENCES scene(id),
    PRIMARY KEY (id)
);