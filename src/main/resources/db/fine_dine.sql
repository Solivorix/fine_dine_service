-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/Hv6F5I
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.

-- Modify this code to update the DB schema diagram.
-- To reset the sample schema, replace everything with
-- two dots ('..' - without quotes).

CREATE TABLE `restaurants` (
    `rest_id` string  NOT NULL ,
    `name` string  NOT NULL ,
    `gst_no` string  NOT NULL ,
    `primary_email` string  NOT NULL ,
    `primary_contact_number` string  NOT NULL ,
    `address` string  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `rest_id`
    )
);

CREATE TABLE `price` (
    `price_id` string  NOT NULL ,
    `rest_id` string  NOT NULL ,
    `portionSize` string  NOT NULL ,
    `item_id` string  NOT NULL ,
    `charge` string  NOT NULL ,
    `price` long  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `price_id`
    )
);

CREATE TABLE `additional_Pricing` (
    `adp_id` string  NOT NULL ,
    `service_type_Id` string  NOT NULL ,
    `priceName` string  NOT NULL ,
    `item_id` string  NOT NULL ,
    `charge` string  NOT NULL ,
    `chargeType` string  NOT NULL ,
    `rest_id` string  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `adp_id`
    )
);

CREATE TABLE `items` (
    `item_id` string  NOT NULL ,
    `product_id` string  NOT NULL ,
    `productName` string  NOT NULL ,
    `productDesc` string  NOT NULL ,
    `rest_id` string  NOT NULL ,
    `itemStatus` string  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `item_id`
    )
);

CREATE TABLE `service_type` (
    `service_type_id` string  NOT NULL ,
    `type_name` string  NOT NULL ,
    `rest_id` string  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `service_type_id`
    )
);

CREATE TABLE `users` (
    `id` string  NOT NULL ,
    `rest_id` string  NOT NULL ,
    `name` string  NOT NULL ,
    `email` string  NOT NULL ,
    `password` string  NOT NULL ,
    `contact_number` string  NOT NULL ,
    `role` string  NOT NULL ,
    `createdAt` Date  NOT NULL ,
    `updatedAt` Date  NOT NULL ,
    `createdBy` string  NOT NULL ,
    `updatedBy` string  NOT NULL ,
    PRIMARY KEY (
        `id`
    )
);

ALTER TABLE `price` ADD CONSTRAINT `fk_price_rest_id` FOREIGN KEY(`rest_id`)
REFERENCES `restaurants` (`rest_id`);

ALTER TABLE `price` ADD CONSTRAINT `fk_price_item_id` FOREIGN KEY(`item_id`)
REFERENCES `items` (`item_id`);

ALTER TABLE `additional_Pricing` ADD CONSTRAINT `fk_additional_Pricing_item_id` FOREIGN KEY(`item_id`)
REFERENCES `items` (`item_id`);

ALTER TABLE `additional_Pricing` ADD CONSTRAINT `fk_additional_Pricing_rest_id` FOREIGN KEY(`rest_id`)
REFERENCES `restaurants` (`rest_id`);

ALTER TABLE `items` ADD CONSTRAINT `fk_items_rest_id` FOREIGN KEY(`rest_id`)
REFERENCES `restaurants` (`rest_id`);

ALTER TABLE `service_type` ADD CONSTRAINT `fk_service_type_rest_id` FOREIGN KEY(`rest_id`)
REFERENCES `restaurants` (`rest_id`);

ALTER TABLE `users` ADD CONSTRAINT `fk_users_rest_id` FOREIGN KEY(`rest_id`)
REFERENCES `restaurants` (`rest_id`);

