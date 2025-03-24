USE StoreFront;

-- 1. Display Product ID, Name, Category Name, and Price of active products, sorted by recent additions
SELECT 
    p.ProductId, 
    p.Name AS ProductName, 
    c.Name AS CategoryName, 
    p.Price 
FROM Product p
LEFT JOIN Category c ON p.CategoryId = c.CategoryId
ORDER BY p.ProductId DESC;

-- 2. Display the list of products that don't have any images
SELECT p.ProductId, p.Name 
FROM Product p
LEFT JOIN Image i ON p.ProductId = i.ProductId
WHERE i.ImageId IS NULL;

-- 3. Display all Category IDs, Names, and Parent Category Names (or "Top Category" for root categories), sorted by Parent and Category Name
SELECT 
    c.CategoryId, 
    c.Name AS CategoryName, 
    COALESCE(pc.Name, 'Top Category') AS ParentCategoryName 
FROM Category c
LEFT JOIN Category pc ON c.ParentCategoryId = pc.CategoryId
ORDER BY ParentCategoryName, CategoryName;

-- 4. Display ID, Name, Parent Category Name of all leaf categories (categories that are not parents of any other category)
SELECT 
    c.CategoryId, 
    c.Name AS CategoryName, 
    COALESCE(pc.Name, 'Top Category') AS ParentCategoryName
FROM Category c
LEFT JOIN Category pc ON c.ParentCategoryId = pc.CategoryId
WHERE c.CategoryId NOT IN (SELECT DISTINCT ParentCategoryId FROM Category WHERE ParentCategoryId IS NOT NULL)
ORDER BY ParentCategoryName, CategoryName;

-- 5. Display Product Name, Price & Description for a specific category (e.g., "Mobile")
SELECT 
    p.Name AS ProductName, 
    p.Price, 
    p.Description 
FROM Product p
JOIN Category c ON p.CategoryId = c.CategoryId
WHERE c.Name = 'Mobile';

-- 6. Display the list of products whose stock quantity is under 50
SELECT ProductId, Name, StockQuantity 
FROM Product 
WHERE StockQuantity < 50;
