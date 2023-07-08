--INSERT INTO tableName (column1, column2, column3...column) VALUES (value1, value2, value3...valueN)
--ex) INSERT INTO StaffSales (staffID, fName, lName) VALUES (100, 'Kohn', 'King')
--ex) INSERT INTO StaffSales1 VALUES (200, 'Michael', 'Blythe'),
--									 (300, 'Linda', 'Mitchell'),
--									 (400, 'Jillian', 'Carson'),
--									 (500, 'Garrett', 'Vargas');

--Inserting from other tables
--Syntax) INSERT tableName1 (value1, value2)
--	  Select value11, value12 from tableName2 where(condition)

--SELECT INTO statement (copy the whole table for a backup the data)
--Syntax) SELECT select_list INTO destination FROM source [WHERE condition];
--ex) SELECT * INTO marketing.customers FROM sales.customers;

CREATE TABLE [dbo].[salesstaff](
	[staffid] [int] NOT NULL PRIMARY KEY,
	[fName] [nvarchar](30) NOT NULL,
	[lName] [nvarchar](30) NOT NULL
)

--BULK INSERT Method
BULK INSERT customer_coordination
FROM 'C:\1.Jin\1.IT\Programming\Data_Analytics\Customer_Coordination' --file location
WITH (FORMAT = 'CSV',
		FIRSTROW = 2, --Starting point
		FIELDQUOTE = '"',
		FIELDTERMINATOR = ',',
		ROWTERMINATOR = '0x0a'); --or '\n'


INSERT INTO salesstaff (staffid, fName, lName) VALUES (200, 'Jin', 'Hwan')

SELECT * FROM salesstaff

update salesstaff set fName = 'Jinhwan' where fName = 'Jin';
update salesstaff set lName = 'Kim' where lName = 'Hwan';

INSERT INTO salesstaff (staffid, fName, lName) VALUES (300, 'Ellie', 'Kim'),(325, 'Narin', 'Kim'), (314, 'Naeun', 'Kim');


CREATE TABLE salesstaffnew (
	ID int NOT NULL IDENTITY PRIMARY KEY,
	[staffid] [int] NOT NULL,
	[fName] [nvarchar](30),
	[lName] [nvarchar](30)
)

SELECT * FROM salesstaffnew

INSERT INTO salesstaffnew (staffid, fName, lName) VALUES (200, 'Jinhwan', 'Kim')
INSERT INTO salesstaffnew (staffid, fName, lName) VALUES (300, 'Ellie', 'Kim'),(325, 'Narin', 'Kim'), (314, 'Naeun', 'Kim');

CREATE TABLE nameOnlyTable (
	[fName] [nvarchar](30),
	[lName] [nvarchar](30)
)

SELECT * FROM nameOnlyTable

--Inserting from other tables
INSERT nameOnlyTable (fName, lName)
SELECT fName, lName from salesstaffnew WHERE id >= 3;

--copy the whole table for a backup the data
SELECT * INTO salesstaffnew_bkp FROM salesstaffnew

SELECT * FROM salesstaffnew_bkp