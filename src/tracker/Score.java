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
        switch (course.toLowerCase()){
            case "java": return Comparator.comparing(Score::getJava).reversed().thenComparing(Score::getStudentID);
            case "dsa": return Comparator.comparing(Score::getDsa).reversed().thenComparing(Score::getStudentID);
            case "databases": return Comparator.comparing(Score::getDatabases).reversed().thenComparing(Score::getStudentID);
            case "spring": return Comparator.comparing(Score::getSpring).reversed().thenComparing(Score::getStudentID);
            default: return null;
        }
    }

    int getPoints(String course){
        switch (course.toLowerCase()){
            case "java":  return getJava();
            case "dsa": return getDsa();
            case "databases": return getDatabases();
            case "spring":  return getSpring();
            default: return 0;
        }
    }

    private String getPercents(String course){
        switch (course.toLowerCase()){
            case "java":  {return new BigDecimal((Double.valueOf(getJava())/Double.valueOf(maxJava))*100.0).setScale(1, RoundingMode.HALF_UP).toString() + "%";}
            case "dsa": {return new BigDecimal((Double.valueOf(getDsa())/Double.valueOf(maxDsa))*100.0).setScale(1, RoundingMode.HALF_UP).toString() + "%";}
            case "databases": {return new BigDecimal((Double.valueOf(getDatabases())/Double.valueOf(maxDatabases))*100.0).setScale(1, RoundingMode.HALF_UP).toString() +"%";}
            case "spring":  {return new BigDecimal((Double.valueOf(getSpring())/Double.valueOf(maxSpring))*100.0).setScale(1, RoundingMode.HALF_UP).toString() +"%";}
            default:
                throw new IllegalStateException("Unexpected value: " + course);
        }
    }



    public String printDetails(String course){
        return studentID + "\t" + getPoints(course) + "\t" +getPercents(course) + "\n";
    }


}
