-- Backup and restore master database
--<Scenario 1> - with well connection 
-- 1. Create TestUser
-- 2. Backup master database
BACKUP DATABASE [master] TO  DISK = N'C:\SQL_BACKUPS\master_backup_test.bak' WITH NOFORMAT, NOINIT,  NAME = N'master-전체 데이터베이스 백업', SKIP, NOREWIND, NOUNLOAD,  STATS = 10
GO

-- 3. Delete the TestUser
-- 4. Before restoring the master database, the database must be running in single user mode.
-- Go to cmd -> net stop mssqlserver -> y -> net start mssqlserver /m  (single user mode online)

-- 5. Restore master database
restore database master from disk = 'C:\SQL_BACKUPS\master_backup_test.bak' with replace;

-- 6. Go to cmd -> net start mssqlserver
-- 7. Start SQL Server Agent
-- 8. Checking if there is no corruption
DBCC CHECKDB

--<Scenario 2> - when the connection is not working
-- 1. Login to the same version of test SQL SERVER
-- 2. window icon -> right click -> Computer management -> user select
-- 3. sql server configuration management -> sql server right click -> properties -> browse -> new user select
-- 4. excute the query below
restore database master_recovery from disk = 'C:\SQL_BACKUPS\master_backup_test.bak' with 
move 'master' to 'C:\master_recovery.mdf',
move 'mastlog' to 'C:\master_recovery.ldf';

-- 5. last step
USE [master]
GO
EXEC master.dbo.sp_detach_db @dbname = N'master_recovery'
GO

-- 6. sql server configuration management -> properties -> StartUp Parameters
-- 7. copy those two files into the folder where original master file located
-- 8. change the name for the new files - master_recovery.mdf / master_recovery.ldf (just master / for the old one change to master_old)


-- After making Maintenance plan, and start one of them in Job Activity Monitor
-- Checking query
sp_who2 active
