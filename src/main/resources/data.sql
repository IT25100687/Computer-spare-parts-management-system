-- ===================================================
-- Initial Sample Data for Computer Spare Parts Management System (MSSQL)
-- ===================================================

-- Sample Computer Spare Parts Catalog (Spec Section 7.1)
INSERT INTO spare_parts (part_name, category, brand, description, unit_price, compatibility, warranty_eligibility, reorder_level, stock_quantity, supplier_id)
VALUES ('Intel Core i7-13700K CPU', 'Processors (CPU)', 'Intel', '16 Cores (8P+8E) up to 5.4 GHz Unlocked', 380.00, 'LGA 1700, DDR4/DDR5', 'Eligible (36 Months)', 5, 25, 1);

INSERT INTO spare_parts (part_name, category, brand, description, unit_price, compatibility, warranty_eligibility, reorder_level, stock_quantity, supplier_id)
VALUES ('NVIDIA GeForce RTX 4070 12GB GPU', 'Graphics Cards (GPU)', 'ASUS / NVIDIA', '12GB GDDR6X, DLSS 3, Ray Tracing', 599.99, 'PCIe 4.0 x16, 650W PSU', 'Eligible (36 Months)', 3, 10, 2);

INSERT INTO spare_parts (part_name, category, brand, description, unit_price, compatibility, warranty_eligibility, reorder_level, stock_quantity, supplier_id)
VALUES ('Corsair Vengeance 32GB (2x16GB) DDR5', 'RAM / Memory', 'Corsair', 'DDR5 6000MHz CL36 Desktop RAM Kit', 115.00, 'DDR5 Motherboards', 'Eligible (36 Months)', 10, 40, 3);

INSERT INTO spare_parts (part_name, category, brand, description, unit_price, compatibility, warranty_eligibility, reorder_level, stock_quantity, supplier_id)
VALUES ('Samsung 990 PRO 2TB NVMe SSD', 'Storage (SSD/HDD)', 'Samsung', 'PCIe Gen 4.0 x4 M.2 2280 SSD 7450 MB/s', 169.99, 'M.2 NVMe Slot', 'Eligible (24 Months)', 5, 18, 2);

-- Sample Computer Hardware Suppliers (Spec Section 7.3)
INSERT INTO suppliers (supplier_name, company, phone, email, address, status, parts_supplied)
VALUES ('Robert Smith', 'Intel Corporation', '+1-800-555-0199', 'sales@intel-supplies.com', '2200 Mission College Blvd, Santa Clara, CA', 'Active', 'CPUs, Processors, NUC Systems');

INSERT INTO suppliers (supplier_name, company, phone, email, address, status, parts_supplied)
VALUES ('Elena Rostova', 'ASUS Computer Tech', '+1-800-555-0244', 'orders@asus-hardware.com', '48720 Kato Rd, Fremont, CA', 'Active', 'Graphics Cards, Motherboards, Monitors');

INSERT INTO suppliers (supplier_name, company, phone, email, address, status, parts_supplied)
VALUES ('David Miller', 'Corsair Components Inc.', '+1-800-555-0377', 'contact@corsair-supply.com', '1150 Enterprise Ct, Milpitas, CA', 'Active', 'RAM Memory, Power Supplies, CPU Coolers');

-- Sample Registered Customers (Spec Section 7.4)
INSERT INTO customers (name, email, phone, address, customer_type, status, registration_date)
VALUES ('John Doe', 'john.doe@example.com', '+1-555-0192', '123 Main St, New York', 'Individual', 'Active', GETDATE());

INSERT INTO customers (name, email, phone, address, customer_type, status, registration_date)
VALUES ('Apex Tech Repair Ltd', 'contact@apextech.com', '+1-555-0888', '456 Tech Parkway', 'Corporate', 'Active', GETDATE());

-- Sample Inventory Movements (Spec Section 7.2)
INSERT INTO inventory (spare_part_id, current_quantity, stock_in_quantity, stock_out_quantity, reorder_level, movement_date, notes)
VALUES (1, 25, 30, 5, 5, GETDATE(), 'Initial stock intake from Supp-1 (Intel Corp)');

INSERT INTO inventory (spare_part_id, current_quantity, stock_in_quantity, stock_out_quantity, reorder_level, movement_date, notes)
VALUES (2, 10, 15, 5, 3, GETDATE(), 'RTX 4070 GPUs stock intake from Supp-2 (ASUS)');

-- Sample Customer Orders (Spec Section 7.5)
INSERT INTO customer_orders (customer_id, spare_part_id, quantity, unit_price, total_amount, order_status, order_date) 
VALUES (1, 1, 1, 380.00, 380.00, 'Completed', GETDATE());

-- Sample Completed Sales (Spec Section 7.5)
INSERT INTO sales (order_id, customer_id, sale_date, payment_status, invoice_number) 
VALUES (1, 1, GETDATE(), 'Paid', 'INV-1710000000001');

-- Sample Warranties & Claims (Spec Section 7.6)
INSERT INTO warranties (sale_id, customer_id, spare_part_id, start_date, expiry_date, warranty_status, claim_description, claim_status)
VALUES (1, 1, 1, GETDATE(), DATEADD(year, 3, GETDATE()), 'Active', 'No issues reported', 'No Claim');
