DROP TABLE IF EXISTS students;

CREATE TABLE students (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL
);

INSERT INTO students (first_name, last_name) VALUES
('Лъчезар', 'Балев'),
('Пламена', 'Славова'),
('Диана', 'Балева'),
('Виктория', 'Балева'),
('Георги', 'Пейчев');
