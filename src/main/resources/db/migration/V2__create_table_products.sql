CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY UNIQUE,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    product_image_url VARCHAR(255)
);
