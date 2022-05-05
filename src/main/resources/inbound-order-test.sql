insert into WAREHOUSE (id, name, region) values (1, 'Tools', 'Doujia');

INSERT INTO "user" (role, id, cpf, last_name, name, password, username) VALUES('ADMIN', 1, NULL, 'ADMIN', 'ADMIN', '$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u', 'ADMIN');

INSERT INTO "user" (role, id, cpf, last_name, name, password, username) VALUES('Supervisor', 2, NULL, 'Supervisor', 'Supervisor', '$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u', 'Supervisor');

INSERT INTO "user" (role, id, cpf, last_name, name, password, username) VALUES('Seller', 3, NULL, 'Seller', 'Seller', '$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u', 'Seller');

INSERT INTO "user" (role, id, cpf, last_name, name, password, username) VALUES('Buyer', 4, NULL, 'Buyer', 'Buyer', '$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u', 'Buyer');

INSERT INTO sector (id, capacity, category, "supervisor_id", warehouse_id) VALUES(1, 1000, 'CONGELADOS', 2, 1);

INSERT INTO inbound_order (id, order_date, sector_id) VALUES(99, '2022-05-04', 1);

INSERT INTO product (id,category,name,price,volume,"seller_id") VALUES
    (1,'CONGELADOS','frango',20.00,1,3);

INSERT INTO batch_stock (id, category, current_quantity, current_temperature, due_date, initial_quantity, initial_temperature, manufacturing_date, manufacturing_time, inbound_order_id, product_id) VALUES(99, 'CONGELADOS', 1, 2, '2025-10-10', 1, 2, '2022-10-10', '20:20:20', 99, 1);

INSERT INTO region (id, region, value) VALUES(1, 'norte', 50.00);
INSERT INTO region (id, region, value) VALUES(2, 'nordeste', 40.00);
INSERT INTO region (id, region, value) VALUES(3, 'sul', 30.00);
INSERT INTO region (id, region, value) VALUES(4, 'sudeste', 20.00);
INSERT INTO region (id, region, value) VALUES(5, 'centro oeste', 10.00);

INSERT INTO state (id, name, region_id) VALUES(1, 'AM', 1);
INSERT INTO state (id, name, region_id) VALUES(2, 'RR', 1);
INSERT INTO state (id, name, region_id) VALUES(3, 'AP', 1);
INSERT INTO state (id, name, region_id) VALUES(4, 'PA', 1);
INSERT INTO state (id, name, region_id) VALUES(5, 'TO', 1);
INSERT INTO state (id, name, region_id) VALUES(6, 'RO', 1);
INSERT INTO state (id, name, region_id) VALUES(7, 'AC', 1);
INSERT INTO state (id, name, region_id) VALUES(8, 'MA', 2);
INSERT INTO state (id, name, region_id) VALUES(9, 'PI', 2);
INSERT INTO state (id, name, region_id) VALUES(10, 'CE', 2);
INSERT INTO state (id, name, region_id) VALUES(11, 'RN', 2);
INSERT INTO state (id, name, region_id) VALUES(12, 'PE', 2);
INSERT INTO state (id, name, region_id) VALUES(13, 'PB', 2);
INSERT INTO state (id, name, region_id) VALUES(14, 'SE', 2);
INSERT INTO state (id, name, region_id) VALUES(15, 'AL', 2);
INSERT INTO state (id, name, region_id) VALUES(16, 'BA', 2);
INSERT INTO state (id, name, region_id) VALUES(17, 'MT', 3);
INSERT INTO state (id, name, region_id) VALUES(18, 'MS', 3);
INSERT INTO state (id, name, region_id) VALUES(19, 'GO', 3);
INSERT INTO state (id, name, region_id) VALUES(20, 'SP', 4);
INSERT INTO state (id, name, region_id) VALUES(21, 'RJ', 4);
INSERT INTO state (id, name, region_id) VALUES(22, 'ES', 4);
INSERT INTO state (id, name, region_id) VALUES(23, 'MG', 4);
INSERT INTO state (id, name, region_id) VALUES(24, 'PR', 5);
INSERT INTO state (id, name, region_id) VALUES(25, 'RS', 5);
INSERT INTO state (id, name, region_id) VALUES(26, 'SC', 5);
INSERT INTO state (id, name, region_id) VALUES(27, 'DF', 3);

INSERT INTO address (id, add_on, city, identifier, neighborhood, number, street, zip, state_id, "user_id") VALUES(1, 'apt 602', 'Magé', 'Minha casa', 'Centro', 13, 'Rua Idalina Monteiro', '25900-028', 21, 4);
