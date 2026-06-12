CREATE TABLE machine_capacity (
    id BIGSERIAL PRIMARY KEY,
    machine_name VARCHAR(100) NOT NULL,
    product_id BIGINT NOT NULL,
    available_quantity INT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);