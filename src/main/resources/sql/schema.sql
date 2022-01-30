CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    name varchar(150) NULL,
    sku varchar(100) NULL,
    description varchar(200) NULL,
    price numeric NULL,
    is_simple boolean NULL default true,
    fecha_create timestamp without time zone null DEFAULT CURRENT_TIMESTAMP,
    fecha_update timestamp without time zone null default NULL,
    fecha_delete timestamp without time zone null default NULL
);

CREATE TABLE IF NOT EXISTS carts (
    id VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    status VARCHAR(60) NULL default 'PENDING',
    price numeric null default 0.0,
    fecha_create timestamp without time zone null DEFAULT CURRENT_TIMESTAMP,
    fecha_update timestamp without time zone null default NULL,
    fecha_delete timestamp without time zone null default NULL
);

CREATE TABLE IF NOT EXISTS cart_products (
    id VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    cart_id VARCHAR(60) not null,
    product_id VARCHAR(60) not null,
    quantity int default  0,
    fecha_create timestamp without time zone null DEFAULT CURRENT_TIMESTAMP,
    fecha_update timestamp without time zone null default NULL,
    fecha_delete timestamp without time zone null default NULL
)