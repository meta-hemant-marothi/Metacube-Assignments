-- ASSIGNMENT 2: Queries

-- Products in more than one category
SELECT p.ProductId, p.Name, COUNT(pc.CategoryId) AS CategoryCount
FROM Product p
JOIN ProductCategory pc ON p.ProductId = pc.ProductId
GROUP BY p.ProductId, p.Name
HAVING COUNT(pc.CategoryId) > 1;

-- Count of products by price range
SELECT 
    CASE
        WHEN Price BETWEEN 0 AND 100 THEN '0 - 100'
        WHEN Price BETWEEN 101 AND 500 THEN '101 - 500'
        ELSE 'Above 500'
    END AS PriceRange,
    COUNT(*) AS ProductCount
FROM Product
GROUP BY PriceRange;

-- Categories with number of products
SELECT c.Name AS CategoryName, COUNT(pc.ProductId) AS ProductCount
FROM Category c
LEFT JOIN ProductCategory pc ON c.CategoryId = pc.CategoryId
GROUP BY c.CategoryId, c.Name;