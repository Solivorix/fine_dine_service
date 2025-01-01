-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/Hv6F5I
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.

-- Modify this code to update the DB schema diagram.
-- To reset the sample schema, replace everything with
-- two dots ('..' - without quotes).

CREATE TABLE `restaurants` (
    `restaurant_id` string  NOT NULL ,
    `restaurant_name` string  NOT NULL ,
    `gst_number` string  NOT NULL ,
    `primary_email` string  NOT NULL ,
    `primary_contact_number` string  NOT NULL ,
    `address` string  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `restaurant_id`
    )
);

CREATE TABLE `prices` (
    `price_id` string  NOT NULL ,
    `restaurant_id` string  NOT NULL ,
    `portion_size` string  NOT NULL ,
    `item_id` string  NOT NULL ,
    `price` decimal  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `price_id`
    )
);

CREATE TABLE `additional_Pricing` (
    `additional_pricing_id` string  NOT NULL ,
    `service_type_id` string  NOT NULL ,
    `price_name` string  NOT NULL ,
    `item_id` string  NOT NULL ,
    `charge` decimal  NOT NULL ,
    `charge_type` string  NOT NULL ,
    `restaurant_id` string  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `additional_pricing_id`
    )
);

CREATE TABLE `items` (
    `item_id` string  NOT NULL ,
    `product_id` string  NOT NULL ,
    `product_name` string  NOT NULL ,
    `product_description` string  NOT NULL ,
    `restaurant_id` string  NOT NULL ,
    `item_status` string  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `item_id`
    )
);

CREATE TABLE `service_types` (
    `service_type_id` string  NOT NULL ,
    `type_name` string  NOT NULL ,
    `restaurant_id` string  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `service_type_id`
    )
);

CREATE TABLE `users` (
    `user_id` string  NOT NULL ,
    `restaurant_id` string  NOT NULL ,
    `user_name` string  NOT NULL ,
    `email` string  NOT NULL ,
    `password` string  NOT NULL ,
    `contact_number` string  NOT NULL ,
    `role` string  NOT NULL ,
    `created_at` date  NOT NULL ,
    `updated_at` date  NOT NULL ,
    `created_by` string  NOT NULL ,
    `updated_by` string  NOT NULL ,
    PRIMARY KEY (
        `user_id`
    )
);

ALTER TABLE `prices` ADD CONSTRAINT `fk_prices_restaurant_id` FOREIGN KEY(`restaurant_id`)
REFERENCES `restaurants` (`restaurant_id`);

ALTER TABLE `prices` ADD CONSTRAINT `fk_prices_item_id` FOREIGN KEY(`item_id`)
REFERENCES `items` (`item_id`);

ALTER TABLE `additional_Pricing` ADD CONSTRAINT `fk_additional_Pricing_service_type_id` FOREIGN KEY(`service_type_id`)
REFERENCES `service_types` (`service_type_id`);

ALTER TABLE `additional_Pricing` ADD CONSTRAINT `fk_additional_Pricing_item_id` FOREIGN KEY(`item_id`)
REFERENCES `items` (`item_id`);

ALTER TABLE `additional_Pricing` ADD CONSTRAINT `fk_additional_Pricing_restaurant_id` FOREIGN KEY(`restaurant_id`)
REFERENCES `restaurants` (`restaurant_id`);

ALTER TABLE `items` ADD CONSTRAINT `fk_items_restaurant_id` FOREIGN KEY(`restaurant_id`)
REFERENCES `restaurants` (`restaurant_id`);

ALTER TABLE `service_types` ADD CONSTRAINT `fk_service_types_restaurant_id` FOREIGN KEY(`restaurant_id`)
REFERENCES `restaurants` (`restaurant_id`);

ALTER TABLE `users` ADD CONSTRAINT `fk_users_restaurant_id` FOREIGN KEY(`restaurant_id`)
REFERENCES `restaurants` (`restaurant_id`);

