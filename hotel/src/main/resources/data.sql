DROP TABLE IF EXISTS ROOMS;
CREATE TABLE ROOMS(
    ID INT PRIMARY KEY,  
    TYPE VARCHAR(255),
    BEDS INT,
    BED_SIZE DOUBLE,
    BALCONY BOOLEAN,
    VIEW VARCHAR(255),
    ACCESSIBLE BOOLEAN,
    CHECK_IN_DATE DATE,
    CHECK_OUT_DATE DATE,
    PRICE DOUBLE,
    BOOKED BOOLEAN
);

INSERT INTO ROOMS VALUES(1, 'Single', 1, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 80, false);
INSERT INTO ROOMS VALUES(2, 'Double', 1, 1.5, true, 'Garden View', false, '2023-01-01', '2023-01-02', 120, false);
INSERT INTO ROOMS VALUES(3, 'Twin', 2, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 100, false);
INSERT INTO ROOMS VALUES(4, 'Suite', 2, 2.0, true, 'City View', false, '2023-01-01', '2023-01-02', 200, false);
INSERT INTO ROOMS VALUES(5, 'Double', 1, 1.5, false, 'Garden View', false, '2023-01-01', '2023-01-02', 110, false);
INSERT INTO ROOMS VALUES(6, 'Twin', 2, 1.0, true, 'Sea View', false, '2023-01-01', '2023-01-02', 90, false);

-- INSERT INTO ROOMS VALUES(7, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 80, false);
-- INSERT INTO ROOMS VALUES(8, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 85, false);
-- INSERT INTO ROOMS VALUES(9, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 90, false);
-- INSERT INTO ROOMS VALUES(10, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 95, false);

DROP TABLE IF EXISTS BOOKINGS;
CREATE TABLE BOOKINGS(
    BOOKING_REF INT PRIMARY KEY,
    NAME VARCHAR(255),
    EMAIL VARCHAR(255),
    PHONE VARCHAR(255),
    BOOKING_NAME VARCHAR(255),
    ID INT,  
    TYPE VARCHAR(255),
    BEDS INT,
    BED_SIZE DOUBLE,
    BALCONY BOOLEAN,
    VIEW VARCHAR(255),
    ACCESSIBLE BOOLEAN,
    CHECK_IN_DATE DATE,
    CHECK_OUT_DATE DATE,
    PRICE DOUBLE,
    BOOKED BOOLEAN
);

<<<<<<< HEAD
INSERT INTO BOOKINGS VALUES(1, 'test', 'test', '1234567890', 'Booking 1', 2, 'Double', 1, 1.5, true, 'Garden View', false, '2023-06-12', '2023-06-14', 120, true);

DROP TABLE IF EXISTS ADMIN;

CREATE TABLE ADMIN(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(255) UNIQUE,
    PASSWORD VARCHAR(255)
);

INSERT INTO ADMIN VALUES (1, 'admin', 'admin');
=======
INSERT INTO BOOKINGS VALUES(1, 'H1-123085708491237', 'test', '1234567890', 'Booking 1', 2, 'Double', 1, 1.5, true, 'Garden View', false, '2023-06-12', '2023-06-14', 120, true);
INSERT INTO BOOKINGS VALUES(2, 'H1-327015010365012', 'John Smith', '1234567891', 'Booking 2', 2, 'Single', 1, 1.5, true, 'Sea View', false, '2023-05-30', '2023-06-03', 120, true);
>>>>>>> 2134cff215f587b29f1677e26d1e107d9888284a
