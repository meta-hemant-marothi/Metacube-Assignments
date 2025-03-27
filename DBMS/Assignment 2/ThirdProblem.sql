-- Display Recent 50 Orders
SELECT OrderId AS Id, OrderTimestamp AS OrderDate,
       (SELECT SUM(Price * Quantity)
        FROM OrderItem
        JOIN Product ON OrderItem.ProductId = Product.ProductId
        WHERE OrderItem.OrderId = Orders.OrderId) AS OrderTotal
FROM Orders
ORDER BY OrderTimestamp DESC
LIMIT 50;

-- Display 10 Most Expensive Orders
SELECT OrderId, OrderTimestamp AS OrderDate,
       (SELECT SUM(Price * Quantity)
        FROM OrderItem
        JOIN Product ON OrderItem.ProductId = Product.ProductId
        WHERE OrderItem.OrderId = Orders.OrderId) AS OrderTotal
FROM Orders
ORDER BY OrderTotal DESC
LIMIT 10;

-- Orders Older than 10 Days with Items Not Yet Shipped
SELECT Orders.OrderId, Orders.OrderTimestamp AS OrderDate
FROM Orders
WHERE Orders.OrderTimestamp < (NOW() - INTERVAL 10 DAY)
AND EXISTS (
    SELECT 1
    FROM OrderItem
    WHERE OrderItem.OrderId = Orders.OrderId AND OrderItem.Status = 'Placed'
);

-- Shoppers Who Haven’t Ordered Anything Since Last Month
SELECT UserId, Name
FROM User
WHERE Role = 'Shopper'
AND UserId NOT IN (
    SELECT ShopperId
    FROM Orders
    WHERE OrderTimestamp > (NOW() - INTERVAL 1 MONTH)
);

-- Shoppers with Orders in the Last 15 Days
SELECT User.UserId, User.Name, COUNT(Orders.OrderId) AS RecentOrders
FROM User
LEFT JOIN Orders ON User.UserId = Orders.ShopperId
WHERE Orders.OrderTimestamp > (NOW() - INTERVAL 15 DAY)
GROUP BY User.UserId, User.Name;

-- Order Items in "Shipped" State for a Specific Order (Example: Order Id = 1020)
SELECT OrderItemId, ProductId, Quantity, Status
FROM OrderItem
WHERE OrderId = 1020 AND Status = 'Shipped';

-- Order Items (with Order Date) Priced Between Rs 20 and Rs 50
SELECT OrderItem.OrderItemId, Product.Name AS ProductTitle,
       Product.Price, OrderItem.Quantity, OrderItem.Status,
       Orders.OrderTimestamp AS OrderDate
FROM OrderItem
JOIN Product ON OrderItem.ProductId = Product.ProductId
JOIN Orders ON OrderItem.OrderId = Orders.OrderId
WHERE Product.Price BETWEEN 20 AND 50;