CREATE DATABASE  IF NOT EXISTS `collabyyc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `collabyyc`;

Drop Table if exists Items;
Drop Table if exists Vendors;


Create table Vendors (
    vendorID int(5),
    vendorName VARCHAR(40) NOT NULL,
    vendorEmail VARCHAR(40) NOT NULL,
    vendorPhone varchar(10) NOT NULL,
    PRIMARY KEY (vendorID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create table Items (
    itemID int(8),
    vendorID int(5),
    nameProducts VARCHAR(30) NOT NULL,
    price decimal(4,2) NOT NULL,
    quantity int(3) NOT NULL,
    category VARCHAR(20) NOT NULL,
    PRIMARY KEY (ItemID),
    FOREIGN KEY (VendorID) REFERENCES VENDORS (VendorID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO Vendors (VendorID, Vendorname, VendorEmail, VendorPhone)
VALUES(00001, 'Kindred Clothing', 'KINDREDCLOTHINGCALGARY@GMAIL.com', 5872254472);

INSERT INTO Vendors (VendorID, VendorName, VendorEmail, VendorPhone)
VALUES(00002, 'Erin Baer Ceramics', 'erin@erinbaerceramics.com', 4033740619);

INSERT INTO ITEMS (ItemID, VendorID, nameproducts, Price, Quantity, Category)
VALUES(00000001, 00001, 'Polka Dot Scrunchie', 10.00, 1, 'Accessories');

INSERT INTO ITEMS (ItemID, VendorID, nameproducts, Price, Quantity, Category)
VALUES(00000002, 00002, 'Glazed Bowl', 65.00, 1, 'Ceramics');