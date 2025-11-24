-- db/init.sql
USE project_db;

CREATE TABLE IF NOT EXISTS task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    main_text VARCHAR(255) NOT NULL,
    date DATE,
    creation_date DATETIME NOT NULL,
    priority VARCHAR(50),
    completed BOOLEAN NOT NULL DEFAULT 0
);