create database FPL_DaoTao

use FPL_DaoTao


create table Users (
	username nvarchar(50) not null,
	password nvarchar(50) null,
	role nvarchar(50) null,
	primary key(username)
)

create table Students (
	Masv nvarchar(50) not null primary key,
	Hoten nvarchar(50) null,
	Email nvarchar(50) null,
	SoDT nvarchar(15) null,
	GioiTinh bit null,
	DiaChi nvarchar(50) null,
	Hinh nvarchar(50) null
)

drop table Grade
create table Grade(
	ID int not null primary key identity,
	Masv nvarchar(50) null foreign key (Masv) references Students unique,
	TiengAnh int null,
	TinHoc int null,
	GDTC int null
)

SELECT * FROM Users
SELECT * FROM Students

INSERT INTO Users (username, password, role)
VALUES
	('user1', 'password1', 'role1'),
	('user2', 'password2', 'role2'),
	('user3', 'password3', 'role3'),
	('user4', 'password4', 'role4'),
	('user5', 'password5', 'role5'),
	('user6', 'password6', 'role6'),
	('user7', 'password7', 'role7'),
	('user8', 'password8', 'role8'),
	('user9', 'password9', 'role9'),
	('user10', 'password10', 'role10');


INSERT INTO Students (Masv, Hoten, Email, SoDT, GioiTinh, DiaChi, Hinh)
VALUES
	('sv1', 'Student 1', 'student1@example.com', '123456789', 1, 'Address 1', 'image1.jpg'),
	('sv2', 'Student 2', 'student2@example.com', '987654321', 0, 'Address 2', 'image2.jpg'),
	('sv3', 'Student 3', 'student3@example.com', '456123789', 1, 'Address 3', 'image3.jpg'),
	('sv4', 'Student 4', 'student4@example.com', '789456123', 0, 'Address 4', 'image4.jpg'),
	('sv5', 'Student 5', 'student5@example.com', '321654987', 1, 'Address 5', 'image5.jpg'),
	('sv6', 'Student 6', 'student6@example.com', '654987321', 0, 'Address 6', 'image6.jpg'),
	('sv7', 'Student 7', 'student7@example.com', '987321654', 1, 'Address 7', 'image7.jpg'),
	('sv8', 'Student 8', 'student8@example.com', '654123987', 0, 'Address 8', 'image8.jpg'),
	('sv9', 'Student 9', 'student9@example.com', '789321654', 1, 'Address 9', 'image9.jpg'),
	('sv10', 'Student 10', 'student10@example.com', '123987456', 0, 'Address 10', 'image10.jpg');



INSERT INTO Grade (Masv, TiengAnh, TinHoc, GDTC)
VALUES
	('1', 80, 90, 75)
	('sv1', 80, 90, 75),
	('sv2', 85, 92, 80),
	('sv3', 75, 88, 70),
	('sv4', 90, 82, 88),
	('sv5', 95, 96, 90),
	('sv6', 78, 85, 72),
	('sv7', 88, 91, 82),
	('sv8', 92, 87, 84),
	('sv9', 83, 79, 76),
	('sv10', 86, 94, 81);



delete from Grade



























if object_id('insert_user') is not null
	drop proc insert_user
go
create proc insert_user (
	@username nvarchar(50),
	@password nvarchar(50),
	@role nvarchar(50)
)
as
begin
	begin try
		insert into Users(username , password , role)
		values (@username , @password , @role)
	end try
	begin catch
		print 'loi ' + error_message()
	end catch
end

exec insert_user 'hao123' , '12345' , 'Admin'
exec insert_user 'hao1234' , '123456' , 'Admin'

go

if object_id('insert_student') is not null
	drop proc insert_student
go
create proc insert_student (
	@Masv nvarchar(50),
	@Hoten nvarchar(50),
	@Email nvarchar(50),
	@SoDT nvarchar(15),
	@GioiTinh bit,
	@DiaChi nvarchar(50),
	@Hinh nvarchar(50)
)
as
begin
	begin try
		insert into Students(Masv , Hoten , Email,
							SoDT , GioiTinh , DiaChi , Hinh)
		values (@Masv , @Hoten , @Email , @SoDT , @GioiTinh,
				@DiaChi , @Hinh)
	end try
	begin catch
		print 'loi ' + error_message()
	end catch
end



if object_id('insert_grade') is not null
	drop proc insert_grade
go
create proc insert_grade (
	@Masv nvarchar(50),
	@Hoten nvarchar(50),
	@Email nvarchar(50),
	@SoDT nvarchar(15),
	@GioiTinh bit,
	@DiaChi nvarchar(50),
	@Hinh nvarchar(50)
)
as
begin
	begin try
		insert into Students(Masv , Hoten , Email,
							SoDT , GioiTinh , DiaChi , Hinh)
		values (@Masv , @Hoten , @Email , @SoDT , @GioiTinh,
				@DiaChi , @Hinh)
	end try
	begin catch
		print 'loi ' + error_message()
	end catch
end

SELECT TOP 3 s.Masv, s.Hoten, s.Email, s.SoDT, s.GioiTinh, s.DiaChi, s.Hinh,
       g.TiengAnh, g.TinHoc, g.GDTC,
       (g.TiengAnh + g.TinHoc + g.GDTC) as TongDiem
FROM Students s
INNER JOIN Grade g ON s.Masv = g.Masv
ORDER BY (g.TiengAnh + g.TinHoc + g.GDTC) DESC;

select Students.Masv , Hoten , ID , TiengAnh , TinHoc , GDTC
from Grade join Students on Grade.Masv = Students.Masv
where Masv = ?