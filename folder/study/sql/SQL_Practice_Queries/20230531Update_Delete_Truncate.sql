--UPDATE Statement Syntax
--UPDATE tableName SET column1 = value1, column2 = value2...columnN = ValueN
--ex)
--UPDATE StaffSales SET SQuota = 500000
--UPDATE StaffSales SET SQuota = SQuota + 50000
--UPDATE StaffSales SET SQuota = SQuota + 50000, SYTD = 0, SLastYear = SLastYear * 1.05
--UPDATE StaffSales SET TerritoryName = 'UK' WHERE TerritoryName = 'United Kingdom'

--You might want to update data in one table with data from another table.
--ex)
--UPDATE StaffSales SET SalesQuota = sp.SalesQuata FROM SalesStaff ss INNER JOIN Sales.vSalesPerson sp ON ss.FullName = (sp.FirstName + ' ' + sp.LastName);

--Copy data to SalesStaff from View
SELECT
FirstName + ' ' + LastName AS FullName,
TerritoryName,
TerritoryGroup,
SalesQuota,
SalesYTD,
SalesLastYear
into SalesStaff
FROM 
Sales.vSalesPerson

Select * From [Sales].[vSalesPerson]
DROP TABLE SalesStaff

SELECT * FROM SalesStaff

UPDATE  SalesStaff SET SalesQuota = 500000.00
UPDATE  SalesStaff SET SalesQuota = SalesQuota + 1500000.00
UPDATE  SalesStaff SET SalesQuota = SalesQuota + 1500000.00, SalesYTD = SalesYTD - 500, salesLastYear = salesLastYear * 1.50;

UPDATE  SalesStaff SET TerritoryName = 'UK' where TerritoryName = 'United Kingdom'

UPDATE  SalesStaff SET TerritoryName = 'UK' where TerritoryGroup is null and FullName = 'Syed Abbas'

UPDATE  SalesStaff SET TerritoryName = 'UK', TerritoryGroup = 'Europe' where TerritoryGroup is null and FullName = 'Syed Abbas'


UPDATE SalesStaff SET SalesQuota = sp.salesquota
FROM SalesStaff ss
INNER JOIN sales.vSalesPerson sp
ON ss.FullName = (sp.FirstName + ' ' + sp.LastName);



--Delete Statement Syntax (DML - Data Manipulation Language)
--DELETE tablename;
--DELETE from tablename (optional)
--Remember DELETE works on the rows not one the columns unlike UPDATE statement.

--ex)
--DELETE FROM SalesStaff WHERE CountryRegion = 'United States';
--DELETE TOP (20) PERCENT FROM SalesStaff;

--DELETE using data from other table
--1. One approach is to is to create a subquery that retrieves data from the other table.
--ex)
--DELETE SalesStaff WHERE StaffID IN (SELECT BusinessEntityID FROM Sales.vSalesPerson WHERE SalesLastYear = 0);
--2. Another method you can use to achieve the same result is to join between the target table and the table that contains the lookup information.
--ex)
--DELETE SalesStaff FROM Sales.vSalesPerson sp INNER JOIN dbo.SalesStaff ss ON sp.BusinessEntityID = ss.StaffID WHERE sp.SalesLastYear = 0;

CREATE TABLE SalesStaff (
	StaffID int NOT NULL PRIMARY KEY,
	FirstName nvarchar(50) NOT NULL,
	LastName nvarchar(50) NOT NULL,
	CountryRegion nvarchar(50) NOT NULL
)

DROP TABLE SalesStaff

SELECT * FROM SalesStaff

INSERT INTO SalesStaff 
Select BusinessEntityID, FirstName, LastName, CountryRegionName from sales.vSalesPerson;

DELETE SalesSTaff;
DELETE FROM SalesSTaff;

DELETE FROM SalesStaff WHERE CountryRegion = 'United States';

--To avoid incorrectly querying (only performed in memory so far)
BEGIN TRAN
DELETE FROM SalesStaff WHERE CountryRegion = 'United States';

--Return the result
ROLLBACK TRAN

--Run and get a final result
COMMIT

--DELETE using data from other table No1.
DELETE SalesStaff 
WHERE StaffID IN
(SELECT BusinessEntityID from Sales.SalesPerson WHERE SalesLastYear = 0);

--DELETE using data from other table No2.
DELETE SalesStaff
From Sales.vSalesPerson sp
INNER JOIN SalesStaff ss
ON sp.BusinessEntityID = ss.StaffID
WHERE sp.SalesLastYear = 0;



--TRUNCATE (DDL - Data Definition Language)
--If you truncate a table, the TRUNCATE TABLE statement can not be rolled back in some databases.
--Syntax
--TRUNCATE TABLE table_name;
--ex)
--TRUNCATE TABLE customers;

Select * from SalesStaff

TRUNCATE TABLE SalesStaff;

CREATE TABLE EmployeeNew (
	ID INT IDENTITY(1,1) NOT NULL,
	EmployeeName nvarchar(50) NOT NULL
);	

INSERT INTO [dbo].[EmployeeNew]
           ([EmployeeName])
     VALUES
           ('Jinhwan'),('Dongjung'),('Narin'),('Naeun')
GO

Select * from EmployeeNew

DELETE FROM EmployeeNew --IDENTITY just follows from the last number.

INSERT INTO EmployeeNew (EmployeeName) VALUES ('Jinhwan');

TRUNCATE TABLE EmployeeNew --IDENTITY number will reset.