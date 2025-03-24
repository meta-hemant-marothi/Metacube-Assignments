DROP DATABASE IF EXISTS StoreFront;
CREATE DATABASE StoreFront;
USE StoreFront;

CREATE TABLE User (
    UserId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role ENUM("Shopper", "Administrator") NOT NULL
);

CREATE TABLE Category (
    CategoryId INT PRIMARY KEY AUTO_INCREMENT,
    ParentCategoryId INT,
    Name VARCHAR(100) NOT NULL,
    FOREIGN KEY (ParentCategoryId) REFERENCES Category(CategoryId) ON DELETE CASCADE
);

CREATE TABLE Product (
    ProductId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(255),
    StockQuantity INT UNSIGNED DEFAULT 0,
    Price DECIMAL(10,2) NOT NULL,
    CategoryId INT,
    FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId) ON DELETE SET NULL
);

CREATE TABLE Image (
    ImageId INT PRIMARY KEY AUTO_INCREMENT,
    ProductId INT,
    URL VARCHAR(255) NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE
);

CREATE TABLE ShippingAddress (
    ShippingAddressId INT PRIMARY KEY AUTO_INCREMENT,
    ShopperId INT,
    ZipCode VARCHAR(10) NOT NULL,
    State VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    AddressDesc TEXT,
    FOREIGN KEY (ShopperId) REFERENCES User(UserId) ON DELETE CASCADE
);

CREATE TABLE Orders (
    OrderId INT PRIMARY KEY AUTO_INCREMENT,
    ShopperId INT,
    ShippingAddressId INT,
    OrderTimestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Status ENUM("Placed", "Accepted", "Shipped", "Delivered", "Returned", "Replaced", "Cancelled") DEFAULT "Placed",
    FOREIGN KEY (ShopperId) REFERENCES User(UserId) ON DELETE CASCADE,
    FOREIGN KEY (ShippingAddressId) REFERENCES ShippingAddress(ShippingAddressId) ON DELETE SET NULL
);

CREATE TABLE OrderItem (
    OrderItemId INT PRIMARY KEY AUTO_INCREMENT,
    OrderId INT,
    ProductId INT,
    Quantity INT UNSIGNED NOT NULL DEFAULT 1,
    PriceAtPurchase DECIMAL(10,2) NOT NULL,
    Status ENUM("Placed", "Accepted", "Shipped", "Delivered", "Returned", "Replaced", "Cancelled") DEFAULT "Placed",
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) ON DELETE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE
);


