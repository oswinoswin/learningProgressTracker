package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;

class ScoresControllerTest {

    static Score[] points;
    static ScoresController scoresController = new ScoresController();

    @BeforeAll
    static void setUp(){
        int student1 = 10000;
        int student2 = 10001;
        int student3 = 10002;
        points = new Score[]{new Score(student1, 8, 7, 7, 5),
                new Score(student1, 7, 6, 9, 7),
                new Score(student1, 6, 5, 5, 0),
                new Score(student2, 8, 0, 8, 6),
                new Score(student2, 7, 0, 0, 0),
                new Score(student2, 9, 0, 0, 5),
                new Score(student3, 10, 0, 0, 12)
        };
        scoresController.insertID(student1);
        scoresController.insertID(student2);
        scoresController.insertID(student3);
        for (Score score: points){
            scoresController.update(score.studentID, score);
        }
        scoresController.subjects.forEach(System.out::println);
    }

    @Test
    @DisplayName("Testing for most popular course")
    void popular() {
        System.out.println("Popular: " + scoresController.find(Comparator.comparing(Subject::enrolledStudents).reversed()));
        scoresController.subjects.forEach(System.out::println);
    }

    @Test
    void leastPopular() {
        System.out.println(scoresController.find(Comparator.comparing(Subject::enrolledStudents).reversed()));
    }

    @Test
    void statistic(){
        System.out.println(scoresController.statistics());
    }


    @ParameterizedTest
    @CsvSource({"Java", "DSA","Databases", "Spring"})
    void courseDetails(String course) {
        System.out.println("Course details: " + course);
        System.out.println(scoresController.courseDetails(course));
    }

    @Test
    void testNotify() {
        System.out.println(scoresController.notifyForJava());
    }
}