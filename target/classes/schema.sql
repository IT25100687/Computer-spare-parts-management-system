-- ===================================================
-- Spare Parts Management System - MSSQL Database Schema
-- Module: Sales & Order Management
-- ===================================================

-- 1. Table: customer_orders
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'customer_orders')
BEGIN
    CREATE TABLE customer_orders (
        order_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        customer_id BIGINT NOT NULL,
        spare_part_id BIGINT,
        quantity INT,
        unit_price FLOAT,
        total_amount FLOAT,
        order_status VARCHAR(50) DEFAULT 'Pending',
        order_date DATETIME DEFAULT GETDATE()
    );
END;

-- 2. Table: sales
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'sales')
BEGIN
    CREATE TABLE sales (
        sale_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        order_id BIGINT NOT NULL,
        customer_id BIGINT NOT NULL,
        sale_date DATETIME DEFAULT GETDATE(),
        payment_status VARCHAR(50) DEFAULT 'Paid',
        invoice_number VARCHAR(100) UNIQUE
    );
END;

-- 3. Table: order_items
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'order_items')
BEGIN
    CREATE TABLE order_items (
        order_item_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        order_id BIGINT NOT NULL,
        spare_part_id BIGINT NOT NULL,
        quantity INT NOT NULL,
        unit_price FLOAT NOT NULL,
        total_price FLOAT NOT NULL
    );
END;
