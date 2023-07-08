--Create a new user - public
USE [master]
GO
CREATE LOGIN [WIN-IU9N20268UB\winuser02] FROM WINDOWS WITH DEFAULT_DATABASE=[master]
GO

--Check what kind of the permission it has
SELECT pr.principal_id, pr.name, pe.state_desc, pe.permission_name
FROM sys.server_principals pr INNER JOIN sys.server_permissions pe
  ON pr.principal_id = pe.grantee_principal_id
WHERE pr.principal_id = SUSER_ID('WIN-IU9N20268UB\winuser02')
  OR pr.principal_id = SUSER_ID('WIN-IU9N20268UB\winuser01');
  
--Switch the login
GRANT IMPERSONATE ANY LOGIN TO [WIN-IU9N20268UB\winuser02], [WIN-IU9N20268UB\winuser01];
GO

--Grant alter any database roll to devops
CREATE SERVER ROLE devops11;
GRANT ALTER ANY DATABASE TO devops11;
ALTER SERVER ROLE devops11 ADD MEMBER [WIN-IU9N20268UB\winuser02];
GO

--Checking
SELECT pr.principal_id, pr.name, pe.state_desc, pe.permission_name
FROM sys.server_principals pr INNER JOIN sys.server_permissions pe
  ON pr.principal_id = pe.grantee_principal_id
WHERE pr.principal_id = SUSER_ID('devops11');

--Check the member's of this group
SELECT rm.member_principal_id, pr.name
FROM sys.server_role_members rm INNER JOIN sys.server_principals pr
  ON rm.member_principal_id = pr.principal_id
WHERE rm.role_principal_id = SUSER_ID('devops11');

--Add server role
ALTER SERVER ROLE [devops11] ADD MEMBER [WIN-IU9N20268UB\winuser01]
GO

--After that re-check member

--Database Roles
