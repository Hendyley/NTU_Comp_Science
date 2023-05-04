IF OBJECT_ID('dbo.Magazine', 'U') IS NOT NULL DROP TABLE dbo.Magazine
IF OBJECT_ID('dbo.Inventory', 'U') IS NOT NULL DROP TABLE dbo.Inventory
IF OBJECT_ID('dbo.Publication', 'U') IS NOT NULL DROP TABLE dbo.Publication
IF OBJECT_ID('dbo.Book', 'U') IS NOT NULL DROP TABLE dbo.Book
IF OBJECT_ID('dbo.Customer', 'U') IS NOT NULL DROP TABLE dbo.Customer
IF OBJECT_ID('dbo.BookstoreComplaint', 'U') IS NOT NULL DROP TABLE dbo.BookstoreComplaint
IF OBJECT_ID('dbo.OrderComplaint', 'U') IS NOT NULL DROP TABLE dbo.OrderComplaint
IF OBJECT_ID('dbo.Complaints', 'U') IS NOT NULL DROP TABLE dbo.Complaints
IF OBJECT_ID('dbo.Employee', 'U') IS NOT NULL DROP TABLE dbo.Employee
IF OBJECT_ID('dbo.Order', 'U') IS NOT NULL DROP TABLE dbo.[Order]
IF OBJECT_ID('dbo.[Price Records]', 'U') IS NOT NULL DROP TABLE dbo.[Price Records]
IF OBJECT_ID('dbo.Bookstore', 'U') IS NOT NULL DROP TABLE dbo.Bookstore
IF OBJECT_ID('dbo.Items_In_Order', 'U') IS NOT NULL DROP TABLE dbo.Items_In_Order



CREATE TABLE Magazine (
    [publication-ID] varchar(255) NOT NULL,
    issue varchar(255),
    title varchar(255)

	PRIMARY KEY([publication-ID])
);

CREATE TABLE Publication (
    [publication-ID] varchar(255) NOT NULL,
    publisher varchar(255),
    [year of publication] int

	PRIMARY KEY([publication-ID])
);

CREATE TABLE Book (
    [publication-ID] varchar(255) NOT NULL,
    title varchar(255),
    [year of publication] int

	PRIMARY KEY([publication-ID])
);


CREATE TABLE Customer (
    [customerID] varchar(255) NOT NULL,
    [name] varchar(255)

	PRIMARY KEY([customerID])
);

CREATE TABLE BookstoreComplaint (
    [complaint-ID] varchar(255) NOT NULL,
    [company-ID] varchar(255)

	PRIMARY KEY([complaint-ID])
);

CREATE TABLE OrderComplaint (
    [complaint-ID] varchar(255) NOT NULL,
    [order-ID] varchar(255)

	PRIMARY KEY([complaint-ID])
);

CREATE TABLE Complaints (
    [complaint-ID] varchar(255) NOT NULL,
    [employee-ID] varchar(255),
	[customer-ID] varchar(255),
	[message] varchar(255),
	[status] varchar(255)

	PRIMARY KEY([complaint-ID])
);

CREATE TABLE Employee (
    [employee-ID] varchar(255) NOT NULL,
    [name] varchar(255),
	[monthly-salary] float

	PRIMARY KEY([employee-ID])
);

CREATE TABLE [Order] (
    [order-ID] varchar(255) NOT NULL,
    [shipping-addr] varchar(255),
	[shipping-cost] float,
	[timestamp] datetime,
	[customer-ID] varchar(255)

	PRIMARY KEY([order-ID])
);

CREATE TABLE Bookstore (
    [company-ID] varchar(255) NOT NULL

	PRIMARY KEY([company-ID])
);

CREATE TABLE Inventory (
    [inventory-ID] varchar(255) NOT NULL,
    [selling-price] float,
	[qty-in-stock] int,
	[publication-ID] varchar(255),
	[company-ID] varchar(255)

	--https://www.w3schools.com/sql/sql_foreignkey.asp
	PRIMARY KEY([inventory-ID]),
	FOREIGN KEY([publication-ID]) REFERENCES Publication([publication-ID]),
	FOREIGN KEY([company-ID]) REFERENCES Bookstore([company-ID])
);


CREATE TABLE [Price Records] (
    [time-updated] datetime NOT NULL,
    [inventory-ID] varchar(255) NOT NULL,
	price float

	PRIMARY KEY([time-updated], [inventory-ID])
);

CREATE TABLE [Items_In_Order] (
    [item-ID] varchar(255) NOT NULL,
    [order-ID] varchar(255) NOT NULL,
    [inventory-ID] varchar(255) NOT NULL,
    [customer-ID] varchar(255) NOT NULL,
    [item-price] float,
	[item-qty] int,
	[status] varchar(255),
	[delivery-date] datetime,
	rating float,
	comment varchar(255),
	[date-time] datetime

	PRIMARY KEY([item-ID])
);



