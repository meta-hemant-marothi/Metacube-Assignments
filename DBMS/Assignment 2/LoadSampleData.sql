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
('iPhone 15', 'Latest iPhone model', 10, 79999, 2),
('MacBook Air', 'M2 chip laptop', 5, 129999, 3),
('Wireless Mouse', 'Ergonomic wireless mouse', 20, 1499, 4),
('T-Shirt', 'Cotton round neck t-shirt', 50, 499, 6),
('Jeans', 'Slim fit blue jeans', 30, 1299, 6),
('Blender', 'Multi-speed kitchen blender', 15, 2999, 10),
('Smartwatch', 'Latest smartwatch with fitness tracking', 8, 19999, 4),
('Tablet', '10-inch tablet with 4GB RAM', 6, 15999, 2),
('Headphones', 'Noise-cancelling over-ear headphones', 12, 9999, 4),
('Sneakers', 'Running shoes with breathable fabric', 25, 3499, 7);

-- Insert Images
INSERT INTO Image (ProductId, URL) VALUES
(1, 'iphone15.jpg'),
(2, 'macbook_air.jpg'),
(3, 'mouse.jpg'),
(4, 'tshirt.jpg'),
(5, 'jeans.jpg'),
(6, 'blender.jpg'),
(7, 'smartwatch.jpg'),
(8, 'tablet.jpg'),
(9, 'headphones.jpg'),
(10, 'sneakers.jpg');

-- Insert Shipping Addresses
INSERT INTO ShippingAddress (ZipCode, State, City, AddressDesc, ShopperId) VALUES
('302001', 'Rajasthan', 'Jaipur', '123, ABC Street', 1),
('560001', 'Karnataka', 'Bangalore', '456, XYZ Road', 3),
('110001', 'Delhi', 'New Delhi', '789, DEF Colony', 4),
('400001', 'Maharashtra', 'Mumbai', '101, GHI Lane', 6),
('600001', 'Tamil Nadu', 'Chennai', '202, JKL Street', 7),
('700001', 'West Bengal', 'Kolkata', '303, MNO Road', 8),
('500001', 'Telangana', 'Hyderabad', '404, PQR Avenue', 9),
('411001', 'Maharashtra', 'Pune', '505, STU Nagar', 10),
('380001', 'Gujarat', 'Ahmedabad', '606, VWX Road', 1),
('751001', 'Odisha', 'Bhubaneswar', '707, YZA Colony', 3);

-- Insert Orders
INSERT INTO Orders (ShippingAddressId, Status) VALUES
(1, 'Placed'),
(2, 'Accepted'),
(3, 'Shipped'),
(4, 'Delivered'),
(5, 'Returned'),
(6, 'Replaced'),
(7, 'Cancelled'),
(8, 'Placed'),
(9, 'Accepted'),
(10, 'Shipped');

-- Insert Order Items
INSERT INTO OrderItem (OrderId, ProductId, Quantity, Status) VALUES
(1, 1, 1, 'Placed'),
(2, 2, 1, 'Accepted'),
(3, 3, 2, 'Shipped'),
(4, 4, 3, 'Delivered'),
(5, 5, 1, 'Returned'),
(6, 6, 2, 'Replaced'),
(7, 7, 1, 'Cancelled'),
(8, 8, 1, 'Placed'),
(9, 9, 2, 'Accepted'),
(10, 10, 1, 'Shipped');
