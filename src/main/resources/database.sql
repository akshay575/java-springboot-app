CREATE TABLE student (
    sid NUMBER(5) AUTO_INCREMENT,
    name VARCHAR(50),
    address VARCHAR(50),
    course VARCHAR(20),
    registration TIMESTAMP
);

INSERT INTO student(sid, name, address, course, registration) VALUES(1, 'Akshay', 'Bangalore', 'CS', '2020-05-01 16:00:00');
INSERT INTO student(sid, name, address, course, registration) VALUES(2, 'Deepak', 'Chennai', 'EC', '2020-05-01 16:30:00');
INSERT INTO student(sid, name, address, course, registration) VALUES(3, 'Saurabh', 'Lucknow', 'EE', '2020-05-05 17:00:00');
INSERT INTO student(sid, name, address, course, registration) VALUES(4, 'Murali', 'Bangalore', 'IT', '2020-05-10 17:30:00');