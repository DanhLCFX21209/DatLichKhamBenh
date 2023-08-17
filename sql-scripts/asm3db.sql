drop database asm3db;
create database asm3db;
use asm3db;

CREATE TABLE specializations (
  id INT(11) auto_increment PRIMARY KEY,
  name VARCHAR(255),
  view int,
  description TEXT,
  image VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME
);
CREATE TABLE roles (
  id INT(11) auto_increment PRIMARY KEY,
  name VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME
);
CREATE TABLE statuses (
  id INT(11) auto_increment PRIMARY KEY,
  name VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME
);
CREATE TABLE users (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  address VARCHAR(255),
  phone VARCHAR(256),
  avatar VARCHAR(255),
  gender VARCHAR(255),
  description TEXT,
  role_id INT(11),
  is_active TINYINT(1),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE clinics (
  id INT(11) auto_increment PRIMARY KEY,
  name VARCHAR(255),
  view int,
  address VARCHAR(255),
  phone VARCHAR(255),
  introductionHTML TEXT,
  introductionMarkdown TEXT,
  description TEXT,
  image VARCHAR(255),
  price VARCHAR(255),
  created_at DATETIME
);
CREATE TABLE places (
  id INT(11) auto_increment PRIMARY KEY,
  name VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME
);
CREATE TABLE doctor_users (
  id INT(11) auto_increment PRIMARY KEY,
  doctor_id INT(11),
  clinic_id INT(11),
  specialization_id INT(11),
  place_id int(11),
  introduction TEXT,
  training_process TEXT,
  achievements TEXT,
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (doctor_id) REFERENCES users(id),
  FOREIGN KEY (clinic_id) REFERENCES clinics(id),
  FOREIGN KEY (specialization_id) REFERENCES specializations(id),
  FOREIGN KEY (place_id) REFERENCES places(id)
);
CREATE TABLE schedules (
  id INT(11) auto_increment PRIMARY KEY,
  doctor_id INT(11),
  price VARCHAR(255),
  time VARCHAR(255),
  info text,
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (doctor_id) REFERENCES users(id)
);
CREATE TABLE patients (
  id INT(11) auto_increment PRIMARY KEY,
  doctor_id INT(11),
  description TEXT,
  status_id INT(11),
  schedule_id int(11),
  image VARCHAR(255),
  name VARCHAR(255),
  gender VARCHAR(255),
  address VARCHAR(255),
  FOREIGN KEY (doctor_id) REFERENCES users(id),
  FOREIGN KEY (status_id) REFERENCES statuses(id),
  FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);
CREATE TABLE extrainfos (
  id INT(11) auto_increment PRIMARY KEY,
  patientId INT(11),
  historyBreath TEXT,
  placeId INT(11),
  oldForms TEXT,
  sendForms TEXT,
  moreInfo TEXT,
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (patientId) REFERENCES patients(id),
  FOREIGN KEY (placeId) REFERENCES places(id)
);

CREATE TABLE posts (
  id INT(11) auto_increment PRIMARY KEY,
  title VARCHAR(255),
  contentMarkdown TEXT,
  forDoctorId INT(11),
  forSpecializationId INT(11),
  forClinicId INT(11),
  writerId INT(11),
  confimByDoctor TINYINT(1),
  image VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (forDoctorId) REFERENCES doctor_users(id),
  FOREIGN KEY (forSpecializationId) REFERENCES specializations(id),
  FOREIGN KEY (forClinicId) REFERENCES clinics(id),
  FOREIGN KEY (writerId) REFERENCES users(id)
);
CREATE TABLE supporterlogs (
  id INT(11) auto_increment PRIMARY KEY,
  patientId INT(11),
  supporterId INT(11),
  content VARCHAR(255),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (patientId) REFERENCES patients(id),
  FOREIGN KEY (supporterId) REFERENCES users(id)
);
CREATE TABLE comments (
  id INT(11) auto_increment PRIMARY KEY,
  doctorId INT(11),
  timeBooking VARCHAR(255),
  dateBooking VARCHAR(255),
  name VARCHAR(255),
  phone VARCHAR(255),
  content TEXT,
  status TINYINT(1),
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  FOREIGN KEY (doctorId) REFERENCES users(id)
);
CREATE TABLE session (
  sid VARCHAR(36) PRIMARY KEY,
  expires DATETIME,
  data TEXT,
  createdAt DATETIME,
  updatedAt DATETIME
);
CREATE TABLE sequelizemeta (
  name VARCHAR(255),
  PRIMARY KEY (name)
);

INSERT INTO specializations (id, name, description, view, image, createdAt, updatedAt, deletedAt)
VALUES (1, 'Specialization 1', 'Description 1', 100, 'image1.jpg', NOW(), NOW(), NULL),
       (2, 'Specialization 2', 'Description 2', 124, 'image2.jpg', NOW(), NOW(), NULL),
       (3, 'Specialization 3', 'Description 3', 86, 'image3.jpg', NOW(), NOW(), NULL);
INSERT INTO roles (id, name, createdAt, updatedAt, deletedAt)
VALUES (1, 'ROLE_EMPLOYEE', NOW(), NOW(), NULL),
	   (2, 'ROLE_MANAGER', NOW(), NOW(), NULL),
       (3, 'ROLE_ADMIN', NOW(), NOW(), NULL);
INSERT INTO statuses (id, name, createdAt, updatedAt, deletedAt)
VALUES (1, 'Active', NOW(), NOW(), NULL),
       (2, 'Inactive', NOW(), NOW(), NULL);

 /* password = password */
INSERT INTO users (id, name, email, password, address, phone, avatar, gender, description, role_id, is_active, createdAt, updatedAt, deletedAt)
VALUES (1, 'User 1', 'user1@example.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Address 1', '123456789', 'avatar1.jpg', 'Male', 'Description 1', 1, 1, NOW(), NOW(), NULL),
       (2, 'User 2', 'user2@example.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Address 2', '987654321', 'avatar2.jpg', 'Female', 'Description 2', 2, 1, NOW(), NOW(), NULL),
       (3, 'User 3', 'user3@example.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Address 3', '456789123', 'avatar3.jpg', 'Male', 'Description 3', 3, 0, NOW(), NOW(), NULL),
	   (4, 'User 4', 'user4@example.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Address 3', '456789123', 'avatar3.jpg', 'Male', 'Description 4', 2, 0, NOW(), NOW(), NULL);

INSERT INTO clinics (id, name, address, phone, price, view, introductionHTML, introductionMarkdown, description, image, created_at)
VALUES (1, 'Clinic 1', 'Address 1', '123456789', 2000000 , 78, 'Introduction HTML 1', 'Introduction Markdown 1', 'Description 1', 'image1.jpg', NOW()),
       (2, 'Clinic 2', 'Address 2', '987654321', 4000000 , 99, 'Introduction HTML 2', 'Introduction Markdown 2', 'Description 2', 'image2.jpg', NOW()),
       (3, 'Clinic 3', 'Address 3', '456789123', 6000000 , 56, 'Introduction HTML 3', 'Introduction Markdown 3', 'Description 3', 'image3.jpg', NOW());

INSERT INTO places (id, name, createdAt, updatedAt, deletedAt)
VALUES (1, 'Place 1', NOW(), NOW(), NULL),
       (2, 'Place 2', NOW(), NOW(), NULL),
       (3, 'Place 3', NOW(), NOW(), NULL);
INSERT INTO doctor_users (id, doctor_id, clinic_id, specialization_id, place_id, createdAt, updatedAt, deletedAt)
VALUES (1, 2, 1, 1, 1, NOW(), NOW(), NULL),
       (2, 2, 2, 2, 2, NOW(), NOW(), NULL),
       (3, 4, 3, 3, 3, NOW(), NOW(), NULL);
INSERT INTO schedules (id, doctor_id, price, time, info, createdAt, updatedAt, deletedAt)
VALUES
  (1, 4, '1000000', '9:00 AM - 11:00 AM', 'Tên: Nguyễn Văn A, Giới tính: Nam, Số điện thoại: 0901234567, Ngày tháng năm sinh: 1990-01-01, Địa chỉ: 123 Đường ABC, Lý do thăm khám: Đau đầu.', '2023-07-29 08:00:00', '2023-07-29 12:00:00', NULL),
  (2, 2, '4000000', '1:00 PM - 4:00 PM', 'Tên: Nguyễn Thị B, Giới tính: Nữ, Số điện thoại: 0987654321, Ngày tháng năm sinh: 1995-05-05, Địa chỉ: 456 Đường XYZ, Lý do thăm khám: Sốt cao.', '2023-07-29 10:00:00', '2023-07-29 15:00:00', NULL),
  (3, 4, '2000000', '3:00 PM - 5:00 PM', 'Tên: Lê Văn C, Giới tính: Nam, Số điện thoại: 0987654321, Ngày tháng năm sinh: 1995-05-05, Địa chỉ: 456 Đường XYZ, Lý do thăm khám: Đau bụng', '2023-07-29 14:00:00', '2023-07-29 16:30:00', NULL);
INSERT INTO patients (id, doctor_id, description, status_id, schedule_id, image, name, gender, address)
VALUES
  (1, 2, 'Patient with a cold', 1, 1, 'image1.jpg', 'John Smith', 'Male', '123 Main St'),
  (2, 4, 'Patient with a fever', 1, 2, 'image2.jpg', 'Jane Doe', 'Female', '456 Park Ave'),
  (3, 4, 'Patient with a broken arm', 2, 3, 'image3.jpg', 'Michael Johnson', 'Male', '789 Broadway');

INSERT INTO extrainfos (id, patientId, historyBreath, placeId, oldForms, sendForms, moreInfo, createdAt, updatedAt, deletedAt)
VALUES (1, 1, 'History 1', 1, 'Old Forms 1', 'Send Forms 1', 'More Info 1', NOW(), NOW(), NULL),
       (2, 2, 'History 2', 2, 'Old Forms 2', 'Send Forms 2', 'More Info 2', NOW(), NOW(), NULL),
       (3, 3, 'History 3', 3, 'Old Forms 3', 'Send Forms 3', 'More Info 3', NOW(), NOW(), NULL);
INSERT INTO posts (id, title, contentMarkdown, forDoctorId, forSpecializationId, forClinicId, writerId, confimByDoctor, image, createdAt, updatedAt, deletedAt)
VALUES (1, 'Post 1', 'Content 1', 1, 1, 1, 1, 1, 'image1.jpg', NOW(), NOW(), NULL),
       (2, 'Post 2', 'Content 2', 2, 2, 2, 2, 0, 'image2.jpg', NOW(), NOW(), NULL),
       (3, 'Post 3', 'Content 3', 3, 3, 3, 3, 1, 'image3.jpg', NOW(), NOW(), NULL);
INSERT INTO supporterlogs (id, patientId, supporterId, content, createdAt, updatedAt, deletedAt)
VALUES (1, 1, 1, 'Content 1', NOW(), NOW(), NULL),
       (2, 2, 2, 'Content 2', NOW(), NOW(), NULL),
       (3, 3, 3, 'Content 3', NOW(), NOW(), NULL);
INSERT INTO comments (id, doctorId, timeBooking, dateBooking, name, phone, content, status, createdAt, updatedAt, deletedAt)
VALUES (1, 4, 'Time 1', 'Date1', 'Name 1', 'Phone 1', 'Content 1', 1, NOW(), NOW(), NULL),
       (2, 4, 'Time 2', 'Date 2', 'Name 2', 'Phone 2', 'Content 2', 0, NOW(), NOW(), NULL),
       (3, 2, 'Time 3', 'Date 3', 'Name 3', 'Phone 3', 'Content 3', 1, NOW(), NOW(), NULL);
INSERT INTO session (sid, expires, data, createdAt, updatedAt)
VALUES ('sid1', NOW(), 'Data 1', NOW(), NOW()),
       ('sid2', NOW(), 'Data 2', NOW(), NOW()),
       ('sid3', NOW(), 'Data 3', NOW(), NOW());




