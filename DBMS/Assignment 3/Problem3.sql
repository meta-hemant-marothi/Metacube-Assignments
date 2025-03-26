-- ASSIGNMENT 3: Queries
SET SQL_SAFE_UPDATES = 0;

-- Shopper’s orders in the last 30 days
SELECT u.UserId, u.Name, COUNT(o.OrderId) AS OrderCount
FROM User u
JOIN Orders o ON u.UserId = o.ShopperId
WHERE o.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
GROUP BY u.UserId, u.Name;

-- Top 10 Shoppers by revenue in the last 30 days
SELECT u.UserId, u.Name, SUM(oi.Quantity * p.Price) AS TotalRevenue
FROM User u
JOIN Orders o ON u.UserId = o.ShopperId
JOIN OrderItem oi ON o.OrderId = oi.OrderId
JOIN Product p ON oi.ProductId = p.ProductId
WHERE o.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
GROUP BY u.UserId, u.Name
ORDER BY TotalRevenue DESC
LIMIT 10;

-- Top 20 products ordered most in the last 60 days
SELECT p.ProductId, p.Name, SUM(oi.Quantity) AS TotalQuantity
FROM OrderItem oi
JOIN Product p ON oi.ProductId = p.ProductId
JOIN Orders o ON oi.OrderId = o.OrderId
WHERE o.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL 60 DAY)
GROUP BY p.ProductId, p.Name
ORDER BY TotalQuantity DESC
LIMIT 20;

-- Monthly sales revenue for the last 6 months
SELECT DATE_FORMAT(o.OrderTimestamp, '%Y-%m') AS Month, SUM(oi.Quantity * p.Price) AS MonthlyRevenue
FROM Orders o
JOIN OrderItem oi ON o.OrderId = oi.OrderId
JOIN Product p ON oi.ProductId = p.ProductId
WHERE o.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
GROUP BY Month
ORDER BY Month;

-- Mark products as inactive if not ordered in the last 90 days
UPDATE Product
SET ActiveStatus = FALSE
WHERE ProductId NOT IN (
    SELECT DISTINCT oi.ProductId
    FROM OrderItem oi
    JOIN Orders o ON oi.OrderId = o.OrderId
    WHERE o.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
);

-- Products by a category search keyword
SELECT p.ProductId, p.Name, c.Name AS CategoryName
FROM Product p
JOIN ProductCategory pc ON p.ProductId = pc.ProductId
JOIN Category c ON pc.CategoryId = c.CategoryId
WHERE c.Name LIKE '%<SearchKeyword>%';

-- Top 10 most-cancelled items
SELECT p.ProductId, p.Name, COUNT(oi.OrderItemId) AS CancelledCount
FROM Product p
JOIN OrderItem oi ON p.ProductId = oi.ProductId
WHERE oi.Status = 'Cancelled'
GROUP BY p.ProductId, p.Name
ORDER BY CancelledCount DESC
LIMIT 10;