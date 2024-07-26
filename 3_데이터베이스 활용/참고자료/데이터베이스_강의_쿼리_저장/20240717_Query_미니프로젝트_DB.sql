CREATE TABLE `menuInfo` (
	`menuId`	INT	NOT NULL,
	`storeId`	INT	NOT NULL,
	`name`	VARCHAR(40)	NULL,
	`description`	VARCHAR(100)	NULL,
	`price`	INT	NULL,
	`category`	VARCHAR(30)	NULL,
	`imageUrl`	VARCHAR(100)	NULL
);

CREATE TABLE `Stores` (
	`storeId`	INT	NOT NULL,
	`categoryId`	INT	NOT NULL,
	`storeName`	VARCHAR(40)	NULL,
	`location`	VARCHAR(100)	NULL,
	`tell`	VARCHAR(40)	NULL,
	`operatingHours`	VARCHAR(100)	NULL,
	`minPrice`	INT	NULL
);

CREATE TABLE `eventInfo` (
	`eventId`	INT	NOT NULL,
	`storeId`	INT	NOT NULL,
	`name`	VARCHAR(40)	NULL,
	`description`	VARCHAR(100)	NULL,
	`startDate`	DATETIME	NULL,
	`endDate`	DATETIME	NULL,
	`discounRate`	INT	NULL,
	`maxDiscountPrice`	INT	NULL
);

CREATE TABLE `orderInfo` (
	`orderId`	INT	NOT NULL,
	`storeId`	INT	NOT NULL,
	`userId2`	INT	NOT NULL,
	`orderDate`	DATETIME	NULL,
	`orderStatus`	VARCHAR(30)	NULL,
	`totalPrice`	INT	NULL,
	`autoIncrement`	INT	NOT NULL,
	`userId`	INT	NOT NULL
);

CREATE TABLE `PaymentMethods` (
	`autoIncrement`	INT	NOT NULL,
	`userId`	INT	NOT NULL,
	`payMethodsType`	VARCHAR(40)	NULL,
	`cardNumber`	INT	NULL,
	`deadline`	DATETIME	NULL,
	`cardOwnerName`	VARCHAR(40)	NULL
);

CREATE TABLE `Addresses` (
	`autoIncrement`	INT	NOT NULL,
	`userId`	INT	NOT NULL,
	`addressesType`	VARCHAR(40)	NULL,
	`addresses`	VARCHAR(100)	NULL,
	`city`	VARCHAR(100)	NULL,
	`postCode`	VARCHAR(100)	NULL,
	`country`	VARCHAR(100)	NULL
);

CREATE TABLE `userInfo` (
	`userId`	INT	NOT NULL,
	`name`	VARCHAR(40)	NULL,
	`email`	VARCHAR(40)	NULL,
	`phone`	VARCHAR(40)	NULL,
	`registerDate`	DATETIME	NULL
);

CREATE TABLE `storeCategoryInfo` (
	`categoryId`	INT	NOT NULL,
	`name`	VARCHAR(30)	NULL,
	`description`	VARCHAR(100)	NULL
);

CREATE TABLE `reviewInfo` (
	`reviewId`	INT	NOT NULL,
	`userId`	INT	NOT NULL,
	`storeId`	INT	NOT NULL,
	`orderId`	INT	NOT NULL,
	`description`	VARCHAR(100)	NULL,
	`score`	INT	NULL,
	`registerDate`	DATETIME	NULL
);

CREATE TABLE `Delivery` (
	`deliveryId`	INT	NOT NULL,
	`orderId`	INT	NOT NULL,
	`driverId`	INT	NOT NULL,
	`state`	VARCHAR(40)	NULL,
	`deliveryStartTime`	DATETIME	NULL,
	`deliveryEndTime`	DATETIME	NULL
);

CREATE TABLE `menuDetailInfo` (
	`orderId`	INT	NOT NULL,
	`menuId`	INT	NOT NULL,
	`quantity`	INT	NULL
);

CREATE TABLE `driverInfo` (
	`driverId`	INT	NOT NULL
);

ALTER TABLE `menuInfo` ADD CONSTRAINT `PK_MENUINFO` PRIMARY KEY (
	`menuId`
);

ALTER TABLE `Stores` ADD CONSTRAINT `PK_STORES` PRIMARY KEY (
	`storeId`
);

ALTER TABLE `eventInfo` ADD CONSTRAINT `PK_EVENTINFO` PRIMARY KEY (
	`eventId`
);

ALTER TABLE `orderInfo` ADD CONSTRAINT `PK_ORDERINFO` PRIMARY KEY (
	`orderId`
);

ALTER TABLE `PaymentMethods` ADD CONSTRAINT `PK_PAYMENTMETHODS` PRIMARY KEY (
	`autoIncrement`,
	`userId`
);

ALTER TABLE `Addresses` ADD CONSTRAINT `PK_ADDRESSES` PRIMARY KEY (
	`autoIncrement`,
	`userId`
);

ALTER TABLE `userInfo` ADD CONSTRAINT `PK_USERINFO` PRIMARY KEY (
	`userId`
);

ALTER TABLE `storeCategoryInfo` ADD CONSTRAINT `PK_STORECATEGORYINFO` PRIMARY KEY (
	`categoryId`
);

ALTER TABLE `reviewInfo` ADD CONSTRAINT `PK_REVIEWINFO` PRIMARY KEY (
	`reviewId`
);

ALTER TABLE `Delivery` ADD CONSTRAINT `PK_DELIVERY` PRIMARY KEY (
	`deliveryId`
);

ALTER TABLE `menuDetailInfo` ADD CONSTRAINT `PK_MENUDETAILINFO` PRIMARY KEY (
	`orderId`,
	`menuId`
);

ALTER TABLE `driverInfo` ADD CONSTRAINT `PK_DRIVERINFO` PRIMARY KEY (
	`driverId`
);

ALTER TABLE `PaymentMethods` ADD CONSTRAINT `FK_userInfo_TO_PaymentMethods_1` FOREIGN KEY (
	`userId`
)
REFERENCES `userInfo` (
	`userId`
);

ALTER TABLE `Addresses` ADD CONSTRAINT `FK_userInfo_TO_Addresses_1` FOREIGN KEY (
	`userId`
)
REFERENCES `userInfo` (
	`userId`
);

ALTER TABLE `menuDetailInfo` ADD CONSTRAINT `FK_orderInfo_TO_menuDetailInfo_1` FOREIGN KEY (
	`orderId`
)
REFERENCES `orderInfo` (
	`orderId`
);

ALTER TABLE `menuDetailInfo` ADD CONSTRAINT `FK_menuInfo_TO_menuDetailInfo_1` FOREIGN KEY (
	`menuId`
)
REFERENCES `menuInfo` (
	`menuId`
);

