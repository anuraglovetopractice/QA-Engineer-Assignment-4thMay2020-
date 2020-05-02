Creating Employee table
CREATE TABLE Employee (EmpID int, EmpName varchar(255), Date_of_birth Date, PRIMARY KEY (EmpID));

Inserting into Employee table
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES (1, 'Rohit', '1990-12-28');
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES (2, 'Rahul', '1989-12-28');
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES (3, 'Rajat', '1985-10-23');
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES (4, 'Raman', '1997-01-23');
INSERT INTO Employee (EmpID, EmpName, Date_of_birth) VALUES (5, 'Raman', '1967-01-23');

Creating Salary table
CREATE TABLE Salary (EmpID int, Salary int, PRIMARY KEY (EmpID), FOREIGN KEY (EmpID) REFERENCES Employee(EmpID));

Inserting into Salary table
INSERT INTO Salary (EmpID, Salary) VALUES (1, '12000');
INSERT INTO Salary (EmpID, Salary) VALUES (2, '17000');
INSERT INTO Salary (EmpID, Salary) VALUES (3, '10000');
INSERT INTO Salary (EmpID, Salary) VALUES (4, '7000');
INSERT INTO Salary (EmpID, Salary) VALUES (5, '7000');

SQL query to find nth largest salary along with employee name.
select TOP 1 a.EmpName, b.Salary from Employee a join Salary b on a.EmpID = b.EmpID order by Salary desc;

SQL query to update salary of employees to 5000 whose age is 30+
UPDATE Salary SET Salary=5000 WHERE EmpID in (Select EmpID from Employee where DATEDIFF(day, Date_of_birth, GETDATE()) > 10957.5);
