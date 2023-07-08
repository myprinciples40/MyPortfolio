CREATE TABLE SQLBackupRestoreTest (
	ID INT NOT NULL PRIMARY KEY,
	loginname VARCHAR(100) NOT NULL,
	logindate DATETIME NOT NULL DEFAULT getdate()
)
GO

Select * from SQLBackupRestoreTest 

insert into SQLBackupRestoreTest (ID, loginname) values (1, 'test1')
insert into SQLBackupRestoreTest (ID, loginname) values (2, 'test2')
insert into SQLBackupRestoreTest (ID, loginname) values (3, 'test3')
insert into SQLBackupRestoreTest (ID, loginname) values (4, 'test4')
insert into SQLBackupRestoreTest (ID, loginname) values (5, 'test5')


--Full Back up 5 rows
BACKUP DATABASE [AdventureWorks2016] TO  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_full.bak' 
WITH NOFORMAT, NOINIT,  NAME = N'AdventureWorks2016-전체 데이터베이스 백업', SKIP, NOREWIND, NOUNLOAD,  STATS = 10
GO

--Differential Back up 10 rows
insert into SQLBackupRestoreTest (ID, loginname) values (6, 'test6')
insert into SQLBackupRestoreTest (ID, loginname) values (7, 'test7')
insert into SQLBackupRestoreTest (ID, loginname) values (8, 'test8')
insert into SQLBackupRestoreTest (ID, loginname) values (9, 'test9')
insert into SQLBackupRestoreTest (ID, loginname) values (10, 'test10')


--Differential Back up 10 rows
BACKUP DATABASE [AdventureWorks2016] TO  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_diff_1.diff' WITH  DIFFERENTIAL , 
NOFORMAT, NOINIT,  NAME = N'AdventureWorks2016-전체 데이터베이스 백업', SKIP, NOREWIND, NOUNLOAD,  STATS = 10
GO

--Tran log Back up - 1 up 13 rows
insert into SQLBackupRestoreTest (ID, loginname) values (11, 'test11')
insert into SQLBackupRestoreTest (ID, loginname) values (12, 'test12')
insert into SQLBackupRestoreTest (ID, loginname) values (13, 'test13')

-- Tran log back up - 2 up 17 rows
insert into SQLBackupRestoreTest (ID, loginname) values (14, 'test14')
insert into SQLBackupRestoreTest (ID, loginname) values (15, 'test15')
insert into SQLBackupRestoreTest (ID, loginname) values (16, 'test16')
insert into SQLBackupRestoreTest (ID, loginname) values (17, 'test17')

-- Tran log back up - 3 up 17 rows
--Jun 27 2023  9:59AM
insert into SQLBackupRestoreTest (ID, loginname) values (114, 'test114')
insert into SQLBackupRestoreTest (ID, loginname) values (115, 'test115')
insert into SQLBackupRestoreTest (ID, loginname) values (116, 'test116')
insert into SQLBackupRestoreTest (ID, loginname) values (117, 'test117')
insert into SQLBackupRestoreTest (ID, loginname) values (118, 'test118')
insert into SQLBackupRestoreTest (ID, loginname) values (120, 'test120')

--Jun 27 2023 10:58AM
insert into SQLBackupRestoreTest (ID, loginname) values (150, 'test150')

print getdate()
--Jun 27 2023  9:59AM

--Total 24 rows has

Delete from SQLBackupRestoreTest 

--Delete AdventureWorks2016 and restore following the sequence: Full back up -> Differential -> Transaction back up 


--NORECOVERY - It will become online but it is not available
-- Restore 5 rows with Full restore
USE [master]
RESTORE DATABASE [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_full.bak' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 5

GO

-- Restore 5 more rows with differential restore (Now, it is 10 rows in total)
USE [master]
RESTORE DATABASE [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_diff_1.diff' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 5

GO

--make the database online (but this way I will lose the rest of the data from 11 rows)
RESTORE DATABASE AdventureWorks2016 WITH RECOVERY
GO

-- Restore transaction log1 - NORECOVERY  (Now, it is 13 rows in total)
RESTORE LOG [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_tran_1.trn' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 10
GO

-- Restore transaction log2 - NORECOVERY  (Now, it is 17 rows in total)
RESTORE LOG [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_tran_2.trn' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 10
GO

-- Restore transaction log3 - NORECOVERY  (Now, it is 23 rows in total)
RESTORE LOG [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_tran_3.trn' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 10
GO

-- Restore transaction log final - NORECOVERY  (Now, it is 24 rows in total)
RESTORE LOG [AdventureWorks2016] FROM  DISK = N'C:\SQL_BACKUPS\AdventureWorks2016_tran_final.trn' WITH  FILE = 1,  NORECOVERY,  NOUNLOAD,  STATS = 10,
STOPAT = N'2023-06-27T10:58:22'
GO

--make the database online (but this way I will lose the rest of the data from 11 rows)
RESTORE DATABASE AdventureWorks2016 WITH RECOVERY
GO

--Final check
Select * from SQLBackupRestoreTest 