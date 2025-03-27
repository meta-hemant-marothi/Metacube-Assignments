SHOW TABLES;

ALTER TABLE ProductCategory DROP FOREIGN KEY productcategory_ibfk_1;
ALTER TABLE Image DROP FOREIGN KEY image_ibfk_1;
ALTER TABLE OrderItem DROP FOREIGN KEY orderitem_ibfk_2;

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

ALTER TABLE ProductCategory ADD FOREIGN KEY (ProductId) REFERENCES Product(ProductId);
ALTER TABLE Image ADD FOREIGN KEY(ProductId) REFERENCES Product(ProductId);
ALTER TABLE OrderItem ADD FOREIGN KEY(ProductId) REFERENCES Product(ProductId);

SHOW CREATE TABLE ProductCategory;