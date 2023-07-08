--20230428Select in details
--Select expressions
--FROM table_name
--[WHERE clause]
--[GROUP BY clause]
--[HAVING clause]
--[ORDER BY clause]

USE AdventureWorks2016
--Separate statement 'Go'
GO  

SELECT *
FROM Person.Address;

SELECT AddressID, City, ModifiedDate
FROM Person.Address

SELECT City, AddressID, ModifiedDate
FROM Person.Address

SELECT Top 10 *
FROM Person.Address

SELECT Top 10 City as [City Name], AddressID, ModifiedDate, PostalCode
FROM Person.Address
WHERE PostalCode = '98104'

SELECT AddressID, City, ModifiedDate, StateProvinceID
FROM PERSON.Address
WHERE (City = 'Seattle' AND AddressID <> 105)
or (ModifiedDate > '2014-02-01' AND ModifiedDate < '2014-02-28')

---------------------------------

--SQL Operators
--Arithmetic operators
--Relational operators
--!= or <> ex) x != y or x <> y (x is not equal to y)
--Logical operators
--AND / OR / NOT / BETWEEN...AND / IS NULL / IS NOT NULL / LIKE / UNIQUE / IN, NOT IN etc.

---------------------------------

--WHERE Clause
--The WHERE clause places conditions on the selected columns.
--Whereas the HAVING clause places conditions on groups created by the GROUP BY clause.

select * from Person.address where postalcode = '98011'

select * from Person.address where postalcode != '98011' --19588

select * from Person.address where postalcode <> '98011' --19588

select count(*) from Person.address where postalcode <> '98011'

select * from Person.address where ModifiedDate >= '2013-11-08 00:00:00'

select * from Person.address where ModifiedDate <= '2013-11-08 00:00:00'

select * from Person.Person where FirstName like 'mat%'

select * from Person.Person where FirstName like '%ew'

select * from Person.Person where FirstName like '%EW'

select * from [HumanResources].[EmployeePayHistory]

select max(rate) from [HumanResources].[EmployeePayHistory]

select max(rate) AS MaxPayrate from [HumanResources].[EmployeePayHistory]

select min(rate) AS [Min Pay rate] from [HumanResources].[EmployeePayHistory]

select * from [Production].[ProductCostHistory] where startdate = '2013-05-30 00:00:00' --195

select * from [Production].[ProductCostHistory] where startdate = '2013-05-30 00:00:00' and StandardCost >= 200 --94

select * from [Production].[ProductCostHistory] where(startdate = '2013-05-30 00:00:00' and StandardCost >= 200) or ProductID > 800 --250

select * from [Production].[ProductCostHistory] where(startdate = '2013-05-30 00:00:00' and StandardCost >= 200) and ProductID > 800 --62

select MAX(StandardCost) as [Max Standard Cost] from [Production].[ProductCostHistory] where startdate = '2013-05-30 00:00:00' and StandardCost >= 200

select * from [Production].[ProductCostHistory] where ProductID IN (802,803,820,900) --IN means that you can use multiple values to find out.

select * from [Production].[ProductCostHistory] where EndDate is null --195 (NULL is not empty.)

select * from [Production].[ProductCostHistory] where EndDate is not null --200

---------------------------------

--GROUP BY Clause
--Often used with aggregate function (COUNT(), MAX(), MIN(), SUM(), AVG()) to group the result-set by one or more columns.

select * from Person.address

SELECT COUNT(*) AS [Number of People], City, PostalCode
FROM PERSON.Address
GROUP BY City, PostalCode
Having PostalCode > '50000'
ORDER BY City ASC 

select * from Person.address where postalcode = '98011' --26

select COUNT(*) from Person.address where postalcode = '98011' --26

select COUNT(*) from Person.address where postalcode = '98225' --213

select count(*), postalcode from Person.address group by PostalCode

select count(*) as NoOfAddresses,postalcode from Person.address group by PostalCode

select count(*) as NoOfAddresses,postalcode from Person.address group by PostalCode order by PostalCode DESC

select count(*),City from Person.address group by City order by City DESC

select count(*) as Number,City,PostalCode from Person.address group by City,PostalCode order by City DESC

---------------------------------

--HAVING Clause

select * from Production.product

select count(*) as countofproduct,Color from Production.product where color = 'yellow' group by Color

select count(*) as countofproduct,Color from Production.product group by Color having Color = 'yellow'

select count(*) as countofproduct,Color,Size from Production.product group by Color,size having Size >= '44'

select count(*) as countofproduct,Color,Size from Production.product group by Color,size having Size >= '44' order by size asc

---------------------------------

--ORDER BY Clause
--It follows group by columns.

SELECT AddressID, City, ModifiedDate, StateProvinceID
FROM PERSON.Address
WHERE (City = 'Seattle' AND AddressID <> 105)
or (ModifiedDate > '2014-02-01' AND ModifiedDate < '2014-02-28')
ORDER BY ModifiedDate, StateProvinceID DESC 

SELECT * FROM [HumanResources].[EmployeePayHistory] order by rate

SELECT * FROM [HumanResources].[EmployeePayHistory] order by rate ASC

SELECT * FROM [HumanResources].[EmployeePayHistory] order by rate DESC

SELECT * FROM [HumanResources].[EmployeePayHistory] WHERE ModifiedDate >= '2010-06-30' order by ModifiedDate DESC

SELECT * FROM [HumanResources].[EmployeePayHistory] WHERE YEAR(ModifiedDate) >= '2013' order by ModifiedDate DESC

select * from [HumanResources].[EmployeePayHistory] where  month(ModifiedDate) >= '11' order by ModifiedDate desc

select * from [HumanResources].[EmployeePayHistory] where  month(ModifiedDate) = '11' order by ModifiedDate desc