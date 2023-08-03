

CREATE TABLE IF NOT EXISTS Person (id INT PRIMARY KEY,name VARCHAR(100) NOT NULL,surname VARCHAR(100) NOT NULL,
    phone CHAR(12) NOT NULL, birth_date DATE NOT NULL);

CREATE TABLE IF NOT EXISTS Employee (id INT PRIMARY KEY,salary DOUBLE PRECISION NOT NULL, med_field VARCHAR(100) NOT NULL,
    CONSTRAINT employee_person_fk FOREIGN KEY (id) REFERENCES Person (id),
    CONSTRAINT emp_appointment_employee_fk FOREIGN KEY (id) REFERENCES appointment_employee (employee_id));

CREATE TABLE IF NOT EXISTS Doctor (id INT PRIMARY KEY, room_num INT NOT NULL, department VARCHAR(10) NOT NULL,
    CONSTRAINT doctor_employee_fk FOREIGN KEY (id) REFERENCES Employee (id));

CREATE TABLE IF NOT EXISTS Doctor_Language (doctor_id INT, language VARCHAR(100) NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES Doctor (id));

CREATE TABLE IF NOT EXISTS Assistant (id INT PRIMARY KEY,specialization VARCHAR(10) NOT NULL,
    CONSTRAINT assistant_employee_fk FOREIGN KEY (id) REFERENCES Employee (id));

CREATE TABLE IF NOT EXISTS Intern (id INT PRIMARY KEY,internship_year VARCHAR(10) NOT NULL,
    CONSTRAINT intern_employee_fk FOREIGN KEY (id) REFERENCES Employee (id));
--Patient
CREATE TABLE IF NOT EXISTS Patient (id INT PRIMARY KEY,nip VARCHAR(13) NOT NULL,
    CONSTRAINT patient_person_fk FOREIGN KEY (id) REFERENCES Person (id));

CREATE TABLE IF NOT EXISTS Adult (id INT PRIMARY KEY,discount INT,
    CONSTRAINT adult_patient_fk FOREIGN KEY (id) REFERENCES Patient (id));

CREATE TABLE IF NOT EXISTS Child (id INT PRIMARY KEY,parent_phone VARCHAR(12) NOT NULL,
    CONSTRAINT child_patient_fk FOREIGN KEY (id) REFERENCES Patient (id));

--Medical

CREATE TABLE IF NOT EXISTS Storage (storage_id INT PRIMARY KEY, type VARCHAR(10) NOT NULL,
    capacity DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS MedicalCard (card_id INT PRIMARY KEY,description VARCHAR(1000) NOT NULL,
    id INT NOT NULL, storage_id INT NOT NULL,
    CONSTRAINT patient_medical_card_fk FOREIGN KEY (id) REFERENCES Patient (id),
    CONSTRAINT card_storaga_card_fk FOREIGN KEY (storage_id) REFERENCES Storage (storage_id));
--Appointment

CREATE TABLE IF NOT EXISTS Appointment (app_id INT PRIMARY KEY , status VARCHAR(255) NOT NULL,
    description_disease VARCHAR(500),note VARCHAR(10),appointment_date DATE NOT NULL,
    CONSTRAINT app_appointment_employee_fk FOREIGN KEY (app_id) REFERENCES appointment_employee (app_id));

CREATE TABLE IF NOT EXISTS Treatment (treatment_id INT PRIMARY KEY,description VARCHAR(500) NOT NULL,
    CONSTRAINT appointment_treatment_fk FOREIGN KEY (app_id) REFERENCES Appointment (app_id));

CREATE TABLE IF NOT EXISTS appointment_doctor (app_id INT, doctor_id INT, PRIMARY KEY (app_id, doctor_id),
    FOREIGN KEY (app_id) REFERENCES Appointment (app_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor (id)
    );

CREATE TABLE IF NOT EXISTS appointment_treatment (app_id INT, treatment_id INT, PRIMARY KEY (app_id, treatment_id),
    FOREIGN KEY (app_id) REFERENCES Appointment (app_id),
    FOREIGN KEY (treatment_id) REFERENCES Treatment (treatment_id)
    );

CREATE TABLE IF NOT EXISTS Reservation (reservation_id INT PRIMARY KEY, reservation_date DATE NOT NULL,
payment_date DATE NOT NULL,payment_method VARCHAR(5) NOT NULL, app_id INT NOT NULL, id INT NOT NULL,
    CONSTRAINT reservation_appointment_fk FOREIGN KEY (app_id) REFERENCES appointment (app_id),
    CONSTRAINT reservation_adult_fk FOREIGN KEY (id) REFERENCES adult (id));

-- Insert data into the tables

INSERT INTO Person (id, name, surname, phone, birth_date)
VALUES
    (1001, 'John', 'Doe', '+12345678901', '2000-01-01'),
    (1002, 'Jane', 'Smith', '+98765432109', '1999-01-01'),
    (1003, 'David', 'Johnson', '+34567890121', '1991-01-01'),
    (1004, 'Alice', 'Brown', '+45678901231', '1992-01-01'),
    (1005, 'Robert', 'Davis', '+56789012341', '1993-01-01'),
    (1006, 'Emma', 'Wilson', '+67890123451', '1994-01-01'),
    (1007, 'William', 'Clark', '+78901234561', '1998-01-01'),
    (1008, 'Olivia', 'Miller', '+89012345671', '1997-01-01'),
    (1009, 'Sophia', 'Wilson', '+12345678901', '1996-01-01'),--adult
    (1010, 'Oliver', 'Brown', '+23456789012', '1996-01-01'),--adult
    (1011, 'Isabella', 'Davis', '+34567890123', '1995-01-01'),
    (1012, 'Mia', 'Clark', '+45678901234', '1994-03-01');

INSERT INTO Employee (id, salary, med_field)
VALUES
    (1001, 5000.5, 'Medicine'),--d
    (1002, 4000.0, 'Cardiology'),--d / a
    (1003, 6000.0, 'Pediatrics'),--d
    (1004, 3500.25, 'Surgery'),--a / i
    (1005, 3000.3, 'Dentistry'),--a
    (1006, 2500.0, 'Orthopedics'),--i
    (1007, 2000.0, 'Gynecology');--i

INSERT INTO Doctor (id, room_num, department)
VALUES
    (1001, 101, 'Main'),
    (1002, 202, 'Main'),
    (1003, 303, 'Side');

INSERT INTO Doctor_Language (doctor_id, language)
VALUES
    (1001, 'English'),
    (1001, 'French'),
    (1002, 'Spanish'),
    (1003, 'German'),
    (1003, 'Italian');

INSERT INTO Assistant (id, specialization)
VALUES
    (1002, 'operation'),
    (1004, 'treatment'),
    (1005, 'operation');

INSERT INTO Intern (id, internship_year)
VALUES
    (1004, 'second'),
    (1006, 'second'),
    (1007, 'first');

--Patient
INSERT INTO Patient (id, nip)
VALUES
    (1009, '123-45-67-890'),
    (1010, '234-56-78-901'),
    (1011, '125-45-67-890'),
    (1012, '236-56-78-901');

INSERT INTO Adult (id, discount)
VALUES
    (1009, 10),
    (1010, 0);

INSERT INTO Child (id, parent_phone)
VALUES
    (1011, '+98765432109'),
    (1012, '+95765432107');

--MedCard
INSERT INTO Storage (storage_id, type, capacity)
VALUES
    (1, 'Main', 1000.0),
    (2, 'Side', 3000.0);

INSERT INTO MedicalCard (card_id, description, id, storage_id)
VALUES
    (2001, 'Sample description 1', 1009, 1),
    (2002, 'Sample description 2', 1010, 1),
    (2003, 'Sample description 3', 1011, 1),
    (2004, 'Sample description 4', 1012, 1);

--Appointment
INSERT INTO Appointment (app_id, status, description_disease, note, appointment_date)
VALUES
    (5001, 'completed', 'Sample description 1', 'Flue', '2020-02-02' ),
    (5002, 'completed', 'Sample description 2', 'Flue', '2020-03-02' ),
    (5003, 'cancelled by customer', 'Sample description 3', 'No reason', '2020-04-02' ),
    (5004, 'completed', 'Sample description 4', 'Allergy','2021-05-02');
--     (5003, 'cancelled by customer', 'Sample description 4', 'Birthday',1001),
--     (5001, 'completed', 'Sample description 2', 'Allergy',1001),
--     (5002, 'completed', 'Sample description 3', 'Flue',1001),
--     (5003, 'cancelled by customer', 'Sample description 4', 'Non',1001);

INSERT INTO Treatment (treatment_id, description)
VALUES
    (701, 'Sample description 1'),
    (702, 'Sample description 2'),
    (703, 'Sample description 3');

INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5001, 1001);
INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5001, 1003);
INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5002, 1001);

INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5002, 1003);
INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5003, 1003);
INSERT INTO appointment_doctor (app_id, doctor_id) VALUES (5004, 1001);


INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5001, 701);
INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5001, 702);
INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5002, 701);

INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5002, 702);
INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5003, 703);
INSERT INTO appointment_treatment (app_id, treatment_id) VALUES (5004, 701);

INSERT INTO Reservation (reservation_id, reservation_date, payment_date, payment_method, app_id, id)
VALUES (9001, '2020-02-01', '2020-02-01', 'card', 5001, 1009),
       (9002, '2020-03-01', '2020-03-01', 'card', 5002, 1009),
       (9003, '2020-04-01', '2020-04-01', 'card', 5003, 1009),
       (9004, '2020-05-01', '2020-05-01', 'card', 5004, 1009);

--(9002, '2023-06-20', '2023-06-21', 'cash', 5002),