-- Insert Users
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

-- Insert Categories
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

-- Insert Products
INSERT INTO Product (Name, Description, StockQuantity, Price, CategoryId) VALUES
('iPhone 15', 'Latest iPhone model', 10, 79999.00, 2),
('MacBook Air', 'M2 chip laptop', 5, 129999.00, 3),
('Wireless Mouse', 'Ergonomic wireless mouse', 20, 1499.00, 4),
('T-Shirt', 'Cotton round neck t-shirt', 50, 499.00, 6),
('Jeans', 'Slim fit blue jeans', 30, 1299.00, 6),
('Blender', 'Multi-speed kitchen blender', 15, 2999.00, 10),
('Smartwatch', 'Latest smartwatch with fitness tracking', 8, 19999.00, 4),
('Tablet', '10-inch tablet with 4GB RAM', 6, 15999.00, 2),
('Headphones', 'Noise-cancelling over-ear headphones', 12, 9999.00, 4),
('Sneakers', 'Running shoes with breathable fabric', 25, 3499.00, 7);

-- Insert Images
INSERT INTO Image (ProductId, URL) VALUES
(1, 'iphone15.jpg'),
(2, 'macbook_air.jpg'),
(3, 'mouse.jpg'),
(4, 'tshirt.jpg'),
(5, 'jeans.jpg'),
(6, 'blender.jpg'),
(7, 'smartwatch.jpg');

-- Insert Shipping Addresses
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

-- Insert Orders
INSERT INTO Orders (ShopperId, ShippingAddressId, Status) VALUES
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

-- Insert Order Items
INSERT INTO OrderItem (OrderId, ProductId, Quantity, PriceAtPurchase, Status) VALUES
(1, 1, 1, 79999.00, 'Placed'),
(2, 2, 1, 129999.00, 'Accepted'),
(3, 3, 2, 1499.00, 'Shipped'),
(4, 4, 3, 499.00, 'Delivered'),
(5, 5, 1, 1299.00, 'Returned'),
(6, 6, 2, 2999.00, 'Replaced'),
(7, 7, 1, 19999.00, 'Cancelled'),
(8, 8, 1, 15999.00, 'Placed'),
(9, 9, 2, 9999.00, 'Accepted'),
(10, 10, 1, 3499.00, 'Shipped');
