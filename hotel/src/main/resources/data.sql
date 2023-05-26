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

INSERT INTO ROOMS VALUES(1, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 80, false);
INSERT INTO ROOMS VALUES(2, 'Double', 1, 1.5, true, 'Garden View', false, '2023-06-10', '2023-06-15', 120, false);
INSERT INTO ROOMS VALUES(3, 'Twin', 2, 1.0, false, 'Sea View', true, '2023-07-01', '2023-07-05', 100, false);
INSERT INTO ROOMS VALUES(4, 'Suite', 2, 2.0, true, 'City View', false, '2023-07-20', '2023-07-25', 200, false);
INSERT INTO ROOMS VALUES(5, 'Double', 1, 1.5, false, 'Garden View', false, '2023-08-01', '2023-08-10', 110, false);
INSERT INTO ROOMS VALUES(6, 'Twin', 2, 1.0, true, 'Sea View', false, '2023-08-15', '2023-08-20', 90, false);

INSERT INTO ROOMS VALUES(7, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 80, false);
INSERT INTO ROOMS VALUES(8, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 85, false);
INSERT INTO ROOMS VALUES(9, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 90, false);
INSERT INTO ROOMS VALUES(10, 'Single', 1, 1.0, false, 'Sea View', false, '2023-06-01', '2023-06-03', 95, false);

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

INSERT INTO BOOKINGS VALUES(1, 'test', 'test', '1234567890', 'Booking 1', 2, 'Double', 1, 1.5, true, 'Garden View', false, '2023-06-12', '2023-06-14', 120, true);

