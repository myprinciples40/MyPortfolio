--Change the tempDB file size
USE [master]
GO
ALTER DATABASE [tempdb] ADD FILE ( NAME = N'temp3', FILENAME = N'C:\SQL_TEMPDB\temp3.ndf' , SIZE = 131072KB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [tempdb] ADD FILE ( NAME = N'temp4', FILENAME = N'C:\SQL_TEMPDB\temp4.ndf' , SIZE = 131072KB , FILEGROWTH = 65536KB )
GO


--Test
Create table #myTempTable (ID INT IDENTITY(1,1), Stuffing varchar(100));
GO

DROP TABLE #myTempTable


--Check the TempDB to another location
SELECT name, physical_name AS CurrentLocation
FROM sys.master_files
WHERE database_id = DB_ID(N'tempdb');
GO

--Make a new folder and use it for the file path below
--Move the TempDB to another location
USE master;
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = tempdev, FILENAME = 'C:\SQL_TEMPDBNew\tempdb.mdf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp2, FILENAME = 'C:\SQL_TEMPDBNew\tempdb_mssql_2.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp3, FILENAME = 'C:\SQL_TEMPDBNew\temp3.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp4, FILENAME = 'C:\SQL_TEMPDBNew\temp4.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = templog, FILENAME = 'C:\SQL_TEMPDBNew\templog.ldf');
GO


--Put it back
USE master;
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = tempdev, FILENAME = 'C:\SQL_TEMPDB\tempdb.mdf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp2, FILENAME = 'C:\SQL_TEMPDB\tempdb_mssql_2.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp3, FILENAME = 'C:\SQL_TEMPDB\temp3.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = temp4, FILENAME = 'C:\SQL_TEMPDB\temp4.ndf');
GO
ALTER DATABASE tempdb
MODIFY FILE (NAME = templog, FILENAME = 'C:\SQL_TEMPDB\templog.ldf');
GO



--MSDB Database
USE [master]
GO
ALTER DATABASE [msdb] MODIFY FILE ( NAME = N'MSDBData', SIZE = 102400KB , FILEGROWTH = 102400KB )
GO
ALTER DATABASE [msdb] MODIFY FILE ( NAME = N'MSDBLog', SIZE = 40960KB , FILEGROWTH = 10240KB )
GO

--Change SQL Server Agent to Stop status

--Check the version of MSSQL
Select @@VERSION

--Script 2: Get information of backup file
RESTORE HEADERONLY
FROM DISK = N'C:\SQL_BACKUPS\msdb_beforedelete.bak'
GO

--Microsoft SQL Server 2016 (SP2) (KB4052908) - 13.0.5026.0 (X64)   Mar 18 2018 09:11:49   
--Copyright (c) Microsoft Corporation  Enterprise Evaluation Edition (64-bit) on Windows Server 2019 Datacenter 10.0 <X64> (Build 17763: ) (Hypervisor) 

--MSDB - Task - Restore - Database

--After restoring the msdb, chagne SQL Server Agent to Start


--Model Database
CREATE DATABASE TEST


--After creating the table and data in model database directly ([dbo].[DatabaseBasicInfo])
CREATE DATABASE testAfterModelUpdate;

--to fix the trouble (Blocking) - master
sp_who2 active
--after active above query and find the block number 
--kill <blocked number>
--restart the query:  CREATE DATABASE testAfterModelUpdate;

--Check(모델 데이터 베이스에서 테이블과 내용을 넣어놨기 때문에 자동으로 모두 등록되서 나온다.)
USE testAfterModelUpdate
Select * FROM [dbo].[DatabaseBasicInfo]
GO

--Resource section
SELECT SERVERPROPERTY('rESEOURCEvERSION')

SELECT SERVERPROPERTY('rESEOURCELastUpdateDateTime')

SELECT OBJECT_DEFINITION(OBJECT_ID('sys.objects'));
GO