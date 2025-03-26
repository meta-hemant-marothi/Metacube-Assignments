SHOW TABLES;

-- ALTER TABLE ProductCategory DROP FOREIGN KEY productcategory_ibfk_1;
-- ALTER TABLE Image DROP FOREIGN KEY image_ibfk_1;
-- ALTER TABLE OrderItem DROP FOREIGN KEY orderitem_ibfk_2;
DROP TABLE ProductCategory;
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

CREATE TABLE OrderItem (
    OrderItemId INT PRIMARY KEY AUTO_INCREMENT,
    OrderId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT DEFAULT 1 CHECK (Quantity > 0),
    Status ENUM('Placed', 'Accepted', 'Shipped', 'Delivered', 'Returned', 'Replaced', 'Cancelled') NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) ON DELETE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE
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

-- ALTER TABLE ProductCategory ADD FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE;
-- ALTER TABLE Image DROP FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE;
-- ALTER TABLE OrderItem DROP FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE;