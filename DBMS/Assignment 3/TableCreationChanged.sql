-- DROP and CREATE Database
DROP DATABASE IF EXISTS StoreFront;
CREATE DATABASE StoreFront;
USE StoreFront;

-- User Table
CREATE TABLE User (
    UserId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    Role ENUM('Shopper', 'Administrator') NOT NULL,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- Category Table
CREATE TABLE Category (
    CategoryId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    ParentCategoryId INT,
    FOREIGN KEY (ParentCategoryId) REFERENCES Category(CategoryId) ON DELETE SET NULL,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- Product Table
CREATE TABLE Product (
    ProductId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100),
    StockQuantity INT DEFAULT 0 CHECK (StockQuantity >= 0),
    Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0),
    ActiveStatus BOOLEAN DEFAULT TRUE,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- ProductCategory Table (Many-to-Many Relationship between Product and Category)
CREATE TABLE ProductCategory (
    ProductCategoryId INT PRIMARY KEY AUTO_INCREMENT,
    ProductId INT NOT NULL,
    CategoryId INT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE,
    FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId) ON DELETE CASCADE
);

-- Image Table
CREATE TABLE Image (
    ImageId INT PRIMARY KEY AUTO_INCREMENT,
    ProductId INT NOT NULL,
    URL VARCHAR(100) NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE
);

-- ShippingAddress Table
CREATE TABLE ShippingAddress (
    ShippingAddressId INT PRIMARY KEY AUTO_INCREMENT,
    ShopperId INT NOT NULL,
    ZipCode VARCHAR(10) NOT NULL,
    State VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    AddressDesc TEXT,
    FOREIGN KEY (ShopperId) REFERENCES User(UserId)
);

-- Orders Table
CREATE TABLE Orders (
    OrderId INT PRIMARY KEY AUTO_INCREMENT,
    ShopperId INT NOT NULL,
    ShippingAddressId INT NOT NULL,
    OrderTimestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Placed', 'Accepted', 'Shipped', 'Delivered', 'Returned', 'Replaced', 'Cancelled') NOT NULL,
    FOREIGN KEY (ShopperId) REFERENCES User(UserId),
    FOREIGN KEY (ShippingAddressId) REFERENCES ShippingAddress(ShippingAddressId)
);

-- OrderItem Table
CREATE TABLE OrderItem (
    OrderItemId INT PRIMARY KEY AUTO_INCREMENT,
    OrderId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT DEFAULT 1 CHECK (Quantity > 0),
    Status ENUM('Placed', 'Accepted', 'Shipped', 'Delivered', 'Returned', 'Replaced', 'Cancelled') NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) ON DELETE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);

-- State, City, ZipCode Tables for Address Lookup (Assignment 4)
CREATE TABLE State (
    StateId INT PRIMARY KEY AUTO_INCREMENT,
    StateName VARCHAR(50) NOT NULL
);

CREATE TABLE City (
    CityId INT PRIMARY KEY AUTO_INCREMENT,
    CityName VARCHAR(50) NOT NULL,
    StateId INT NOT NULL,
    FOREIGN KEY (StateId) REFERENCES State(StateId)
);

CREATE TABLE ZipCode (
    ZipCodeId INT PRIMARY KEY AUTO_INCREMENT,
    ZipCode VARCHAR(10) NOT NULL,
    CityId INT NOT NULL,
    FOREIGN KEY (CityId) REFERENCES City(CityId)
);


ALTER TABLE ProductCategory DROP FOREIGN KEY productcategory_ibfk_1;
ALTER TABLE Image DROP FOREIGN KEY image_ibfk_1;
ALTER TABLE OrderItem DROP FOREIGN KEY orderitem_ibfk_2;
DROP TABLE Product;