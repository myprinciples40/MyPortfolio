--[HumanResources].[EmployeePayHistory]
--[Production].[Product]
--[Production].[ProductInventory]

SELECT * FROM [AdventureWorks2016].[HumanResources].[EmployeePayHistory] -- Database name.Schema name.Table name

--All Tables get created in DBO SCHEMA unless we create a new schema

SELECT * FROM [dbo].[Employee]

SELECT * FROM [HumanResources].[EmployeePayHistory] 
WHERE BusinessEntityID IN (SELECT BusinessEntityID FROM [HumanResources].[EmployeePayHistory] WHERE Rate > 60)

--Subquery returned more than 1 value. Thus, it is not working.
SELECT * FROM [HumanResources].[EmployeePayHistory] 
WHERE BusinessEntityID = (SELECT BusinessEntityID FROM [HumanResources].[EmployeePayHistory] WHERE Rate > 60)

--In the select in the subquery select we are also returning one rows.
SELECT * FROM [HumanResources].[EmployeePayHistory] 
WHERE BusinessEntityID = (SELECT BusinessEntityID FROM [HumanResources].[EmployeePayHistory] WHERE Rate = 39.06)


SELECT * FROM [Production].[ProductInventory]

SELECT * FROM [Production].[Product]
WHERE ProductID IN (SELECT ProductID FROM [Production].[ProductInventory] WHERE Quantity >= 300)


