create database dbset;

use dbset
alter database dbset set MIXED_PAGE_ALLOCATION on

CREATE TABLE heaptable1(c1 int, c2 varchar(8000))

insert heaptable1 values (1, REPLICATE('a',1000))

select * from heaptable1

drop table heaptable1

--execute all together at below 4 rows queries
select ht1.c1, ht1.c2, p.file_id, p.page_id, is_mixed_page_allocation

From heaptable1 as ht1 CROSS APPLY sys.fn_PhysLocCracker(%%physloc%%) as p inner join sys.dm_db_database_page_allocations(db_id(), object_id('dbo.heaptable1'),null,null,'detailed') as dddpa

on p.file_id=dddpa.allocated_page_file_id and p.page_id=dddpa.allocated_page_page_id

-- 1:329

--turn on the trace 
dbcc traceon(3604, -1)

-- dbcc page information - dbcc consistency check - This is PFS(Page Free Space) saves information about free space.
dbcc page(dbset, 1, 1, 3)

--Section 8 - 79.MSSQL Architecture (Very Important!!!!)