USE StoreFront;

-- 1. Display Product ID, Name, Category Name, and Price of active products, sorted by recent additions
SELECT Product.ProductId AS Id, Product.Name AS Title, Category.Name AS CategoryTitle, Product.Price
FROM Product
JOIN ProductCategory ON Product.ProductId = ProductCategory.ProductId
JOIN Category ON ProductCategory.CategoryId = Category.CategoryId
WHERE Product.ActiveStatus = TRUE
ORDER BY Product.UpdatedAt DESC;

-- 2. Display the list of products that don't have any images
SELECT p.ProductId, p.Name 
FROM Product p
LEFT JOIN Image i ON p.ProductId = i.ProductId
WHERE i.ImageId IS NULL;

-- 3. Display all Category IDs, Names, and Parent Category Names (or "Top Category" for root categories), sorted by Parent and Category Name
SELECT c.CategoryId, c.Name AS CategoryName, COALESCE(pc.Name, 'Top Category') AS ParentCategoryName 
FROM Category c
LEFT JOIN Category pc ON c.ParentCategoryId = pc.CategoryId
ORDER BY ParentCategoryName, CategoryName;

-- 4. Display ID, Name, Parent Category Name of all leaf categories (categories that are not parents of any other category)
SELECT c.CategoryId, c.Name AS CategoryName, COALESCE(pc.Name, 'Top Category') AS ParentCategoryName
FROM Category c
LEFT JOIN Category pc ON c.ParentCategoryId = pc.CategoryId
WHERE c.CategoryId NOT IN (SELECT DISTINCT ParentCategoryId FROM Category WHERE ParentCategoryId IS NOT NULL)
ORDER BY ParentCategoryName, CategoryName;

-- 5. Display Product Name, Price & Description for a specific category (e.g., "Mobile")
SELECT p.Name AS ProductName, p.Price, p.Description
FROM Product p
JOIN ProductCategory pc ON p.ProductId = pc.ProductId
JOIN Category c ON pc.CategoryId = c.CategoryId
WHERE c.Name = 'Mobile';

-- 6. Display the list of products whose stock quantity is under 50
SELECT ProductId, Name, StockQuantity 
FROM Product 
WHERE StockQuantity < 50;
