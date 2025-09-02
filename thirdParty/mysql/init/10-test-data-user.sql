SET NAMES 'utf8mb4';
USE test;
-- 密码均为testPassword
INSERT INTO user (uid, email, username, salt_password)
VALUES (1000000000000000001, 'test01@hirrao.com', 'testUser_1',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000002, 'test02@hirrao.com', 'testUser_2',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000003, 'test03@hirrao.com', 'testUser_3',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000004, 'test04@hirrao.com', 'testUser_4',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000005, 'test05@hirrao.com', 'testUser_5',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000006, 'test06@hirrao.com', 'testUser_6',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000007, 'test07@hirrao.com', 'testUser_7',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000008, 'test08@hirrao.com', 'testUser_8',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000009, 'test09@hirrao.com', 'testUser_9',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm'),
       (1000000000000000010, 'test10@hirrao.com', 'testUser_10',
        '$2a$10$.NjaANnLt7dP51snruB80.Wx5OLqqBgFnQQexu8bIRli2kt6BRmLm');

INSERT INTO user (uid, email, username, salt_password, role)
VALUES (1000000000000000000, 'testadmin@hirrao.com', 'hirrao',
        '$2a$10$cSicYBembOOUvxJc4WEZxuyODJSWgqm0OzBmRXW0LAoAPu4IdN6Me', 1000);