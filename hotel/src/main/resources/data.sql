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
    BOOKED BOOLEAN,
    HOTEL VARCHAR(255)
);


INSERT INTO ROOMS VALUES(1, 'Single', 1, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 80, false, 'Hotel1');
INSERT INTO ROOMS VALUES(2, 'Single', 1, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 85, false, 'Hotel1');
INSERT INTO ROOMS VALUES(3, 'Single', 1, 1.0, false, 'City View', false, '2023-01-01', '2023-01-02', 90, false, 'Hotel1');
INSERT INTO ROOMS VALUES(4, 'Single', 1, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(5, 'Single', 1, 1.0, false, 'Garden View', true, '2023-01-01', '2023-01-02', 100, false, 'Hotel1');
INSERT INTO ROOMS VALUES(6, 'Single', 1, 1.0, false, 'City View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(7, 'Single', 1, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 90, false, 'Hotel1');
INSERT INTO ROOMS VALUES(8, 'Single', 1, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 85, false, 'Hotel1');
INSERT INTO ROOMS VALUES(9, 'Single', 1, 1.0, false, 'City View', false, '2023-01-01', '2023-01-02', 80, false, 'Hotel1');
INSERT INTO ROOMS VALUES(10, 'Single', 1, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(11, 'Single', 1, 1.0, false, 'Garden View', true, '2023-01-01', '2023-01-02', 100, false, 'Hotel1');
INSERT INTO ROOMS VALUES(12, 'Single', 1, 1.0, false, 'City View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(13, 'Single', 1, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 90, false, 'Hotel1');
INSERT INTO ROOMS VALUES(14, 'Single', 1, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 85, false, 'Hotel1');
INSERT INTO ROOMS VALUES(15, 'Single', 1, 1.0, false, 'City View', false, '2023-01-01', '2023-01-02', 80, false, 'Hotel1');
INSERT INTO ROOMS VALUES(16, 'Single', 1, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(17, 'Single', 1, 1.0, false, 'Garden View', true, '2023-01-01', '2023-01-02', 100, false, 'Hotel1');
INSERT INTO ROOMS VALUES(18, 'Single', 1, 1.0, false, 'City View', true, '2023-01-01', '2023-01-02', 95, false, 'Hotel1');
INSERT INTO ROOMS VALUES(19, 'Single', 1, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 90, false, 'Hotel1');
INSERT INTO ROOMS VALUES(20, 'Single', 1, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 85, false, 'Hotel1');

INSERT INTO ROOMS VALUES(21, 'Double', 1, 1.5, false, 'Sea View', false, '2023-01-01', '2023-01-02', 120, false, 'Hotel1');
INSERT INTO ROOMS VALUES(22, 'Double', 1, 1.5, false, 'Garden View', false, '2023-01-01', '2023-01-02', 125, false, 'Hotel1');
INSERT INTO ROOMS VALUES(23, 'Double', 1, 1.5, false, 'City View', false, '2023-01-01', '2023-01-02', 130, false, 'Hotel1');
INSERT INTO ROOMS VALUES(24, 'Double', 1, 1.5, false, 'Sea View', true, '2023-01-01', '2023-01-02', 135, false, 'Hotel1');
INSERT INTO ROOMS VALUES(25, 'Double', 1, 1.5, false, 'Garden View', true, '2023-01-01', '2023-01-02', 140, false, 'Hotel1');
INSERT INTO ROOMS VALUES(26, 'Double', 1, 1.5, false, 'City View', true, '2023-01-01', '2023-01-02', 145, false, 'Hotel1');
INSERT INTO ROOMS VALUES(27, 'Double', 1, 1.5, false, 'Sea View', false, '2023-01-01', '2023-01-02', 130, false, 'Hotel1');
INSERT INTO ROOMS VALUES(28, 'Double', 1, 1.5, false, 'Garden View', false, '2023-01-01', '2023-01-02', 125, false, 'Hotel1');
INSERT INTO ROOMS VALUES(29, 'Double', 1, 1.5, false, 'City View', false, '2023-01-01', '2023-01-02', 120, false, 'Hotel1');
INSERT INTO ROOMS VALUES(30, 'Double', 1, 1.5, false, 'Sea View', true, '2023-01-01', '2023-01-02', 135, false, 'Hotel1');
INSERT INTO ROOMS VALUES(31, 'Double', 1, 1.5, false, 'Garden View', true, '2023-01-01', '2023-01-02', 140, false, 'Hotel1');
INSERT INTO ROOMS VALUES(32, 'Double', 1, 1.5, false, 'City View', true, '2023-01-01', '2023-01-02', 145, false, 'Hotel1');
INSERT INTO ROOMS VALUES(33, 'Double', 1, 1.5, false, 'Sea View', false, '2023-01-01', '2023-01-02', 130, false, 'Hotel1');
INSERT INTO ROOMS VALUES(34, 'Double', 1, 1.5, false, 'Garden View', false, '2023-01-01', '2023-01-02', 125, false, 'Hotel1');
INSERT INTO ROOMS VALUES(35, 'Double', 1, 1.5, false, 'City View', false, '2023-01-01', '2023-01-02', 120, false, 'Hotel1');

INSERT INTO ROOMS VALUES(36, 'Twin', 2, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 100, false, 'Hotel1');
INSERT INTO ROOMS VALUES(37, 'Twin', 2, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 105, false, 'Hotel1');
INSERT INTO ROOMS VALUES(38, 'Twin', 2, 1.0, false, 'City View', false, '2023-01-01', '2023-01-02', 110, false, 'Hotel1');
INSERT INTO ROOMS VALUES(39, 'Twin', 2, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 115, false, 'Hotel1');
INSERT INTO ROOMS VALUES(40, 'Twin', 2, 1.0, false, 'Garden View', true, '2023-01-01', '2023-01-02', 120, false, 'Hotel1');
INSERT INTO ROOMS VALUES(41, 'Twin', 2, 1.0, false, 'City View', true, '2023-01-01', '2023-01-02', 125, false, 'Hotel1');
INSERT INTO ROOMS VALUES(42, 'Twin', 2, 1.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 110, false, 'Hotel1');
INSERT INTO ROOMS VALUES(43, 'Twin', 2, 1.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 105, false, 'Hotel1');
INSERT INTO ROOMS VALUES(44, 'Twin', 2, 1.0, false, 'City View', false, '2023-01-01', '2023-01-02', 100, false, 'Hotel1');
INSERT INTO ROOMS VALUES(45, 'Twin', 2, 1.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 115, false, 'Hotel1');

INSERT INTO ROOMS VALUES(46, 'Suite', 2, 2.0, false, 'Sea View', false, '2023-01-01', '2023-01-02', 200, false, 'Hotel1');
INSERT INTO ROOMS VALUES(47, 'Suite', 2, 2.0, false, 'Garden View', false, '2023-01-01', '2023-01-02', 210, false, 'Hotel1');
INSERT INTO ROOMS VALUES(48, 'Suite', 2, 2.0, false, 'City View', false, '2023-01-01', '2023-01-02', 220, false, 'Hotel1');
INSERT INTO ROOMS VALUES(49, 'Suite', 2, 2.0, false, 'Sea View', true, '2023-01-01', '2023-01-02', 230, false, 'Hotel1');
INSERT INTO ROOMS VALUES(50, 'Suite', 2, 2.0, false, 'Garden View', true, '2023-01-01', '2023-01-02', 240, false, 'Hotel1');


DROP TABLE IF EXISTS BOOKINGS;
CREATE TABLE BOOKINGS(
    BOOKING_REF INT PRIMARY KEY,
    NAME VARCHAR(255),
    EMAIL VARCHAR(255),
    PHONE VARCHAR(255),
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
    BOOKED BOOLEAN,
    HOTEL VARCHAR(255)
);


DROP TABLE IF EXISTS ADMIN;


CREATE TABLE ADMIN(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(255) UNIQUE,
    PASSWORD VARCHAR(255),
    HOTEL VARCHAR(255)
);

INSERT INTO ADMIN VALUES (1, 'admin', 'admin', 'Hotel1');
