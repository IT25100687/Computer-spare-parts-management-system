-- ===================================================
-- Spare Parts Management System - MSSQL Database Schema
-- Modules: Spare Parts, Sales & Orders, Inventory, Customers, Suppliers, Warranty
-- ===================================================

-- 1. Table: spare_parts (Spare Parts Catalog - Spec Section 7.1)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'spare_parts')
BEGIN
    CREATE TABLE spare_parts (
        spare_part_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        part_name VARCHAR(100) NOT NULL,
        category VARCHAR(50),
        brand VARCHAR(50) NOT NULL,
        description VARCHAR(255),
        unit_price FLOAT NOT NULL,
        compatibility VARCHAR(255),
        warranty_eligibility VARCHAR(100) DEFAULT 'Eligible (12 Months)',
        reorder_level INT DEFAULT 5,
        stock_quantity INT DEFAULT 0,
        supplier_id BIGINT
    );
END;

-- 2. Table: customer_orders (Spec Section 7.5)
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

-- 3. Table: sales (Spec Section 7.5)
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

-- 4. Table: order_items
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

-- 5. Table: inventory (Inventory Movements - Spec Section 7.2)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'inventory')
BEGIN
    CREATE TABLE inventory (
        inventory_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        spare_part_id BIGINT NOT NULL,
        current_quantity INT DEFAULT 0,
        stock_in_quantity INT DEFAULT 0,
        stock_out_quantity INT DEFAULT 0,
        reorder_level INT DEFAULT 5,
        movement_date DATETIME DEFAULT GETDATE(),
        notes VARCHAR(255)
    );
END;

-- 6. Table: customers (Customer Management - Spec Section 7.4)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'customers')
BEGIN
    CREATE TABLE customers (
        customer_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        phone VARCHAR(30) NOT NULL,
        address VARCHAR(255),
        customer_type VARCHAR(50) DEFAULT 'Individual',
        status VARCHAR(50) DEFAULT 'Active',
        registration_date DATETIME DEFAULT GETDATE()
    );
END;

-- 7. Table: suppliers (Supplier Management - Spec Section 7.3)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'suppliers')
BEGIN
    CREATE TABLE suppliers (
        supplier_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        supplier_name VARCHAR(100) NOT NULL,
        company VARCHAR(100) NOT NULL,
        phone VARCHAR(30) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        address VARCHAR(255),
        status VARCHAR(50) DEFAULT 'Active',
        parts_supplied VARCHAR(255)
    );
END;

-- 8. Table: warranties (Warranty Management - Spec Section 7.6)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'warranties')
BEGIN
    CREATE TABLE warranties (
        warranty_id BIGINT IDENTITY(1,1) PRIMARY KEY,
        sale_id BIGINT NOT NULL,
        customer_id BIGINT NOT NULL,
        spare_part_id BIGINT NOT NULL,
        start_date DATETIME DEFAULT GETDATE(),
        expiry_date DATETIME NOT NULL,
        warranty_status VARCHAR(50) DEFAULT 'Active',
        claim_description VARCHAR(255),
        claim_status VARCHAR(50) DEFAULT 'No Claim'
    );
END;
