DBCC CHECKDB ('AdventureWorks2016')

DBCC CHECKDB ('AdventureWorks2016') with NO_INFOMSGS

--Three sentences all together. Becuase other user can login.
alter database AdventureWorks2016 set single_user with rollback immediate
go
--This is safe.
DBCC CHECKDB ('AdventureWorks2016', REPAIR_REBUILD)
go
alter database AdventureWorks2016 set multi_user with rollback immediate
go

--This is unsafe. It will make the data loss.
DBCC CHECKDB ('AdventureWorks2016', REPAIR_ALLOW_DATA_LOSS)

------------------------------------------------
--Page Level Restore / Recovery using full backups
Use master
GO
DROP DATABASE IF EXISTS [CorruptionTest]
GO
CREATE DATABASE [CorruptionTest]
GO
ALTER DATABASE [CorruptionTest] SET RECOVERY FULL;
GO
ALTER DATABASE [CorruptionTest] SET PAGE_VERIFY CHECKSUM
GO
CREATE TABLE [CorruptionTest].[dbo].[mssqltips_online]
(increment INT, randomGUID uniqueidentifier, randomvalue INT, BigCol CHAR(2000) DEFAULT 'a',
INDEX CIX_MSSQLTips_increment1 UNIQUE CLUSTERED (increment))
GO
CREATE TABLE [CorruptionTest].[dbo].[mssqltips_corrupt]
(increment INT, randomGUID uniqueidentifier, randomvalue INT, BigCol CHAR(2000) DEFAULT 'a',
INDEX CIX_MSSQLTips_increment1 UNIQUE CLUSTERED (increment))
GO

SET NOCOUNT ON;
DECLARE @counter INT = 1;
BEGIN TRAN
	WHILE @counter <= 250000
	BEGIN
		INSERT INTO CorruptionTest.dbo.mssqltips_online (increment, randomGUID, randomvalue)
		VALUES (@counter, NEWID(), ABS(CHECKSUM(NewID())) % 140000000)

		INSERT INTO CorruptionTest.dbo.mssqltips_corrupt(increment, randomGUID, randomvalue)
		VALUES (@counter, NEWID(), ABS(CHECKSUM(NewID())) % 140000000)

		SET @counter += 1
	END;
COMMIT TRAN;
GO

SELECT COUNT(1) FROM [dbo].[mssqltips_corrupt]
SELECT COUNT(1) FROM [dbo].[mssqltips_online]

DBCC CHECKDB('CorruptionTest') WITH NO_INFOMSGS
GO
BACKUP DATABASE [CorruptionTest] TO DISK = 'C:\SQL_BACKUPS\CorruptionTest_Full.BAK' WITH COMPRESSION
GO


SELECT TOP 10
	sys.fn_PhysLocFormatter(%%physloc%%) PageId,
	*
FROM [dbo].[mssqltips_corrupt]
GO

--32263129 (1:354:1 randomvalue)

DBCC TRACEON (3604);
GO
DBCC PAGE ('CorruptionTest', 1, 354, 3); --3 means column
GO

SELECT CONVERT(VARBINARY(8), 51337673)
GO
--0x030F59C9 : hexa-decimal number above

--intent on the corruption (Don't do this!) - CHANGE THE PAGE NUMBER
USE master
GO
ALTER DATABASE [CorruptionTest] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
DBCC WRITEPAGE ('CorruptionTest', 1, 354, 2151, 4, 0x030F59C9, 1)
GO
ALTER DATABASE [CorruptionTest] SET MULTI_USER;
GO

--After this restore the page