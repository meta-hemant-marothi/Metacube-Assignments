SHOW TABLES;
DROP TABLE Image;
DROP TABLE OrderItem;
DROP TABLE Product;

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