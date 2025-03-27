-- Insert Sample Data: User Table
INSERT INTO User (Name, Email, Password, Role) VALUES
('John Doe', 'john@example.com', 'hashedpassword1', 'Shopper'),
('Alice Smith', 'alice@example.com', 'hashedpassword2', 'Administrator'),
('Bob Brown', 'bob@example.com', 'hashedpassword3', 'Shopper'),
('Eve Johnson', 'eve@example.com', 'hashedpassword4', 'Shopper'),
('Charlie Davis', 'charlie@example.com', 'hashedpassword5', 'Administrator'),
('David Wilson', 'david@example.com', 'hashedpassword6', 'Shopper'),
('Grace Moore', 'grace@example.com', 'hashedpassword7', 'Shopper'),
('Hank Taylor', 'hank@example.com', 'hashedpassword8', 'Shopper'),
('Ivy Anderson', 'ivy@example.com', 'hashedpassword9', 'Administrator'),
('Jake Thomas', 'jake@example.com', 'hashedpassword10', 'Shopper');

-- Insert Sample Data: Category Table
INSERT INTO Category (CategoryId, ParentCategoryId, Name) VALUES
(1, NULL, 'Electronics'),
(2, 1, 'Mobile'),
(3, 1, 'Laptop'),
(4, 1, 'Accessories'),
(5, NULL, 'Clothing'),
(6, 5, 'Men'),
(7, 5, 'Women'),
(8, 5, 'Kids'),
(9, NULL, 'Home Appliances'),
(10, 9, 'Kitchen');

-- Insert Sample Data: Product Table
INSERT INTO Product (Name, Description, StockQuantity, Price, ActiveStatus) VALUES
('iPhone 15', 'Latest iPhone model', 10, 79999.00, TRUE),
('MacBook Air', 'M2 chip laptop', 5, 129999.00, TRUE),
('Wireless Mouse', 'Ergonomic wireless mouse', 20, 1499.00, TRUE),
('T-Shirt', 'Cotton round neck t-shirt', 50, 499.00, TRUE),
('Jeans', 'Slim fit blue jeans', 30, 1299.00, TRUE),
('Blender', 'Multi-speed kitchen blender', 15, 2999.00, TRUE),
('Smartwatch', 'Latest smartwatch with fitness tracking', 8, 19999.00, TRUE),
('Tablet', '10-inch tablet with 4GB RAM', 6, 15999.00, TRUE),
('Headphones', 'Noise-cancelling over-ear headphones', 12, 9999.00, TRUE),
('Sneakers', 'Running shoes with breathable fabric', 25, 3499.00, TRUE);

-- Insert Sample Data: ProductCategory Table
INSERT INTO ProductCategory (ProductId, CategoryId) VALUES
(1, 2), -- iPhone 15 in Mobile
(2, 3), -- MacBook Air in Laptop
(3, 4), -- Wireless Mouse in Accessories
(4, 6), -- T-Shirt in Men
(5, 6), -- Jeans in Men
(6, 10), -- Blender in Kitchen
(7, 4), -- Smartwatch in Accessories
(8, 2), -- Tablet in Mobile
(9, 4), -- Headphones in Accessories
(10, 7), -- Sneakers in Women
(1, 4);

-- Insert Sample Data: Image Table
INSERT INTO Image (ProductId, URL) VALUES
(1, 'iphone15.jpg'),
(2, 'macbook_air.jpg'),
(3, 'mouse.jpg'),
(4, 'tshirt.jpg'),
(5, 'jeans.jpg'),
(6, 'blender.jpg'),
(7, 'smartwatch.jpg');

-- Insert Sample Data: ShippingAddress Table
INSERT INTO ShippingAddress (ShopperId, ZipCode, State, City, AddressDesc) VALUES
(1, '302001', 'Rajasthan', 'Jaipur', '123, ABC Street'),
(3, '560001', 'Karnataka', 'Bangalore', '456, XYZ Road'),
(4, '110001', 'Delhi', 'New Delhi', '789, DEF Colony'),
(6, '400001', 'Maharashtra', 'Mumbai', '101, GHI Lane'),
(7, '600001', 'Tamil Nadu', 'Chennai', '202, JKL Street'),
(8, '700001', 'West Bengal', 'Kolkata', '303, MNO Road'),
(9, '500001', 'Telangana', 'Hyderabad', '404, PQR Avenue'),
(10, '411001', 'Maharashtra', 'Pune', '505, STU Nagar'),
(1, '380001', 'Gujarat', 'Ahmedabad', '606, VWX Road'),
(3, '751001', 'Odisha', 'Bhubaneswar', '707, YZA Colony');

-- Insert Sample Data: Orders Table
INSERT INTO Orders (ShopperId, ShippingAddressId, Status, OrderTimestamp) VALUES
(1, 1, 'Placed'),
(3, 2, 'Accepted'),
(4, 3, 'Shipped'),
(6, 4, 'Delivered'),
(7, 5, 'Returned'),
(8, 6, 'Replaced'),
(9, 7, 'Cancelled'),
(10, 8, 'Placed'),
(1, 9, 'Accepted'),
(3, 10, 'Shipped');

-- Insert Sample Data: OrderItem Table
INSERT INTO OrderItem (ProductId, Quantity, Status) VALUES
(1, 1, 'Placed'),
(2, 1, 'Accepted'),
(3, 1, 'Shipped'),
(4, 30, 'Delivered'),
(5, 1, 'Returned'),
(4, 9, 'Delivered'),
(4, 12, 'Delivered'),
(6, 2, 'Replaced'),
(7, 1, 'Cancelled'),
(8, 1, 'Placed'),
(9, 2, 'Accepted'),
(10, 1, 'Shipped');

-- Insert Sample Data: State Table
INSERT INTO State (StateName) VALUES 
('Rajasthan'), ('Karnataka'), ('Delhi'), ('Maharashtra'),
('Tamil Nadu'), ('West Bengal'), ('Telangana'), 
('Gujarat'), ('Odisha');

-- Insert Sample Data: City Table
INSERT INTO City (CityName, StateId) VALUES
('Jaipur', 1), ('Bangalore', 2), ('New Delhi', 3),
('Mumbai', 4), ('Chennai', 5), ('Kolkata', 6),
('Hyderabad', 7), ('Ahmedabad', 8), ('Bhubaneswar', 9);

-- Insert Sample Data: ZipCode Table
INSERT INTO ZipCode (ZipCode, CityId) VALUES
('302001', 1), ('560001', 2), ('110001', 3),
('400001', 4), ('600001', 5), ('700001', 6),
('500001', 7), ('380001', 8), ('751001', 9);