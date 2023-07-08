--Function
--Function can return an only single value or a table.
--We can't use a function to Insert, Update, Delete records in the database table(s).
--We can't call us stored procedure in a select statement or on an insert statement or a delete statement while we can call a function in any of these statement.
 --You could create a stored procedure to group a set of SQL statement and execute them, however,
 --stored procedures cannot be called within SQL statements. Functions, on the other hand, can be.

 --Syntax
 --CREATE FUNCTION [database_name]function_name (parameters)
 --    RETURNS data_type AS
 --    BEGIN
 --    SQL statements
 --    RETURN value
 --    END;

 --1.Built-in Functions
 --SQL server adds some built-in functions to every database.
 --Built-in functions are grouped into different types depending upon their functionality.
 --1)Scalar Function: Scalar functions operate on a single value and return a single value.
 --upper('dotnet'), lower('DOTNET'), convert(int, 15.56)
 --2)Aggregate Functions: Aggregate functions operate on a collection of values and return a single value.
 --max(), min(), avg(), count()
 --3)Date and Time Functions: Related to date and time
 --GETDATE(), Datediff(), DateAdd(), Day(), Month(), Year()

 --2.User-Defined Functions
 --These functions are created by the user in the system database or in a user-defined database.
 --1)Scalar function: The user-defined scalar function also returns a single value as a result of actions performed by the function.
 --2)Inline Table-Valued Function: The user-defined inline table-valued function returns a table variable as a result of actions performed by the function.
 --3)Multi-Statement Table-Valued Function: A user-defined multi-statement table-valued function returns a table variable as a result of actions performed by the function.
 --In tthis, a table variable must be explicitly declared and defined whose value can be derived from multiple SQL statements.

 --Defference between Functions and Stored Procedures - Important!!!
 --The function must return a value but in Stored Procedure it is optional. Even a procedure can return zero or in values.
 --Functions can have only input parameters for it whereas Procedures can have input or output parameters.
 --Functions can be called from Procedure whereas Procedures cannot be called from a Function.
 --The procedure allows SELECT as well as DML(INSERT/UPDATE/DELETE) statement in it whereas Function allows only SELECT statement in it.
 --Procedures can't be utilized in a SELECT statement whereas Function can be embedded in a SELECT statement.
 --Stored Procedures can't be used in the SQL statements anywhere in the WHERE/HAVING/SELECT section whereas Function can be.
 --An exception can be handled by try-catch block in a Procedure whereas try-catch block can't be used in a Function.
 --We can use Transactions in Procedure whereas we can't use Transactions in Function.

 --1.Built-in Functions
SELECT * FROM [HumanResources].[EmployeePayHistory]

SELECT MAX(Rate) FROM [HumanResources].[EmployeePayHistory]

SELECT MIN(Rate) FROM [HumanResources].[EmployeePayHistory]

SELECT COUNT(Rate) FROM [HumanResources].[EmployeePayHistory]

SELECT SUM(Rate) FROM [HumanResources].[EmployeePayHistory]

SELECT AVG(Rate) FROM [HumanResources].[EmployeePayHistory]

PRINT UPPER('dotnet')

PRINT LOWER('dotnet')

PRINT convert(int, 15.56)

SELECT CONVERT(int, Rate) FROM [HumanResources].[EmployeePayHistory]

PRINT GETDATE()

PRINT DAY(GETDATE())

PRINT MONTH(GETDATE())

PRINT YEAR(GETDATE())


 --2.User-Defined Functions
 CREATE TABLE FunctionEmployee (
	EmpID INT PRIMARY KEY NOT NULL,
	FirstName varchar(50) NULL,
	LastName varchar(50) NULL,
	Salary INT NULL,
	Address varchar(100) NULL,
 );

Insert into FunctionEmployee (EmpID, FirstName, LastName, Salary, Address) values (1, 'Jinhwan', 'Kim', 220000, 'Bendigo');
Insert into FunctionEmployee (EmpID, FirstName, LastName, Salary, Address) values (2, 'Dongjung', 'Kim', 350000, 'Bendigo');
Insert into FunctionEmployee (EmpID, FirstName, LastName, Salary, Address) values (3, 'Narin', 'Kim', 170000, 'Bendigo');
Insert into FunctionEmployee (EmpID, FirstName, LastName, Salary, Address) values (4, 'Naeun', 'Kim', 170000, 'Bendigo');

SELECT * FROM FunctionEmployee


--Create a Scalar Function
CREATE FUNCTION fnGetEmpFullName
(@FirstName varchar(50), @LastName varchar(50))
RETURNS varchar(101)
AS
BEGIN
RETURN (SELECT @FirstName + ' ' + @LastName)
END;


--Using Function has created
SELECT dbo.fnGetEmpFullName (FirstName, LastName) as FullName, Salary from FunctionEmployee --We can't use PROCEDURE here.

SELECT FirstName + ' ' + LastName as FullName, Salary from FunctionEmployee --We can't use PROCEDURE here.

--Inline-table Function
CREATE FUNCTION fnGetEmployee()
returns Table
as 
return (SELECT * from FunctionEmployee)

SELECT * FROM dbo.fnGetEmployee()


--Multi-Statement Table-Valued Function
--It is available only in the temporary table not in the original table to use Function to update something. 
CREATE FUNCTION fnGetMulEmployee()
returns @Emp Table (
	EmpID int,
	FirstName varchar(50),
	Salary INT
)
AS
BEGIN
INSERT INTO @Emp SELECT e.EmpID, e.FirstName, e.Salary from FunctionEmployee e;
--Now update salary of first employee
UPDATE @Emp SET Salary=250000 where EmpID=1;
--It will update only in @Emp table not in original Employee table
Return
end;

SELECT * FROM dbo.fnGetMulEmployee()



--What is a Trigger
--SQL Server triggers are special stored procedures that are executed automatically in response to the database object, database, and server events.
--Data Manipulation Language (DML) triggers which are invoked automatically in response to INSERT, UPDATE, and DELETE events against tables.
--Data Definition Language (DDL) triggers which fire in response to CREATE, ALTER, and DROP statements. 
--DDL triggers also fire in response to some system stored procedures that perform DDL-like operations. (or multiple tables)
--Logon Triggers which fire in response to LOGON events. (who and when)

--Syntax
--The CREATE TRIGGER statement allows you to create a new trigger this is fired automatically whenever an event such as INSERT, DELETE, or UPDATE occurs against a table.
--CREATE TRIGGER [schema_name].trigger_name
--    ON table_name
--    AFTER {[INSERT],[UPDATE],[DELETE]}
--    [NOT FOR REPLICATION]  --advanced concept: It means it do not get in call when we are doing replicating that table
--    AS
--    {sql_statements}

--ex)
--CREATE TRIGGER AfterInsertTrigger 
--    ON TriggerDemo_Parent
--    AFTER INSERT
--    AS
--    INSERT INTO TriggerDemo_History VALUES ((SELECT TOP 1 ID FROM TriggerDemo_Parent), 'Insert')
--    GO

--CREATE TRIGGER AfterDeleteTrigger 
--    ON TriggerDemo_Parent
--    AFTER DELETE
--    AS
--    INSERT INTO TriggerDemo_History VALUES ((SELECT TOP 1 ID FROM TriggerDemo_Parent), 'DELETE')
--    GO

--CREATE TRIGGER AfterUPDATETrigger 
--    ON TriggerDemo_Parent
--    AFTER UPDATE
--    AS
--    INSERT INTO TriggerDemo_History VALUES ((SELECT TOP 1 ID FROM TriggerDemo_Parent), 'UPDATE')
--    GO


CREATE TRIGGER EmployeeInsert
   ON  Employee
   AFTER INSERT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for trigger here
	INSERT INTO EmployeeTriggerHistory VALUES ((SELECT MAX(EmpID) FROM Employee), 'INSERT')
END
GO

SELECT * FROM EmployeeTriggerHistory --After CREATE TABLE

SELECT * FROM Employee

DELETE FROM EmployeeTriggerHistory

INSERT INTO Employee (EmpID, EmpName, EmpTitle) VALUES (9, 'Mia', 'Accountant')

UPDATE Employee SET EmpID = 1 WHERE EmpID = 3
UPDATE Employee SET EmpID = 2 WHERE EmpID = 8
UPDATE Employee SET EmpID = 3 WHERE EmpID = 9
UPDATE Employee SET EmpID = 4 WHERE EmpID = 11
UPDATE Employee SET EmpID = 5 WHERE EmpID = 79
UPDATE Employee SET EmpID = 6 WHERE EmpID = 100
UPDATE Employee SET EmpID = 7 WHERE EmpID = 101
UPDATE Employee SET EmpID = 8 WHERE EmpID = 105
UPDATE Employee SET EmpID = 9 WHERE EmpID = 200



