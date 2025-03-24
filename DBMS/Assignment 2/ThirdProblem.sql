USE StoreFront;

-- 1. Display the 50 most recent orders with Id, Order Date, and Order Total
SELECT 
    o.OrderId, 
    o.OrderTimestamp, 
    SUM(oi.Quantity * oi.PriceAtPurchase) AS OrderTotal
FROM Orders o
JOIN OrderItem oi ON o.OrderId = oi.OrderId
GROUP BY o.OrderId, o.OrderTimestamp
ORDER BY o.OrderTimestamp DESC
LIMIT 50;

-- 2. Display the 10 most expensive orders
SELECT 
    o.OrderId, 
    SUM(oi.Quantity * oi.PriceAtPurchase) AS OrderTotal
FROM Orders o
JOIN OrderItem oi ON o.OrderId = oi.OrderId
GROUP BY o.OrderId
ORDER BY OrderTotal DESC
LIMIT 10;

-- 3. Display orders placed more than 10 days ago with at least one item still not shipped
SELECT DISTINCT o.OrderId, o.OrderTimestamp, o.Status 
FROM Orders o
JOIN OrderItem oi ON o.OrderId = oi.OrderId
WHERE o.OrderTimestamp < NOW() - INTERVAL 10 DAY
AND oi.Status NOT IN ('Shipped', 'Delivered', 'Cancelled');

-- 4. Display shoppers who haven't placed any order in the last month
SELECT u.UserId, u.Name, u.Email 
FROM User u
LEFT JOIN Orders o ON u.UserId = o.ShopperId 
    AND o.OrderTimestamp >= NOW() - INTERVAL 1 MONTH
WHERE o.OrderId IS NULL AND u.Role = 'Shopper';

-- 5. Display shoppers along with orders they placed in the last 15 days
SELECT 
    u.UserId, 
    u.Name AS ShopperName, 
    o.OrderId, 
    o.OrderTimestamp 
FROM User u
JOIN Orders o ON u.UserId = o.ShopperId
WHERE o.OrderTimestamp >= NOW() - INTERVAL 15 DAY
ORDER BY o.OrderTimestamp DESC;

-- 6. Display list of order items that are in “shipped” state for a particular OrderId (e.g., 1020)
SELECT 
    oi.OrderItemId, 
    oi.OrderId, 
    p.Name AS ProductName, 
    oi.Quantity, 
    oi.PriceAtPurchase, 
    oi.Status
FROM OrderItem oi
JOIN Product p ON oi.ProductId = p.ProductId
WHERE oi.OrderId = 1020 
AND oi.Status = 'Shipped';

-- 7. Display order items along with order placed date for items with price between Rs 20 and Rs 50
SELECT 
    oi.OrderItemId, 
    oi.OrderId, 
    o.OrderTimestamp, 
    p.Name AS ProductName, 
    oi.Quantity, 
    oi.PriceAtPurchase
FROM OrderItem oi
JOIN Orders o ON oi.OrderId = o.OrderId
JOIN Product p ON oi.ProductId = p.ProductId
WHERE oi.PriceAtPurchase BETWEEN 20 AND 50
ORDER BY o.OrderTimestamp DESC;
