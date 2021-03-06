CREATE DATABASE  IF NOT EXISTS `collabyyc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `collabyyc`;

Drop Table if exists Items;
Drop Table if exists Vendors;
Drop Table if exists Users;
drop table if exists sale;
drop table if exists EmployeeAccounts;

Create table Vendors (
    vendorID int(5),
    vendorName VARCHAR(40) NOT NULL,
    vendorEmail VARCHAR(40) NOT NULL,
    vendorPhone varchar(10) NOT NULL,
    PRIMARY KEY (vendorID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create table Items (
    itemID int(8) auto_increment,
    vendorID int(5),
    nameProducts VARCHAR(30) NOT NULL,
    price decimal(4,2) NOT NULL,
    quantity int(3) NOT NULL,
    category VARCHAR(20) NOT NULL,
    PRIMARY KEY (ItemID),
    FOREIGN KEY (VendorID) REFERENCES VENDORS (VendorID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create table Sale (
    transactionID int(10) auto_increment,
    customerID int(5),
    paymentDate datetime default current_timestamp NOT NULL,
    saleAmount decimal(4,2) NOT NULL,
    payVendorAmount decimal(4,2) NOT NULL,
    soldItems VARCHAR (10000) NOT NULL,
    shippingSentDate DATE,
    shippingAddress VARCHAR(60),
    pickupDate DATE,
    PRIMARY KEY (transactionID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create table EmployeeAccounts (
    employeeID int(5),
    employeePassword VARCHAR (20) NOT NULL,
    employeeName VARCHAR(50) NOT NULL,
    employeeEmail VARCHAR(50) NOT NULL,
    employeePhone BIGINT NOT NULL,
    PRIMARY KEY (employeeID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


Create table Users (
    userName VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL, 
    PRIMARY KEY (userName)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO Vendors (VendorID, Vendorname, VendorEmail, VendorPhone)
VALUES(00001, 'Kindred Clothing', 'KINDREDCLOTHINGCALGARY@GMAIL.com', 5872254472);

INSERT INTO Vendors (VendorID, VendorName, VendorEmail, VendorPhone)
VALUES(00002, 'Erin Baer Ceramics', 'erin@erinbaerceramics.com', 4033740619);

INSERT INTO ITEMS (ItemID, VendorID, nameproducts, Price, Quantity, Category)
VALUES(00000001, 00001, 'Polka Dot Scrunchie', 10.00, 1, 'Accessories');

INSERT INTO ITEMS (ItemID, VendorID, nameproducts, Price, Quantity, Category)
VALUES(00000002, 00002, 'Glazed Bowl', 65.00, 1, 'Ceramics');

INSERT INTO EmployeeAccounts (employeeID, employeePassword, employeeName, employeeEmail, employeePhone)
VALUES(12345, 'password', 'Employee', 'employee@gmail.com', 1234567890);

INSERT INTO Users (userName, password)
VALUES('user01', 'password');

INSERT INTO sale (transactionID, saleAmount, payVendorAmount, soldItems)
VALUES(1, 75.25, 42.22, 'Cookies, Scrunchies');

INSERT INTO sale (transactionID, saleAmount, payVendorAmount, soldItems)
VALUES(2, 22.45, 7.77, 'Candles, Pickles');
