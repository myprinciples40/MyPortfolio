--Stored Procedure
--SQL Server stored procedure is a batch of statements grouped as a logical unit and stored in the database.
--The stored procedure accepts the parameters and executes the T-SQL statements in the procedure, returns the result set if any.
--A stored procedure is a type of code in SQL that can be stored for later use and can be used many times.
--So, whenever you need to execute the query, instead of calling it you can just call the stored procedure.
--You can also pass parameters to a stored procedure, so that the stored procedure can act based on the parameter values that is passed.

--Advantages of using stored procedure
--1.Better performance: The procedure calls are quick and efficient as stored procedures are compiled once and stored in executable form. 
--Hence the response is quick. The executable code is automatically cached, hence lowers the memory requirements.

--2.Reduce network traffic: When we use stored procedures instead of writing T-SQL queries at the application level, 
--only the procedure name is passed over the netwrok instead of the whole T-SQL code.

--3.Reusable: Stored procedures can be executed by multiple users or muliple client applications without the need of writing the code again.

--4.Security: Stored procedures reduce the threat by eliminating direct access to the tables. we can also encrypt the stored procedures 
--while creating them so that source code inside the stored procedure is not visible.

--5.It can be easily modified: We can easily modify the code inside the stored procedure without the need to restart or deploying the application.
--For example, If the T-SQL queries are written in the application and if we need to change the logic, we must change the code in the application and re-deploy it.
--SQL Server Stored procedures eliminate such challenges by storing the code in the database. so, when we want to change the logic inside the procedure we can just do it
--by simple ALTER PROCEDURE statement.

--Syntax
--Create Statement for a stored procedure
--CREATE PROCEDURE procedure_name
--AS
--sql_statement
--GO;

--ex)
--CREATE PROCEDURE SelectAllPersonAddress
--AS
--Select * FROM Person.Address
--GO;

--1.Execute a stored procedure
--Exec procedure_name;
--ex)
--EXEC SelectAllPersonAddress;

--2.Calling the stored procedure or executing the stored procedure


--Syntax of stored procedure with parameter(s)
--Create Statement for a stored procedure with parameter
--Syntax
--CREATE PROCEDURE procedure_name @param1 type, @param2 type
--AS
--sql_statement
--GO;

--Execute a stroed procedure
--EXEC procedure_name param1, param2;

--CREATE PROCEDURE SelectAllPersonAddress @City nvarchar(30)
--AS
--SELECT * FROM Person.Address where city = @city

--EXEC SelectAllPersonAddress @city = 'New York'; 
--or
--EXEC SelectAllPersonAddress 'New York'; 


CREATE PROCEDURE SelectedAllPersonAddress
AS
SELECT * FROM Person.Address
GO;

EXEC SelectedAllPersonAddress; --or EXECUTE

DROP PROCEDURE SelectedAllPersonAddress;

CREATE PROCEDURE SelectedAllPersonAddressWithParams (@city nvarchar(30))
AS
--BEGIN
SET NOCOUNT ON

SELECT * FROM Person.Address where City = @city;
--END

EXEC SelectedAllPersonAddressWithParams @city = 'New York'; 
EXEC SelectedAllPersonAddressWithParams 'Miami'; 

DROP PROCEDURE SelectedAllPersonAddressWithParams

--set the parameter value from the first
CREATE PROCEDURE SelectedAllPersonAddressWithParams (@city nvarchar(30) = 'New York')
AS
BEGIN
SET NOCOUNT ON
SELECT * FROM Person.Address where City = @city;
END

EXEC SelectedAllPersonAddressWithParams

--if I want to change the information of the procedure, with more parameters
ALTER PROCEDURE [dbo].[SelectedAllPersonAddressWithParams] (@city nvarchar(30) = 'New York', @stateProvinceID INT)
AS
BEGIN
SET NOCOUNT ON
SELECT * FROM Person.Address where City = @city;
END

EXEC SelectedAllPersonAddressWithParams @city = 'New York', @stateProvinceID = 30; 

--with encription(option): It is not available to modify - but, it is not recommended to use this way
CREATE PROCEDURE SelectedAllPersonAddressWithParamsWithEncryption (@city nvarchar(30) = 'New York')
WITH ENCRYPTION
AS
BEGIN
SET NOCOUNT ON
SELECT * FROM Person.Address where City = @city;
END

DROP PROCEDURE SelectedAllPersonAddressWithParamsWithEncryption