
--First way to create table for the file group
--Database Properties -> File Group -> Default check on the file group that you want to create


--Second way to create table for the file group
CREATE TABLE [dbo].[Orders_2020]
  ( [OrderId] int PRIMARY KEY,
    [ProductName] nchar(10) )
ON [FG_2020];

--Transaction Log Architecture
DBCC LOGINFO