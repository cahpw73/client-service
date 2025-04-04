CREATE TABLE clients (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    birth_date DATE NOT NULL
);

-- Insert initial data
INSERT INTO clients (first_name, last_name, age, birth_date) VALUES
('Ana', 'Gomez', 30, '1993-05-21'),
('Luis', 'Perez', 45, '1978-11-10'),
('Carla', 'Sanchez', 60, '1963-03-05');
