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