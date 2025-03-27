DROP FUNCTION calculateNumberOfOrders;
DELIMITER $$

CREATE FUNCTION calculateNumberOfOrders(input_month INT, input_year INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE orders_count INT;
    
    SELECT COUNT(*) INTO orders_count
    FROM Orders
    WHERE MONTH(OrderTimestamp) = input_month AND YEAR(OrderTimestamp) = input_year;

    RETURN orders_count;
END$$

DELIMITER ;

SELECT calculateNumberOfOrders(3, 2025); 

DELIMITER $$

CREATE FUNCTION monthHavingMaxOrders(input_year INT)
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE max_order_month INT;
    SELECT MONTH(OrderTimestamp) INTO max_order_month
    FROM Orders
    WHERE YEAR(OrderTimestamp) = imput_year
    GROUP BY MONTH(OrderTimestamp)
    ORDER BY COUNT(OrderId) DESC
    LIMIT 1;
    
    
    RETURN max_order_month;
END$$
DELIMITER ;

SELECT monthHavingMaxOrders(2025);