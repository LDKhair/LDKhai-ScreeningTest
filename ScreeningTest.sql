CREATE DATABASE SREENING_TEST;
USE SREENING_TEST;

CREATE TABLE EducationLevel (
    EducationLevelID INT AUTO_INCREMENT PRIMARY KEY,
    LevelName VARCHAR(50) NOT NULL
);

CREATE TABLE FacultyType (
    FacultyTypeID INT AUTO_INCREMENT PRIMARY KEY,
    TypeName VARCHAR(50) NOT NULL
);

CREATE TABLE SalaryLevel (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    FacultyTypeID INT,
    EducationLevelID INT,
    BaseSalary DOUBLE,
    FOREIGN KEY (FacultyTypeID) REFERENCES FacultyType (FacultyTypeID),
    FOREIGN KEY (EducationLevelID) REFERENCES EducationLevel (EducationLevelID)
);

CREATE TABLE Instructor (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    InstructorID VARCHAR(50) NOT NULL UNIQUE,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    FacultyTypeID INT,
    EducationLevelID INT,
    FacultyImage VARCHAR(255),
    Email VARCHAR(100),
    Address NVARCHAR(100),
    SalaryLevelID INT,
    StartDate DATE,
    Gender BIT DEFAULT 0,
    FOREIGN KEY (FacultyTypeID) REFERENCES FacultyType(FacultyTypeID),
    FOREIGN KEY (EducationLevelID) REFERENCES EducationLevel(EducationLevelID)
);

CREATE TABLE Account (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role BIT DEFAULT 0,
    IsFirstLogin BIT DEFAULT 1,
    FOREIGN KEY (Username) REFERENCES Instructor(InstructorID)
);

CREATE TABLE Banned (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    InstructorID VARCHAR(50) NOT NULL,
    BannedDate DATE NOT NULL,
    ReasonBan TEXT,
    FOREIGN KEY (InstructorID) REFERENCES Instructor(InstructorID)
);

INSERT INTO EducationLevel (LevelName) VALUES 
('Cử nhân cao đẳng'), 
('Cử nhân đại học'), 
('Thạc sỹ'), 
('Tiến sỹ');

INSERT INTO FacultyType (TypeName) VALUES 
('Full-time'), 
('Part-time');

INSERT INTO SalaryLevel (FacultyTypeID, EducationLevelID, BaseSalary) VALUES
(1, 1, 10000000), -- Full-time, Cử nhân cao đẳng: 10 triệu VNĐ
(1, 2, 15000000), -- Full-time, Cử nhân đại học: 15 triệu VNĐ
(1, 3, 20000000), -- Full-time, Thạc sỹ: 20 triệu VNĐ
(1, 4, 25000000), -- Full-time, Tiến sỹ: 25 triệu VNĐ
(2, 1, 5000000),  -- Part-time, Cử nhân cao đẳng: 5 triệu VNĐ
(2, 2, 8000000),  -- Part-time, Cử nhân đại học: 8 triệu VNĐ
(2, 3, 10000000), -- Part-time, Thạc sỹ: 10 triệu VNĐ
(2, 4, 15000000); -- Part-time, Tiến sỹ: 15 triệu VNĐ

INSERT INTO Instructor (InstructorID, FirstName, LastName, FacultyTypeID, EducationLevelID, FacultyImage, SalaryLevelID, StartDate) VALUES
('giangtn1', 'Trần', 'Ngọc Giang', 1, 4, 'giang_image.jpg', 4, '2020-01-15'),
('quyendn2', 'Đỗ', 'Ngọc Quyên', 1, 3, 'quyen_image.jpg', 3, '2018-05-10'),
('linhpt3', 'Phạm', 'Thùy Linh', 2, 2, 'linh_image.jpg', 6, '2019-09-01'),
('minhbt4', 'Bùi', 'Thị Minh', 2, 1, 'minh_image.jpg', 5, '2021-03-20'),
('thaoht5', 'Hoàng', 'Thị Thảo', 1, 2, 'thao_image.jpg', 2, '2017-11-23'),
('vinhnt6', 'Nguyễn', 'Trọng Vinh', 1, 4, 'vinh_image.jpg', 4, '2019-07-16'),
('hungdn7', 'Đặng', 'Ngọc Hùng', 2, 3, 'hung_image.jpg', 7, '2022-02-08'),
('quyenbt8', 'Bùi', 'Thị Quyên', 2, 1, 'quyen_image.jpg', 5, '2021-12-01'),
('tuanpq9', 'Phạm', 'Quốc Tuấn', 1, 1, 'tuan_image.jpg', 1, '2016-04-21'),
('lannt10', 'Nguyễn', 'Thị Lan', 1, 3, 'lan_image.jpg', 3, '2020-08-12'),
('dungnv11', 'Nguyễn', 'Văn Dũng', 2, 2, 'dung_image.jpg', 6, '2023-01-30'),
('haopt12', 'Phạm', 'Thị Hảo', 2, 4, 'hao_image.jpg', 8, '2020-10-05'),
('thuynm13', 'Nguyễn', 'Minh Thùy', 1, 4, 'thuy_image.jpg', 4, '2015-09-14'),
('linhbt14', 'Bùi', 'Thị Linh', 1, 3, 'linh2_image.jpg', 3, '2018-06-25');

INSERT INTO Instructor (InstructorID, FirstName, LastName, FacultyTypeID, EducationLevelID, FacultyImage, SalaryLevelID, StartDate) VALUES
('giangvien01', 'Nguyễn', 'Văn A', 1, 4, 'nguyenvana.jpg', 4, '2020-01-10'),
('giangvien02', 'Trần', 'Thị B', 1, 3, 'tranthib.jpg', 3, '2019-02-15'),
('giangvien03', 'Lê', 'Văn C', 1, 2, 'levanc.jpg', 2, '2018-03-20'),
('giangvien04', 'Phạm', 'Thị D', 2, 1, 'phamthid.jpg', 5, '2021-04-25'),
('giangvien05', 'Hoàng', 'Văn E', 2, 4, 'hoangvane.jpg', 8, '2022-05-30'),
('giangvien06', 'Vũ', 'Thị F', 1, 2, 'vuthif.jpg', 2, '2017-06-05'),
('giangvien07', 'Đinh', 'Văn G', 2, 3, 'dinhvang.jpg', 7, '2020-07-10'),
('giangvien08', 'Bùi', 'Thị H', 1, 1, 'buithih.jpg', 1, '2016-08-15'),
('giangvien09', 'Đỗ', 'Văn I', 1, 4, 'dovani.jpg', 4, '2019-09-20'),
('giangvien10', 'Ngô', 'Thị J', 2, 2, 'ngothij.jpg', 6, '2021-10-25'),
('giangvien11', 'Trịnh', 'Văn K', 1, 3, 'trinhvank.jpg', 3, '2018-11-30'),
('giangvien12', 'Lâm', 'Thị L', 2, 1, 'lamthil.jpg', 5, '2022-12-05'),
('giangvien13', 'Nguyễn', 'Văn M', 1, 2, 'nguyenvanm.jpg', 2, '2020-01-10'),
('giangvien14', 'Trần', 'Thị N', 2, 4, 'tranthin.jpg', 8, '2017-02-15'),
('giangvien15', 'Lê', 'Văn O', 1, 3, 'levano.jpg', 3, '2019-03-20'),
('giangvien16', 'Phạm', 'Thị P', 2, 2, 'phamthip.jpg', 6, '2021-04-25'),
('giangvien17', 'Hoàng', 'Văn Q', 1, 1, 'hoangvanq.jpg', 1, '2018-05-30'),
('giangvien18', 'Vũ', 'Thị R', 2, 4, 'vuthir.jpg', 8, '2022-06-05'),
('giangvien19', 'Đinh', 'Văn S', 1, 3, 'dinhvans.jpg', 3, '2020-07-10'),
('giangvien20', 'Bùi', 'Thị T', 2, 1, 'buithit.jpg', 5, '2019-08-15');

