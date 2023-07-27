package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class Score {
    int java;
    int dsa;
    int databases;

    static final int maxJava = 600;
    static final int maxDsa = 400;
    static final int maxDatabases = 480;
    static final int maxSpring = 550;



    public int getJava() {
        return java;
    }

    public int getDsa() {
        return dsa;
    }

    public int getDatabases() {
        return databases;
    }

    public int getSpring() {
        return spring;
    }

    int spring;

    public int getStudentID() {
        return studentID;
    }

    int studentID;

    public Score(int studentID, int java, int dsa, int databases, int spring) {
        this.java = java;
        this.dsa = dsa;
        this.databases = databases;
        this.spring = spring;
        this.studentID = studentID;
    }

    public void update(int java, int dsa, int databases, int spring){
        this.java += java;
        this.dsa += dsa;
        this.databases += databases;
        this.spring += spring;
    }

    @Override
    public String toString() {
        return studentID + " points: " +
                "Java=" + java +
                "; DSA=" + dsa +
                "; Databases=" + databases +
                "; Spring=" + spring;
    }

    public static Comparator<Score> scoresComparator(String course){
        return switch (course.toLowerCase()) {
            case "java" -> Comparator.comparing(Score::getJava).reversed().thenComparing(Score::getStudentID);
            case "dsa" -> Comparator.comparing(Score::getDsa).reversed().thenComparing(Score::getStudentID);
            case "databases" -> Comparator.comparing(Score::getDatabases).reversed().thenComparing(Score::getStudentID);
            case "spring" -> Comparator.comparing(Score::getSpring).reversed().thenComparing(Score::getStudentID);
            default -> null;
        };
    }

    int getPoints(String course){
        return switch (course.toLowerCase()) {
            case "java" -> getJava();
            case "dsa" -> getDsa();
            case "databases" -> getDatabases();
            case "spring" -> getSpring();
            default -> 0;
        };
    }

    private String getPercents(String course){
        switch (course.toLowerCase()) {
            case "java" -> {
                return new BigDecimal(((double) getJava() / (double) maxJava) * 100.0).setScale(1, RoundingMode.HALF_UP) + "%";
            }
            case "dsa" -> {
                return new BigDecimal(((double) getDsa() / (double) maxDsa) * 100.0).setScale(1, RoundingMode.HALF_UP) + "%";
            }
            case "databases" -> {
                return new BigDecimal(((double) getDatabases() / (double) maxDatabases) * 100.0).setScale(1, RoundingMode.HALF_UP) + "%";
            }
            case "spring" -> {
                return new BigDecimal(((double) getSpring() / (double) maxSpring) * 100.0).setScale(1, RoundingMode.HALF_UP) + "%";
            }
            default -> throw new IllegalStateException("Unexpected value: " + course);
        }
    }



    public String printDetails(String course){
        return studentID + "\t" + getPoints(course) + "\t" +getPercents(course) + "\n";
    }


}
