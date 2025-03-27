DROP PROCEDURE getAverageSales;
DELIMITER $$
SELECT * FROM Product;
CREATE PROCEDURE getAverageSales(input_month INT, input_year INT)
BEGIN
SELECT p.ProductId, p.Name, AVG(ot.Quantity) AS "Average Sales"
FROM OrderItem ot
JOIN Product p ON ot.ProductId = p.ProductId
JOIN Orders o ON o.OrderId = ot.OrderId
WHERE MONTH(OrderTimestamp) = input_month AND YEAR(OrderTimestamp) = input_year
GROUP BY p.ProductId;
END$$

DELIMITER ;

CALL getAverageSales( 3, 2025);


DROP PROCEDURE getOrdersBetween;
DELIMITER $$
CREATE PROCEDURE getOrdersBetween(start_date DATE, end_date DATE)
BEGIN
	IF start_date > end_date THEN
		SET start_date = date_format(end_date, '%y-%m-01');
	END IF;
	SELECT * 
	FROM Orders
	WHERE DATE(OrderTimestamp) >= start_date AND DATE(OrderTimestamp) <= end_date;
END$$

DELIMITER ;

CALL getOrdersBetween("2025-03-29", DATE(now()));