package ua.nure.samoylenko.utils;

public class SQLConstants {

    private SQLConstants() {
        throw new UnsupportedOperationException();
    }

    public static final String INSERT_ANSWER_FOR_QUESTION = "INSERT INTO answers VALUES(default, ?, ?, ?)";

    public static final String DELETE_ANSWER_BY_NAME = "DELETE FROM answers WHERE aText=?";

    public static final String SELECT_ALL_ANSWERS_BY_QUESTIONS_ID = "SELECT * FROM answers WHERE questions_id = ?";

    public static final String SELECT_ALL_ANSWERS_BY_TEST_ID = "SELECT answers.* FROM answers JOIN questions ON questions_id = questions.id JOIN tests ON tests_id = tests.id WHERE tests.id = ?";

    public static final String SELECT_ALL_ANSWERS_WHERE_TRUE = "SELECT * FROM answers WHERE questions_id = ? AND isCorrect = true";

    public static final String DELETE_ANSWER_BY_ID = "DELETE FROM answers WHERE id=?";


    public static final String SELECT_ALL_QUESTIONS_BY_TEST_ID = "SELECT * FROM questions WHERE tests_id = ?";

    public static final String INSERT_QUESTION_FOR_TEST = "INSERT INTO questions VALUES(default, ?, ?)";

    public static final String SELECT_ALL_QUESTIONS_AND_ANSWERS_BY_TEST_ID = "SELECT qText, aText, isCorrect, questions.id as 'question_id', questions_id as 'answer_question_id' FROM questions JOIN tests ON tests_id = tests.id LEFT JOIN answers ON questions.id = questions_id WHERE tests.id = ?";

    public static final String DELETE_QUESTION = "DELETE FROM questions WHERE id=?";


    public static final String SELECT_ALL_RESULTS_BY_STUDENT_ID = "SELECT * FROM results WHERE students_id=?";

    public static final String SELECT_RESULT_BY_STUDENT_ID_AND_TEST_ID = "SELECT * FROM results WHERE students_id=? AND tests_id=?";

    public static final String INSERT_RESULT_FOR_STUDENT_AND_TEST = "INSERT INTO results VALUES(default, ?, ?, ?, ?)";

    public static final String SELECT_RESULT_VALUE_BY_STUDENT_ID = "SELECT result FROM results WHERE students_id = ? ORDER BY id DESC LIMIT 1";

    public static final String SELECT_RESULTS_WITH_TEST_NAME = "SELECT result, date, test_name FROM results JOIN tests ON tests_id = tests.id WHERE students_id = ?";


    public static final String GET_STUDENT_BY_ID = "SELECT * FROM students WHERE id=?";

    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";

    public static final String UPDATE_STUDENTS_SET_BANNED_TRUE = "UPDATE students SET is_banned=TRUE WHERE id=?";

    public static final String UPDATE_STUDENTS_SET_BANNED_FALSE = "UPDATE students SET is_banned=FALSE WHERE id=?";

    public static final String INSERT_NEW_STUDENT = "INSERT INTO students VALUES(default, ?, ?, ?, default)";

    public static final String SELECT_STUDENT = "SELECT * FROM students WHERE user_email = ?";

    public static final String SELECT_STUDENT_ID_BY_EMAIL = "SELECT id FROM students WHERE user_email = ?";

    public static final String SELECT_ALL_BLOCKED_STUDENTS = "SELECT * FROM students WHERE is_banned = true";

    public static final String SELECT_ALL_UNBLOCKED_STUDENTS = "SELECT * FROM students WHERE is_banned = false";

    public static final String SELECT_STUDENT_BY_EMAIL = "SELECT is_banned FROM students WHERE user_email=?";


    public static final String GET_SUBJECT_BY_ID = "SELECT * FROM subjects WHERE id=?";

    public static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subjects";

    public static final String INSERT_NEW_SUBJECT = "INSERT INTO subjects VALUES(default, ?)";


    public static final String INSERT_NEW_TEST = "INSERT INTO tests VALUES(default, ?, ?, ?, ?)";

    public static final String SELECT_TEST = "SELECT * FROM tests WHERE id=?";

    public static final String SELECT_ALL_TEST = "SELECT * FROM tests ORDER BY id DESC";

    public static final String SELECT_ALL_TEST_WITH_SUBJECT_NAME = "SELECT tests.id AS test_id, complexity, test_name, time, subject_name, subjects_id FROM tests JOIN subjects ON tests.subjects_id = subjects.id";

    public static final String SELECT_ALL_TESTS_WITH_SUBJECT_NAME_AND_NUMBER_OF_QUESTIONS = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id GROUP BY tests.id ORDER BY tests.id DESC";

    public static final String SELECT_ALL_TESTS_ORDER_BY_TEST_NAME = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id GROUP BY tests.id ORDER BY test_name";

    public static final String SELECT_ALL_TESTS_ORDER_BY_TEST_SUBJECT = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id GROUP BY tests.id ORDER BY subject_name";

    public static final String SELECT_ALL_TESTS_ORDER_BY_TEST_NUMBER_OF_QUESTIONS = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id GROUP BY tests.id ORDER BY number_of_questions";

    public static final String SELECT_TEST_TIME_BY_TEST_ID = "SELECT time FROM tests WHERE id=?";

    public static final String SELECT_TEST_BY_NAME = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id GROUP BY tests.id HAVING test_name = ?";

    public static final String DELETE_TEST_BY_ID = "DELETE FROM tests WHERE id=?";

    public static final String UPDATE_TEST_NAME = "UPDATE tests SET test_name=? WHERE id=?";

    public static final String UPDATE_TEST_COMPLEXITY = "UPDATE tests SET complexity=? WHERE id=?";

    public static final String UPDATE_TEST_TIME = "UPDATE tests SET `time`=? WHERE id=?";

    public static final String SELECT_ALL_TESTS_BY_SUBJECT = "SELECT tests.*, count(tests_id) AS number_of_questions, subject_name FROM tests LEFT JOIN questions ON tests.id=tests_id JOIN subjects ON subjects_id=subjects.id WHERE subjects.id = ? GROUP BY tests.id ORDER BY tests.id DESC";



    public static final String SELECT_USERS_WHERE_EMAIL = "SELECT * FROM users WHERE email = ?";

    public static final String INSERT_USER = "INSERT INTO users VALUES(?, SHA1(?), default)";

    public static final String SELECT_USERS_WHERE_EMAIL_PASSWORD = "SELECT * FROM users WHERE email=? AND password=SHA1(?)";

    public static final String UPDATE_USER_EMAIL = "UPDATE users SET email=? WHERE email=?";

    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET password=SHA1(?) WHERE email=?";

}
