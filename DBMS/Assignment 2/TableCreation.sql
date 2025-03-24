DROP DATABASE StoreFront;
CREATE DATABASE StoreFront;
USE StoreFront;

CREATE TABLE User(
	Name VARCHAR(50) NOT NULL,
	UserId INT PRIMARY KEY AUTO_INCREMENT,
	Email VARCHAR(100) NOT NULL UNIQUE,
	Password VARCHAR(100) NOT NULL,
	Role ENUM("Shopper", "Administrator") NOT NULL
);

CREATE TABLE Category(
	CategoryId INT PRIMARY KEY AUTO_INCREMENT,
	ParentCategoryId INT,
	Name VARCHAR(100) NOT NULL,
	FOREIGN KEY(ParentCategoryId) REFERENCES Category(CategoryId) ON DELETE SET NULL
);

CREATE TABLE Product(
	Name VARCHAR(50) NOT NULL,
	ProductId INT PRIMARY KEY AUTO_INCREMENT,
	Description VARCHAR(100),
	StockQuantity INT Default 0,
	Price INT NOT NULL,
	CategoryId INT,
	FOREIGN KEY(CategoryId) REFERENCES Category(CategoryId)
);

CREATE TABLE Image(
	ImageId INT PRIMARY KEY AUTO_INCREMENT,
	ProductID INT,
	URL VARCHAR(100),
	FOREIGN KEY(ProductId) REFERENCES Product(ProductId)
);

CREATE TABLE ShippingAddress(
	ShippingAddressId INT PRIMARY KEY AUTO_INCREMENT,
	ZipCode INT NOT NULL,
	State VARCHAR(50) NOT NULL,
	City VARCHAR(50) NOT NULL,
	AddressDesc TEXT,
	ShopperId INT,
	FOREIGN KEY(ShopperId) REFERENCES User(UserId)
);

CREATE TABLE Orders(
	OrderId INT PRIMARY KEY AUTO_INCREMENT,
	ShippingAddressId INT,
	OrderTimestamp DATETIME NOT NULL Default CURRENT_TIMESTAMP,
	Status ENUM("Placed", "Accepted", "Shipped", "Delivered", "Returned", "Replaced", "Cancelled"),
	FOREIGN KEY(ShippingAddressId) REFERENCES ShippingAddress(ShippingAddressId)
);

CREATE TABLE OrderItem(
   OrderItemId INT PRIMARY KEY AUTO_INCREMENT,
   OrderId INT,
   ProductId INT,
   Quantity INT NOT NULL Default 1,
   Status ENUM("Placed", "Accepted", "Shipped", "Delivered", "Returned", "Replaced", "Cancelled"),
   FOREIGN KEY (OrderId) REFERENCES Orders(OrderId),
   FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);