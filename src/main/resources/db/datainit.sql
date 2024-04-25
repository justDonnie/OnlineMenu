-- Инициализация данных для таблицы order_boxes
INSERT INTO order_boxes (id)
VALUES (1),
       (2),
       (3),
       (4);

-- Инициализация данных для таблицы dishes
INSERT INTO dishes (id, image, name, description, price)
VALUES (1, 'image1.jpg', 'Dish 1', 'Description for Dish 1', 300),
       (2, 'image2.jpg', 'Dish 2', 'Description for Dish 2', 450),
       (3, 'image3.jpg', 'Dish 3', 'Description for Dish 3', 345),
       (4, 'image4.jpg', 'Dish 4', 'Description for Dish 4', 345),
       (5, 'image5.jpg', 'Dish 5', 'Description for Dish 5', 490),
       (6, 'image6.jpg', 'Dish 6', 'Description for Dish 6', 560),
       (7, 'image7.jpg', 'Dish 7', 'Description for Dish 7', 560),
       (8, 'image8.jpg', 'Dish 8', 'Description for Dish 8', 300),
       (9, 'image9.jpg', 'Dish 9', 'Description for Dish 9', 400),
       (10, 'image10.jpg', 'Dish 10', 'Description for Dish 10', 700);

-- Инициализация связей между order_boxes и dishes
INSERT INTO dish_order_box (dish_id, order_box_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 3),
       (8, 3),
       (9, 4),
       (10, 4);
