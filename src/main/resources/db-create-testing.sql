
DROP DATABASE IF EXISTS testing;

CREATE DATABASE testing;

USE testing;

CREATE TABLE users (
  email 	VARCHAR(100) UNIQUE NOT NULL PRIMARY KEY,
  password 	VARCHAR(100) NOT NULL,
  user_type VARCHAR(100) DEFAULT 'STUDENT'
);

INSERT INTO users VALUE ('root', SHA1('root'), 'ADMIN');
INSERT INTO users VALUE ('rvanuekedu@gmail.com', SHA1('1Q!qwerty'), DEFAULT);


CREATE TABLE students (
  id			INT AUTO_INCREMENT PRIMARY KEY,
  first_name  	VARCHAR(100) NOT NULL,
  second_name 	VARCHAR(100) NOT NULL,
  user_email  	VARCHAR(100) NOT NULL,
  is_banned   	BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (user_email) REFERENCES users (email) 
	ON UPDATE CASCADE ON DELETE CASCADE
);
    
INSERT INTO students VALUE (1, 'Illya', 'Samoylenko', 'rvanuekedu@gmail.com', DEFAULT);
	
CREATE TABLE subjects (
	id INT PRIMARY KEY auto_increment, 
	subject_name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO subjects VALUES(default, 'English');
INSERT INTO subjects VALUES(default, 'Mathematics');
INSERT INTO subjects VALUES(default, 'History');

CREATE TABLE tests (
	id INT PRIMARY KEY auto_increment, 
    test_name VARCHAR(50) NOT NULL,
	subjects_id INT,
    complexity ENUM('easy', 'medium', 'hard'),
    `time` INT,
    FOREIGN KEY(subjects_id) REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

-- subject = Mathematics, complexity = easy, time = 10
INSERT INTO tests VALUES(default, 'Mathematics Test', 2, 'easy', 10);
INSERT INTO tests VALUES(default, 'Mathematics Test', 2, 'medium', 10);

CREATE TABLE questions (
	id INT PRIMARY KEY auto_increment, 
	qText VARCHAR(100) NOT NULL UNIQUE ,
    tests_id INT,
    FOREIGN KEY(tests_id) REFERENCES tests(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- text of question = Скільки сторін має трикутник?, test_id = 1;
INSERT INTO questions VALUES(default, 'Скільки сторін має трикутник?', 1);

CREATE TABLE answers (
	id INT PRIMARY KEY auto_increment, 
	aText VARCHAR(30) NOT NULL,
    isCorrect BOOL NOT NULL,
    questions_id INT,
    FOREIGN KEY(questions_id) REFERENCES questions(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- text of answer = 3, isCorrect = true, questions_id = 1
INSERT INTO answers VALUES(default, '3', true, 1);

CREATE TABLE results (
	id INT PRIMARY KEY auto_increment, 
	result TINYINT,
    `date` DATE,
    tests_id INT,
    students_id INT,
    FOREIGN KEY(tests_id) REFERENCES tests(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(students_id) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from questions;
INSERT INTO tests VALUES(default, 'History Test', 3, 'hard', 15);
INSERT INTO questions VALUES(default, '«Отцом истории» европейцы называют:', 3);
INSERT INTO answers VALUES(default, 'Нестора', false, 2);
INSERT INTO answers VALUES(default, 'Геродота', true, 2);
INSERT INTO answers VALUES(default, 'Грушевского', false, 2);
INSERT INTO questions VALUES(default, 'Скифскую пектораль нашел археолог:', 3);
INSERT INTO answers VALUES(default, 'Хвойка', false, 3);
INSERT INTO answers VALUES(default, 'Мозолевский', true, 3);
INSERT INTO answers VALUES(default, 'Телегин', false, 3);
INSERT INTO questions VALUES(default, 'Древнегреческая муза истории имеет имя:', 3);
INSERT INTO answers VALUES(default, 'Мнемозина', false, 4);
INSERT INTO answers VALUES(default, 'Клио', true, 4);
INSERT INTO answers VALUES(default, 'Каллиопа', false, 4);
INSERT INTO questions VALUES(default, 'Слово «суббота» имеет происхождение:', 3);
INSERT INTO answers VALUES(default, 'древнеримское', false, 5);
INSERT INTO answers VALUES(default, 'древнееврейское', true, 5);
INSERT INTO questions VALUES(default, 'Какая из дат не относится к XIX ст.:', 3);
INSERT INTO answers VALUES(default, '1825', false, 6);
INSERT INTO answers VALUES(default, '1800', true, 6);
INSERT INTO answers VALUES(default, '1861', false, 6);
INSERT INTO answers VALUES(default, '1862', false, 6);
INSERT INTO questions VALUES(default, 'Древнерусская мера «пядь» – это длина:', 3);
INSERT INTO answers VALUES(default, 'пальца', false, 7);
INSERT INTO answers VALUES(default, 'ладони', true, 7);
INSERT INTO answers VALUES(default, 'шага', false, 7);
INSERT INTO questions VALUES(default, 'Наука, изучающая монеты - это:', 3);
INSERT INTO answers VALUES(default, 'геральдика', false, 8);
INSERT INTO answers VALUES(default, 'нумизматика', true, 8);
INSERT INTO answers VALUES(default, 'сфрагистика', false, 8);
INSERT INTO questions VALUES(default, 'Письменным источником истории является:', 3);
INSERT INTO answers VALUES(default, 'орнамент', false, 9);
INSERT INTO answers VALUES(default, 'газета', true, 9);
INSERT INTO answers VALUES(default, 'картина', false, 9);
INSERT INTO questions VALUES(default, 'В Древнем Египте для письма использовали:', 3);
INSERT INTO answers VALUES(default, 'бересту', false, 10);
INSERT INTO answers VALUES(default, 'папирус', true, 10);
INSERT INTO answers VALUES(default, 'бумагу', false, 10);
INSERT INTO questions VALUES(default, 'Хранилищем документов разных времен есть:', 3);
INSERT INTO answers VALUES(default, 'библиотека', false, 11);
INSERT INTO answers VALUES(default, 'архив', true, 11);
INSERT INTO answers VALUES(default, 'музей', false, 11);
