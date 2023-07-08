--Create Temporary Table

CREATE TABLE [AdventureWorks2016].[sales].[storenew] (	
	store_id INT PRIMARY KEY NOT NULL,
	sales INT
);

CREATE TABLE [AdventureWorks2016].[sales].[visits] (
	visit_id INT PRIMARY KEY IDENTITY (1,1),
	first_name varchar (50) NOT NULL,
	last_name varchar (50) NOT NULL,
	visited_at DATETIME,
	phone varchar (20),
	store_id INT NOT NULL,
	FOREIGN KEY (store_id) REFERENCES sales.storenew (store_id)
);

SELECT * 
from [AdventureWorks2016].[sales].[visits]

SELECT BusinessEntityID, firstname, lastname, title
from [person].[Person]
where title = 'mr.'	

--Option 1 for creating temp table
SELECT BusinessEntityID, firstname, lastname, title
into #TempPersonTable
from [person].[Person]
where title = 'mr.'
	
SELECT * FROM #TempPersonTable

DROP TABLE #TempPersonTable

--Option 2 for creating temp table
CREATE TABLE #TempPersonTable (
	BusinessEntityID INT, 
	FirstName nvarchar(50), 
	LastName nvarchar(50), 
	title nvarchar(20)
)
INSERT INTO #TempPersonTable
SELECT BusinessEntityID, firstname, lastname, title 
FROM [Person].[Person]
where title = 'mr.'

SELECT * FROM #TempPersonTable

DROP TABLE #TempPersonTable


--View
--CREATE VIEW Name AS
	--SELECT column1, column2...column N FROM tables
	--WHERE conditions;

