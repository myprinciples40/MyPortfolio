--INDEX (Why we need index and what is Table Scan)
--Consider the example of a book
--Imagine it has 10 chapters about human anatomy, but with NO INDEX at the end
--To find about HEART and its functions you have to read the whole book to find which chapter have information.
--Similarly if we have a table with no INDEX, Its called a HEAP.
--A heap is a table that is stored without any underlying order like a lundary.
--When rows are inserted into a heap, there is no way to ensure where the pages will be written nor are thosse pages guaranteed to remain in the same order
--as the talbe is written maintenance is performed against it.

--To find a row the SQL Server engine has to do TABLE SCAN
--A TABLE SCAN is a pretty staraitforward process. When your query engine performs a table scan it starts from the physical beginning of the table and goes through every row in the table.
--If a row matches the criterion then it includes that into the result set.
--If you have a small data set, then the table scan is the fastest way to do to find a table.

--What is an INDEX
--The primary reason indexes are built is to provide faster data access to the specific data your query is trying to retrieve.
--This could be either a clustered or non-clustered index.
--Without having an index SQL Server would need to read through all of the data in order to find the rows that satisfy the query.
--Indexes are created on columns in tables or views.
--The index provides a fast way to look up data based on the values within those columns.
--For example, if you create an index on the primary key and then search for a row data based on one of the primary key values, SQL Server first finds that value in the index, 
--and then uses the index to quickly locate the entire row of data.
--You can create indexes on most columns in a table or a view.
--The exceptions are primarily those columns configured with large object(LOB) data types, such as image, text, and varchar(max).

--Structure of an INDEX
--An index is made up of a set of pages(index nodes) that are organized in a B-tree structure.
--This structure is hierachical in nature.
--Root node at the top of the hierachy and the leaf nodes at the bottom.
--When a query is issued against an indexed column, the query engine starts at the root node and navigates down through the intermediate nodes, 
--with each layer of the intermediate level more granular than the one above.
--The query engine continues down through the index nodes until it reaches the leaf node.

--Clustered INDEX
--A clustered index stores the actual data rows at the leaf level of the index.
--That would mean that the entire row of data associated with the primary key value of 123 would be stored in that leaf node.
--An important characteristic of the clustered index is that the indexed values are sorted in either ascending or descending order.
--As a result, there can be only one clustered index on a table or view. (Very Important!!!)
--In addition, data in a table is sorted only if a clustered index has been defined on a table.

--NonClustered Index
--1.Unlike a clustered indexed, the leaf nodes of a nonclustered index contain only the values from the indexed columns and row locators that point to the actual data rows, 
--rather than contain the data rows themselves.
--2.This means that the query engine must take an additional step in order to locate the actual data. (Main difference with Clustered INDEX!!!)
--3.A row locator¡¯s structure depends on whether it points to a clustered table or to a heap. If referencing a clustered table, 
--the row locator points to the clustered index, using the value from the clustered index to navigate to the correct data row.
--4.If referencing a heap, the row locator points to the actual data row.
--5.Nonclustered indexes cannot be sorted like clustered indexes.
--6.SQL Server support up to 999 nonclustered indexes.
--7.This certainly doesn¡¯t mean you should create that many indexes. Indexes can both help and hinder performance.
--8.In addition to being able to create multiple nonclustered indexes on a table or view, you can also add included columns to your index


--Index types based on configuration
--1.Composite index: An index that contains more than one column. In both SQL Server you can include up to 16 columns in an index, 
--as long as the index doesn¡¯t exceed the 900-byte limit. Both clustered and nonclustered indexes can be composite indexes.

--2.Unique Index: An index that ensures the uniqueness of each value in the indexed column. If the index is a composite, 
--the uniqueness is enforced across the columns as a whole, not on the individual columns. For example, 
--if you were to create an index on the FirstName and LastName columns in a table, the names together must be unique, but the individual names can be duplicated.

--3.A unique index is automatically created when you define a primary key or unique constraint 

--4.Primary key: When you define a primary key constraint on one or more columns, SQL Server automatically creates a unique, 
--clustered index if a clustered index does not already exist on the table or view.

--5.Unique: When you define a unique constraint, SQL Server automatically creates a unique, nonclustered index. (It is not a primary key.)

--6.Covering index: A type of index that includes all the columns that are needed to process a particular query. 
--For example, your query might retrieve the FirstName and LastName columns from a table, based on a value in the ContactID column. 
--You can create a covering index that includes all three columns.


--Index design consideration
--As beneficial as indexes can be, they must be designed carefully.
--They can take up significant disk space, you don¡¯t want to implement more indexes than necessary.
--Indexes are automatically updated when the data rows themselves are updated, which can lead to additional overhead and can affect performance.
--For tables that are heavily updated, use as few columns as possible in the index, and don¡¯t over-index the tables.
--on small tables because the query engine might take longer to navigate the index than to perform a table scan.
--For clustered indexes, try to keep the length of the indexed columns as short as possible.
--Try to implement your clustered indexes on unique columns that do not permit null values.
--When possible, implement unique indexes.
--For composite indexes, take into consideration the order of the columns in the index definition.
--Try to insert or modify as many rows as possible in a single statement, rather than using multiple queries.
--Create nonclustered indexes on columns used frequently in your statement's predicates and join conditions.


--Create Index Syntax
--Create a nonclustered index on a table or view.
--CREATE INDEX index1 ON schema1.table1 (column1);

--Create a clustered index on a table and use a 3-part name for the table.
--CREATE CLUSTERED INDEX index1 ON database1.schema1.table1 (column1);

--Create a nonclustered index with a unique constraint
--on 3 columns and specify the sort order for each column.
--CREATE UNIQUE INDEX index1 ON schema1.table1 (column1 DESC, column2 ASC, column3 DESC);


--Fix SQL Server Index fragmentation
--1.Rebuild: Rebuild indexes when fragmentation reaches greater than 30 percent.
--2.Reorganize: Reorganize indexes with between 11-30 percent fragmentation.
--3.Ignore: Fragmentation levels of 10 percent or less should not pose a performance problem, so you don¡¯t need to do anything.


SELECT count(1) FROM [Person].[Person]


/****** Object:  Index [AK_Person_rowguid]    Script Date: 2023-06-07 ¿ÀÀü 11:45:12 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Person_rowguid] ON [Person].[Person]
(
	[rowguid] ASC
)
GO


-- DMS for checking index fragmentation for whole

SELECT S.name as 'Schema',
T.name as 'Table',
I.name as 'Index',
DDIPS.avg_fragmentation_in_percent,
DDIPS.page_count
FROM sys.dm_db_index_physical_stats (DB_ID(), NULL, NULL, NULL, NULL) AS DDIPS
INNER JOIN sys.tables T on T.object_id = DDIPS.object_id
INNER JOIN sys.schemas S on T.schema_id = S.schema_id
INNER JOIN sys.indexes I ON I.object_id = DDIPS.object_id
AND DDIPS.index_id = I.index_id
WHERE DDIPS.database_id = DB_ID()
and I.name is not null
AND DDIPS.avg_fragmentation_in_percent > 0
ORDER BY DDIPS.avg_fragmentation_in_percent desc

--Rebuild index fragmentation when it is more than 30% but, we need to check the page as well.
USE [AdventureWorks2019]
GO
ALTER INDEX [IX_WorkOrder_ProductID] ON [Production].[WorkOrder] REBUILD PARTITION = ALL WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO

--Reorganize index fragmentation when it is between 11 ~ 30% but, we need to check the page as well.
USE [AdventureWorks2019]
GO
ALTER INDEX [IX_WorkOrder_ProductID] ON [Production].[WorkOrder] REORGANIZE  WITH ( LOB_COMPACTION = ON )
GO
