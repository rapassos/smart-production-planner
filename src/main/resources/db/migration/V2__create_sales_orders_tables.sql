CREATE TABLE sales_order (
    id BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sales_order_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE sales_order_item (
    id BIGSERIAL PRIMARY KEY,
    sales_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    CONSTRAINT fk_item_sales_order FOREIGN KEY (sales_order_id) REFERENCES sales_order(id) ON DELETE CASCADE,
    CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE INDEX idx_sales_order_customer ON sales_order(customer_id);
CREATE INDEX idx_sales_order_item_order ON sales_order_item(sales_order_id);