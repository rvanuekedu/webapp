
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
INSERT INTO users VALUE ('email@email.net', SHA1('1Q!qwerty'), DEFAULT);



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
INSERT INTO students VALUE (2, 'Вася', 'Семечкин', 'email@email.net', TRUE);
	
CREATE TABLE subjects (
	id INT PRIMARY KEY auto_increment, 
	subject_name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO subjects VALUES(default, 'Английский');
INSERT INTO subjects VALUES(default, 'Математика');
INSERT INTO subjects VALUES(default, 'История');

CREATE TABLE tests (
	id INT PRIMARY KEY auto_increment, 
    test_name VARCHAR(50) NOT NULL,
	subjects_id INT,
    complexity ENUM('easy', 'medium', 'hard'),
    `time` INT,
    FOREIGN KEY(subjects_id) REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE TABLE questions (
	id INT PRIMARY KEY auto_increment, 
	qText VARCHAR(200) NOT NULL UNIQUE ,
    tests_id INT,
    FOREIGN KEY(tests_id) REFERENCES tests(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE answers (
	id INT PRIMARY KEY auto_increment, 
	aText VARCHAR(30) NOT NULL,
    isCorrect BOOL NOT NULL,
    questions_id INT,
    FOREIGN KEY(questions_id) REFERENCES questions(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE results (
	id INT PRIMARY KEY auto_increment, 
	result TINYINT,
    `date` DATE,
    tests_id INT,
    students_id INT,
    FOREIGN KEY(tests_id) REFERENCES tests(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(students_id) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Тест по английскому
INSERT INTO tests VALUES(1, 'Тест по английскому', 1, 'easy', 4);
INSERT INTO questions VALUES(1, 'When the parents came home, the apples (to eat)', 1);
INSERT INTO answers VALUES(default, 'were eaten', false, 1);
INSERT INTO answers VALUES(default, 'had already been eaten', true, 1);
INSERT INTO answers VALUES(default, 'ate', false, 1);
INSERT INTO questions VALUES(2, 'After I did my homework I (to have) dinner.', 1);
INSERT INTO answers VALUES(default, 'had had', true, 2);
INSERT INTO answers VALUES(default, 'had', false, 2);
INSERT INTO answers VALUES(default, 'did have', false, 2);
INSERT INTO questions VALUES(3, 'Before we had supper we (to play) football.', 1);
INSERT INTO answers VALUES(default, 'played', false, 3);
INSERT INTO answers VALUES(default, 'did play', false, 3);
INSERT INTO answers VALUES(default, 'had played', true, 3);
INSERT INTO answers VALUES(default, 'play', false, 3);
INSERT INTO questions VALUES(4, 'I had done my homework before I (to go) for a walk.', 1);
INSERT INTO answers VALUES(default, 'had gone', false, 4);
INSERT INTO answers VALUES(default, 'have gone', false, 4);
INSERT INTO answers VALUES(default, 'went', true, 4);
INSERT INTO questions VALUES(5, 'When I (to be) sixteen, I had already got my own car.', 1);
INSERT INTO answers VALUES(default, 'am', false, 5);
INSERT INTO answers VALUES(default, 'was', true, 5);
INSERT INTO answers VALUES(default, 'were', false, 5);
INSERT INTO questions VALUES(6, 'When I called her, she already (to leave) her house.', 1);
INSERT INTO answers VALUES(default, 'left', false, 6);
INSERT INTO answers VALUES(default, 'were left', false, 6);
INSERT INTO answers VALUES(default, 'had left', true, 6);
INSERT INTO questions VALUES(7, 'I (to go) to a shopping mall when I saw her.', 1);
INSERT INTO answers VALUES(default, 'was going', true, 7);
INSERT INTO answers VALUES(default, 'had been going', false, 7);
INSERT INTO answers VALUES(default, 'were going', false, 7);
INSERT INTO questions VALUES(8, 'You (to brush) your teeth before you went to bed?', 1);
INSERT INTO answers VALUES(default, 'have brushed', false, 8);
INSERT INTO answers VALUES(default, 'had brushed', true, 8);
INSERT INTO answers VALUES(default, 'brushed', false, 8);
INSERT INTO questions VALUES(9, 'I (to wash) all the dishes before my mum came home.', 1);
INSERT INTO answers VALUES(default, 'had washed', true, 9);
INSERT INTO answers VALUES(default, 'washed', false, 9);
INSERT INTO answers VALUES(default, 'have washed', false, 9);
INSERT INTO questions VALUES(10, 'He (to return) the book to the library before he went on vacation.', 1);
INSERT INTO answers VALUES(default, 'had returned', true, 10);
INSERT INTO answers VALUES(default, 'were returned', false, 10);
INSERT INTO answers VALUES(default, 'was returned', false, 10);
INSERT INTO questions VALUES(11, 'He never (to see) anything like that before.', 1);
INSERT INTO answers VALUES(default, 'sees', false, 11);
INSERT INTO answers VALUES(default, 'would see', false, 11);
INSERT INTO answers VALUES(default, 'had seen', true, 11);
INSERT INTO questions VALUES(12, 'I (to do) my homework when she called me.', 1);
INSERT INTO answers VALUES(default, 'were doing', false, 12);
INSERT INTO answers VALUES(default, 'was doing', true, 12);
INSERT INTO answers VALUES(default, 'have done', false, 12);
INSERT INTO questions VALUES(13, 'After the Sun (to set), we saw thousands of fireflies.', 1);
INSERT INTO answers VALUES(default, 'has set', false, 13);
INSERT INTO answers VALUES(default, 'have set', false, 13);
INSERT INTO answers VALUES(default, 'had set', true, 13);
INSERT INTO questions VALUES(14, 'He looked very happy when I (to see) him', 1);
INSERT INTO answers VALUES(default, 'was seeing', false, 14);
INSERT INTO answers VALUES(default, 'saw', true, 14);
INSERT INTO answers VALUES(default, 'have seen', false, 14);
INSERT INTO questions VALUES(15, 'Lucy (to not drink) a cup of water before she ate a sandwich.', 1);
INSERT INTO answers VALUES(default, 'hadn`t drunk', false, 15);
INSERT INTO answers VALUES(default, 'didn`t drink', true, 15);
INSERT INTO answers VALUES(default, 'drinks', false, 15);

-- Легкий тест по математике
INSERT INTO tests VALUES(2, 'Легкий тест по математике', 2, 'easy', 4);
INSERT INTO questions VALUES(16, 'Найдите площадь квадрата, сторона которого равна 6 см.', 2);
INSERT INTO answers VALUES(default, '72 кв см', false, 16);
INSERT INTO answers VALUES(default, '12 кв см', false, 16);
INSERT INTO answers VALUES(default, '36 кв см', true, 16);
INSERT INTO answers VALUES(default, '24 кв см', false, 16);
INSERT INTO questions VALUES(17, 'Из двух пунктов одновременно навстречу друг другу вышли два пешехода и встретились через 2 ч. Найдите расстояние между пунктами, если скорость одного пешехода 4 км/ч, а другого — 5 км/ч.', 2);
INSERT INTO answers VALUES(default, '20 км', false, 17);
INSERT INTO answers VALUES(default, '18 км', true, 17);
INSERT INTO answers VALUES(default, '9 км', false, 17);
INSERT INTO answers VALUES(default, '16 км', false, 17);
INSERT INTO questions VALUES(18, 'Какое число на 7 меньше, чем 7042?', 2);
INSERT INTO answers VALUES(default, '106', false, 18);
INSERT INTO answers VALUES(default, '7049', false, 18);
INSERT INTO answers VALUES(default, '7035', true, 18);
INSERT INTO answers VALUES(default, '1006', false, 18);
INSERT INTO questions VALUES(19, 'Укажите число, содержащее 15 сотен и 5 единиц.', 2);
INSERT INTO answers VALUES(default, '15005', false, 19);
INSERT INTO answers VALUES(default, '155', false, 19);
INSERT INTO answers VALUES(default, '1505', true, 19);
INSERT INTO answers VALUES(default, '1550', false, 19);
INSERT INTO questions VALUES(20, 'Килограмм клубники стоит 130р. Какое наибольшее количество клубники можно купить на 700 р.?', 2);
INSERT INTO answers VALUES(default, '4 кг', false, 20);
INSERT INTO answers VALUES(default, '6 кг', false, 20);
INSERT INTO answers VALUES(default, '3 кг', false, 20);
INSERT INTO answers VALUES(default, '5 кг', true, 20);
INSERT INTO questions VALUES(21, 'Вычитаемое уменьшили на 3. а уменьшаемое оставили без изменения. Как изменлиась разность чисел?', 2);
INSERT INTO answers VALUES(default, 'увеличилась в 3 раза', false, 21);
INSERT INTO answers VALUES(default, 'уменьшилась на 3', true, 21);
INSERT INTO answers VALUES(default, 'нельзя определить', false, 21);
INSERT INTO answers VALUES(default, 'увеличилась на 3', false, 21);
INSERT INTO questions VALUES(22, 'Найдите значение выражения 402 : (27 — 21) +4 * 17.', 2);
INSERT INTO answers VALUES(default, '1207', false, 22);
INSERT INTO answers VALUES(default, '135', true, 22);
INSERT INTO answers VALUES(default, '125', false, 22);
INSERT INTO answers VALUES(default, '1107', false, 22);
INSERT INTO questions VALUES(23, 'Чему равно частное чисел 27063 и 9?', 2);
INSERT INTO answers VALUES(default, '3006', false, 23);
INSERT INTO answers VALUES(default, '27054', false, 23);
INSERT INTO answers VALUES(default, '3007', true, 23);
INSERT INTO answers VALUES(default, '307', false, 23);
INSERT INTO questions VALUES(24, 'Решите уравнение х — 124 = 68.', 2);
INSERT INTO answers VALUES(default, '64', false, 24);
INSERT INTO answers VALUES(default, '56', true, 24);
INSERT INTO answers VALUES(default, '66', false, 24);
INSERT INTO answers VALUES(default, '192', false, 24);


-- Средний тест по математике
INSERT INTO tests VALUES(3, 'Средний тест по математике', 2, 'medium', 7);
INSERT INTO questions VALUES(25, 'Цилиндр с радиусом 3 и высотой 4 имеет такую полную площадь поверхности:', 3);
INSERT INTO answers VALUES(default, '62п', false, 25);
INSERT INTO answers VALUES(default, '12п', false, 25);
INSERT INTO answers VALUES(default, '42п', true, 25);
INSERT INTO answers VALUES(default, '48п', false, 25);
INSERT INTO questions VALUES(26, 'Определите объем правильной треугольной призмы, боковые грани которой являются квадратами, а периметр основы 12 :', 3);
INSERT INTO answers VALUES(default, '16', true, 26);
INSERT INTO answers VALUES(default, '64', false, 26);
INSERT INTO answers VALUES(default, '64', false, 26);
INSERT INTO answers VALUES(default, '48', false, 26);
INSERT INTO questions VALUES(27, 'Какую область имеет сфера с радиусом 2 :', 3);
INSERT INTO answers VALUES(default, '36п', false, 27);
INSERT INTO answers VALUES(default, '100п', false, 27);
INSERT INTO answers VALUES(default, '56п', false, 27);
INSERT INTO answers VALUES(default, '48п', true, 27);
INSERT INTO questions VALUES(28, 'Какому значению среди наведенных может ровняться длинна стороны АС треугольника АВС, если АВ = 3см, ВС = 10см :', 3);
INSERT INTO answers VALUES(default, '3 см', false, 28);
INSERT INTO answers VALUES(default, '11 см', true, 28);
INSERT INTO answers VALUES(default, '7 см', false, 28);
INSERT INTO answers VALUES(default, '15 см', false, 28);
INSERT INTO questions VALUES(29, 'Выпущена партия из 300 лотерейных билетов. Вероятность того, что наугад выбранный билет из этой партии будет выигрышным, равна 0,2. Определите количество билетов без выигрыша среди этих 300 билетов :', 3);
INSERT INTO answers VALUES(default, '6', false, 29);
INSERT INTO answers VALUES(default, '60', false, 29);
INSERT INTO answers VALUES(default, '294', true, 29);
INSERT INTO answers VALUES(default, '150', false, 29);
INSERT INTO questions VALUES(30, 'Высота правильной четырехугольной пирамиды равна 3 см, а сторона ее основания — 12 см. Найдите длину бокового ребра пирамиды.', 3);
INSERT INTO answers VALUES(default, '6', false, 30);
INSERT INTO answers VALUES(default, '16', false, 30);
INSERT INTO answers VALUES(default, '15', false, 30);
INSERT INTO answers VALUES(default, '9', true, 30);
INSERT INTO questions VALUES(31, 'Длина стороны AB параллелограмма ABCD равна 10 см, а его периметр — 60 см. Определите длину стороны BC.', 3);
INSERT INTO answers VALUES(default, '50', false, 31);
INSERT INTO answers VALUES(default, '40', false, 31);
INSERT INTO answers VALUES(default, '25', false, 31);
INSERT INTO answers VALUES(default, '20', true, 31);
INSERT INTO questions VALUES(32, 'В пространстве заданы прямая b и точка A, не принадлежащая этой прямой. Сколько всего существует разных плоскостей, проходящих через точку A которые не имеют общих точек с прямой b?', 3);
INSERT INTO answers VALUES(default, 'бесконечно много', true, 32);
INSERT INTO answers VALUES(default, 'только две', false, 32);
INSERT INTO answers VALUES(default, 'ни одной', false, 32);
INSERT INTO answers VALUES(default, 'только одна', false, 32);

-- English test
INSERT INTO tests VALUES(4, 'English test', 1, 'hard', 5);
INSERT INTO questions VALUES(33, 'New York consists of______ boroughs.', 4);
INSERT INTO answers VALUES(default, 'four', false, 33);
INSERT INTO answers VALUES(default, 'five', true, 33);
INSERT INTO answers VALUES(default, 'six', false, 33);
INSERT INTO answers VALUES(default, 'three', false, 33);
INSERT INTO questions VALUES(34, '______ is the heart of New York.', 4);
INSERT INTO answers VALUES(default, 'Manhattan', true, 34);
INSERT INTO answers VALUES(default, 'Staten Island', false, 34);
INSERT INTO answers VALUES(default, 'Central Park', false, 34);
INSERT INTO answers VALUES(default, 'Broadway', false, 34);
INSERT INTO questions VALUES(35, 'When tourists come to America by sea they are welcomed by______', 4);
INSERT INTO answers VALUES(default, 'The Empire State Building', false, 35);
INSERT INTO answers VALUES(default, 'The Statue of Liberty', true, 35);
INSERT INTO answers VALUES(default, 'The Chrysler', false, 35);
INSERT INTO answers VALUES(default, 'Times Square', false, 35);
INSERT INTO questions VALUES(36, 'If you want to see the most beautiful Christmas tree in New York, you should go to _____', 4);
INSERT INTO answers VALUES(default, 'The Rockefeller Centre', true, 36);
INSERT INTO answers VALUES(default, 'Central Park', false, 36);
INSERT INTO answers VALUES(default, 'Times Square', false, 36);
INSERT INTO answers VALUES(default, 'Broadway', false, 36);
INSERT INTO questions VALUES(37, '“Big Apple” is the nickname of _____', 4);
INSERT INTO answers VALUES(default, 'Chicago', false, 37);
INSERT INTO answers VALUES(default, 'Washington D.C.', false, 37);
INSERT INTO answers VALUES(default, 'New York metro', false, 37);
INSERT INTO answers VALUES(default, 'New York', true, 37);
INSERT INTO questions VALUES(38, 'You can see a rich collection of _______ in the Guggenheim Museum.', 4);
INSERT INTO answers VALUES(default, 'Ancient books', false, 38);
INSERT INTO answers VALUES(default, 'Coins', false, 38);
INSERT INTO answers VALUES(default, 'Modern art', true, 38);
INSERT INTO answers VALUES(default, 'icons', false, 38);
INSERT INTO questions VALUES(39, 'Lima`s father … all day long today and he is very tired.', 4);
INSERT INTO answers VALUES(default, 'worked', false, 39);
INSERT INTO answers VALUES(default, 'work', false, 39);
INSERT INTO answers VALUES(default, 'has been working', true, 39);
INSERT INTO answers VALUES(default, 'was working', false, 39);
INSERT INTO questions VALUES(40, 'New York city is situated in the state of______', 4);
INSERT INTO answers VALUES(default, 'California', false, 40);
INSERT INTO answers VALUES(default, 'New York', true, 40);
INSERT INTO answers VALUES(default, 'Florida', false, 40);
INSERT INTO answers VALUES(default, 'Texas', false, 40);
INSERT INTO questions VALUES(41, 'The largest borough of New York is ______', 4);
INSERT INTO answers VALUES(default, 'The Bronx', false, 41);
INSERT INTO answers VALUES(default, 'Staten Island', false, 41);
INSERT INTO answers VALUES(default, 'Manhattan', false, 41);
INSERT INTO answers VALUES(default, 'Queens', true, 41);
INSERT INTO questions VALUES(42, 'Which borough was an independent city until 1898?', 4);
INSERT INTO answers VALUES(default, 'Brooklyn', true, 42);
INSERT INTO answers VALUES(default, 'Manhattan', false, 42);
INSERT INTO answers VALUES(default, 'The Bronx', false, 42);
INSERT INTO answers VALUES(default, 'Staten Island', false, 42);
INSERT INTO questions VALUES(43, 'The longest street in New York city is ______', 4);
INSERT INTO answers VALUES(default, 'Ann Street', false, 43);
INSERT INTO answers VALUES(default, 'Washington Street', false, 43);
INSERT INTO answers VALUES(default, 'Broadway', false, 43);
INSERT INTO answers VALUES(default, 'Jane Street', true, 43);

-- Тест по истории
INSERT INTO tests VALUES(5, 'Тест по истории', 3, 'hard', 4);
INSERT INTO questions VALUES(44, '«Отцом истории» европейцы называют:', 5);
INSERT INTO answers VALUES(default, 'Нестора', false, 44);
INSERT INTO answers VALUES(default, 'Геродота', true, 44);
INSERT INTO answers VALUES(default, 'Грушевского', false, 44);
INSERT INTO questions VALUES(45, 'Скифскую пектораль нашел археолог:', 5);
INSERT INTO answers VALUES(default, 'Хвойка', false, 45);
INSERT INTO answers VALUES(default, 'Мозолевский', true, 45);
INSERT INTO answers VALUES(default, 'Телегин', false, 45);
INSERT INTO questions VALUES(46, 'Древнегреческая муза истории имеет имя:', 5);
INSERT INTO answers VALUES(default, 'Мнемозина', false, 46);
INSERT INTO answers VALUES(default, 'Клио', true, 46);
INSERT INTO answers VALUES(default, 'Каллиопа', false, 46);
INSERT INTO questions VALUES(47, 'Слово «суббота» имеет происхождение:', 5);
INSERT INTO answers VALUES(default, 'древнеримское', false, 47);
INSERT INTO answers VALUES(default, 'древнееврейское', true, 47);
INSERT INTO questions VALUES(48, 'Какая из дат не относится к XIX ст.:', 5);
INSERT INTO answers VALUES(default, '1825', false, 48);
INSERT INTO answers VALUES(default, '1800', true, 48);
INSERT INTO answers VALUES(default, '1861', false, 48);
INSERT INTO answers VALUES(default, '1862', false, 48);
INSERT INTO questions VALUES(49, 'Древнерусская мера «пядь» – это длина:', 5);
INSERT INTO answers VALUES(default, 'пальца', false, 49);
INSERT INTO answers VALUES(default, 'ладони', true, 49);
INSERT INTO answers VALUES(default, 'шага', false, 49);
INSERT INTO questions VALUES(50, 'Наука, изучающая монеты - это:', 5);
INSERT INTO answers VALUES(default, 'геральдика', false, 50);
INSERT INTO answers VALUES(default, 'нумизматика', true, 50);
INSERT INTO answers VALUES(default, 'сфрагистика', false, 50);
INSERT INTO questions VALUES(51, 'Письменным источником истории является:', 5);
INSERT INTO answers VALUES(default, 'орнамент', false, 51);
INSERT INTO answers VALUES(default, 'газета', true, 51);
INSERT INTO answers VALUES(default, 'картина', false, 51);
INSERT INTO questions VALUES(52, 'В Древнем Египте для письма использовали:', 5);
INSERT INTO answers VALUES(default, 'бересту', false, 52);
INSERT INTO answers VALUES(default, 'папирус', true, 52);
INSERT INTO answers VALUES(default, 'бумагу', false, 52);
INSERT INTO questions VALUES(53, 'Хранилищем документов разных времен есть:', 5);
INSERT INTO answers VALUES(default, 'библиотека', false, 53);
INSERT INTO answers VALUES(default, 'архив', true, 53);
INSERT INTO answers VALUES(default, 'музей', false, 53);
