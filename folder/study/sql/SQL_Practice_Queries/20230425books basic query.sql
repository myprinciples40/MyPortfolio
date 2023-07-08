select dob, lastName, phoneNo, interest
from personalInfo
where firstName = 'jin';

CREATE TABLE [OurFirstDatabase].[dbo].[Books] (
	[ID] [int] NOT NULL PRIMARY KEY,
	[bookName] [varchar](100) NOT NULL,
	[author] [varchar](50) NOT NULL,
	[field] [varchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[star] [decimal](2,1) NULL
)

SELECT *
from Books, personalInfo

--ex)
--Alter TABLE Books DROP COLUMN star

sp_rename '[Books].[ID]', 'bookID';
sp_rename '[personalInfo].[ID]', 'personID';

SELECT *
FROM INFORMATION_SCHEMA.TABLES;

EXEC SP_COLUMNS Books;