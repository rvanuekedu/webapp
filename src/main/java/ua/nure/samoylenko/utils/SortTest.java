package ua.nure.samoylenko.utils;

import ua.nure.samoylenko.dto.TestDTO;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SortTest {

    private static Map<String, Integer> complexityMap = new TreeMap<String, Integer>() {{
        put("easy", 1);
        put("medium", 2);
        put("hard", 3);
    }};

    public static final Comparator<TestDTO> SORT_TESTS_BY_NAME_AZ = Comparator.comparing(TestDTO::getTestName);

    public static final Comparator<TestDTO> SORT_TESTS_BY_NAME_ZA = (t1, t2) -> t2.getTestName().compareTo(t1.getTestName());

    public static final Comparator<TestDTO> SORT_TESTS_BY_SUBJECT_AZ = Comparator.comparing(TestDTO::getSubjectName);

    public static final Comparator<TestDTO> SORT_TESTS_BY_SUBJECT_ZA = (t1, t2) -> t2.getSubjectName().compareTo(t1.getSubjectName());

    public static final Comparator<TestDTO> SORT_TESTS_BY_NUMBER_OF_QUESTIONS_09 = Comparator.comparingInt(TestDTO::getNumberOfQuestions);

    public static final Comparator<TestDTO> SORT_TESTS_BY_NUMBER_OF_QUESTIONS_90 = (t1, t2) -> t2.getNumberOfQuestions() - t1.getNumberOfQuestions();

    public static final Comparator<TestDTO> SORT_TEST_BY_COMPLEXITY_INCREASE = Comparator.comparingInt(t -> SortTest.complexityMap.get(t.getComplexityName()));

    public static final Comparator<TestDTO> SORT_TEST_BY_COMPLEXITY_DECREASE = (t1, t2) -> SortTest.complexityMap.get(t2.getComplexityName()) - SortTest.complexityMap.get(t1.getComplexityName());
}
