-- ===================================================
-- Initial Sample Data for Sales & Order Management (MSSQL)
-- ===================================================

-- Sample Customer Orders
INSERT INTO customer_orders (customer_id, spare_part_id, quantity, unit_price, total_amount, order_status, order_date) 
VALUES (101, 501, 2, 45.00, 90.00, 'Completed', GETDATE());

INSERT INTO customer_orders (customer_id, spare_part_id, quantity, unit_price, total_amount, order_status, order_date) 
VALUES (102, 502, 1, 120.50, 120.50, 'Pending', GETDATE());

-- Sample Completed Sales
INSERT INTO sales (order_id, customer_id, sale_date, payment_status, invoice_number) 
VALUES (1, 101, GETDATE(), 'Paid', 'INV-1710000000001');
