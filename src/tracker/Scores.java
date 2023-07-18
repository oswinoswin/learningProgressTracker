package tracker;

public class Scores {
    int java;
    int dsa;
    int databases;
    int spring;
    int studentID;

    public Scores(int studentID, int java, int dsa, int databases, int spring) {
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


}
