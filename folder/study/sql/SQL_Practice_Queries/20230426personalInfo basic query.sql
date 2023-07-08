
--Create
CREATE TABLE [dbo].[personalInfo](
	[ID] [int] NOT NULL,
	[firstName] [varchar](50) NULL,
	[lastName] [varchar](50) NULL,
	[dob] [datetime] NULL
) ON [PRIMARY]
GO


--Create with Primary key
CREATE TABLE [dbo].[personalInfo](
	[ID] [int] NOT NULL,
	[firstName] [varchar](50) NULL,
	[lastName] [varchar](50) NULL,
	[dob] [datetime] NULL,
 CONSTRAINT [PK_personalInfo] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO



--Insert
INSERT INTO [dbo].[personalInfo]
           ([ID]
           ,[firstName]
           ,[lastName]
           ,[dob])
     VALUES
           (777
           ,'Jin'
           ,'Kim'
           ,'08/07/1983')
GO


SELECT *
FROM personalInfo
GO


--Update
SELECT TOP (1000) [ID]
      ,[firstName]
      ,[lastName]
      ,[dob]
  FROM [OurFirstDatabase].[dbo].[personalInfo]


  USE [OurFirstDatabase]
GO

UPDATE [dbo].[personalInfo]
   SET [ID] = <ID, int,>
      ,[firstName] = <firstName, varchar(50),>
      ,[lastName] = <lastName, varchar(50),>
      ,[dob] = <dob, datetime,>
 WHERE <검색 조건,,>
GO

--DML (Data Manipulation Language)
Select *
from personalInfo
WHERE dob > '2000';

Insert into personalInfo (ID, firstName, lastName, dob) values (779, 'Naeun', 'Kim', '2020-03-11');

update personalInfo set id = 780 where firstName = 'Naeun';
update personalInfo set id = 779 where firstName = 'Narin';

Insert into personalInfo (ID, firstName, lastName, dob) values (778, 'Ellie', 'Kim', '1985-02-19');

Insert into personalInfo (ID, firstName, lastName, dob) values (781, 'Hangok', 'Kang', '1957-04-30');

Delete from personalInfo where firstName = 'Hangok';

--Both way Truncate and Drop can not recover the data after the execution.
--Truncate : delete all contents in the table (Same condition with creating a table at first) - automatically commit;
--Drop  : remove everything including the table - automatically commit;

Select *
From personalInfo

Alter Table personalInfo 
ADD phoneNo int,
interest varchar(50);

Alter table personalInfo
Alter column phoneNo varchar(50);

update personalInfo set phoneNo = '0451046639', interest = 'Reading books'
Where ID = 777;

update personalInfo set phoneNo = '0434892017', interest = 'Learning'
Where ID = 778;

update personalInfo set interest = 'Dancing'
Where ID = 779;

update personalInfo set interest = 'Singing'
Where ID = 780;

--DCL statements defined..
--DCL is short name of Data Control Language.
--It defines the control over the data in the database.
--GRANT statement used to grant SQL SELECT, UPDATE, INSERT, DELETE, and other privileges on tables or views.
--REVOKE is used to cancel previously granted or denied permissions.
--Examples> GRANT UPDATE ON ORDER_BACKLOG TO JONES WITH GRANT OPTION
--GRANT SELECT ON TABLE Q.STAFF TO PUBLIC
--REVOKE DELETE ON employees FROM anderson;
--REVOKE ALL ON employees FROM anderson;

--TCL statements.. (Transactions Control Language)
--It is used to manage the changes made by DML statements like  delete update insert.
--It also allows the statements to be grouped together into logical transactions.
--COMMIT statements is used to permanently save any transaction into the database.
--When we use Commit in any query then the channge made by that query will be permanent and visible.
--ROLLBACK statement is used to undo the changes made by any command but only before a commit is done.

--ex)
begin tran d
update personalInfo set phoneNo = '0451046639', interest = 'Reading books'
Where ID = 777
commit tran d;

--ex)
DECLARE @visitCount int
BEGIN TRANSACTION AddVisit
--INSERT INTO personalInfo VALUES (781, 'Mia', 'Park', '1985-09-29', '0478740486', 'Studying')
SELECT @visitCount = COUNT(*) FROM personalInfo WHERE firstName = 'MIA'
IF @visitCount > 1
BEGIN
ROLLBACK TRANSACTION ADDVisit
PRINT 'A person with the same name already exists'
END
ELSE
BEGIN
COMMIT TRANSACTION AddPerson
PRINT 'New person added successfully'
END

--In TCL, SAVEPOINT statement is used to temporarily save a transaction so that you can roll back to that point whenever necessary.
--Savepoint names must be distinct within a given transaction.
--ex)
UPDATE personalInfo SET interest = 'Coffee' WHERE firstName = 'Mia';
SAVEPOINT TRANSACTION mia_interest;
UPDATE personalInfo SET interest = 'Nurturing' WHERE firstName = 'Ellie';
SAVEPOINT TRANSACTION ellie_interest;
SELECT * FROM personalInfo;
ROLLBACK TRANSACTION mia_interest;
UPDATE personalInfo SET interest = 'Business' WHERE firstName = 'Ellie'
COMMIT;

sp_rename '[Books].[ID]', 'bookID';
sp_rename '[personalInfo].[ID]', 'personID';