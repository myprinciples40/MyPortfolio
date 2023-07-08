--20230502 JOINS

--JOIN SYNTAX
--SELECT column-names
--FROM table-name1 JOIN table-name2
--ON column-names1 = column-names2
--WHERE condition

SELECT *
FROM Employee

SELECT *
FROM [dbo].[Sales]

INSERT INTO Employee (EmpID, EmpName, EmpTitle) VALUES (3, 'Dino', 'Sales Associate');
INSERT INTO Employee (EmpID, EmpName, EmpTitle) VALUES (11, 'Dino', 'Sr Sales Associate');
INSERT INTO Employee (EmpID, EmpName, EmpTitle) VALUES (79, 'James', 'Sales Manager');

EXEC SP_rename 'Sales.emName', 'EmpName', 'COLUMN'
--ALTER TABLE Sales CHANGE COLUMN emName to EmpName;  --Not working for both (RENAME instead of CHANGE)

--This queries make wrong information 
SELECT *
FROM Employee e
JOIN Sales s
ON e.EmpName = s.EmpName

--RIght queries for the JOIN case
SELECT e.EmpID, e.EmpName, s.SalesNumber, s.ItemSold  --alias need infront of the column name
FROM Employee e
JOIN Sales s
ON e.EmpID = s.EmpID
ORDER BY e.empID 

SELECT count(SalesNumber) as [Sales Number], e.empID, e.empName from Employee e
Join sales s
ON e.empID = s.empID
group by e.empID, e.empName


--Different Types of JOINS
--1.INNER join: Default join when we don't specify any thing with JOIN key word.

Select * from dbo.Student
Go

Select * from dbo.course

select * from student s
inner join course c
on s.rollno = c.rollno

select s.rollNo, s.studentName,c.courseid from student s
inner join course c  --without inner will be the same result.
on s.rollno = c.rollno

--2. Left join: is also known as LEFT OUTER JOIN.

select s.rollNo, s.studentName,c.courseid from student s
left join course c
on s.rollno = c.rollno

--3. Right join: is also known as RIGHT OUTER JOIN.

select s.rollNo, s.studentName,c.courseid from student s
right join course c 
on s.rollno = c.rollno

--4. Full join

select s.rollNo, s.studentName,c.courseid from student s
full join course c 
on s.rollno = c.rollno

Select * from dbo.Student
Go

Select * from dbo.course