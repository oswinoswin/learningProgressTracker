package tracker;

import java.util.*;
import java.util.function.Function;

public class ScoresController {
    Map<Integer, Score> scores;
    List<Subject> subjects;
    Subject java;
    Subject dsa;
    Subject databases;
    Subject spring;


    public ScoresController() {
        scores = new LinkedHashMap<>();
        subjects = new ArrayList<>(4);
        java =  new Subject("Java");
        dsa = new Subject("DSA");
        databases = new Subject("Databases");
        spring = new Subject("Spring");
        subjects.add(java);
        subjects.add(dsa);
        subjects.add(databases);
        subjects.add(spring);
    }

    public boolean containsID(int studentID){
        return scores.containsKey(studentID);
    }

    public void update(int id, Score studentScore){
        Score scoreToUpdate = scores.get(id);
        scoreToUpdate.update(studentScore.java, studentScore.dsa, studentScore.databases, studentScore.spring);
        java.add(id, studentScore.java);
        dsa.add(id, studentScore.dsa);
        databases.add(id, studentScore.databases);
        spring.add(id, studentScore.spring);
    }

    public void insertID(int id){
        scores.put(id, new Score(id, 0,0,0,0));
    }

    public void list(){
        scores.keySet().forEach(System.out::println);
    }

    public Score findStudentScores(int studentID){
        return scores.get(studentID);
    }

    public String statistics(){
        return  "Most popular: "+ popular() +"\n" +
                "Least popular: " +find(Comparator.comparing(Subject::enrolledStudents)) + "\n" +
                "Highest activity: " + activityHighest() + "\n" +
                "Lowest activity: " + find(Comparator.comparing(Subject::activities)) +"\n" +
                "Easiest course: " + find(Comparator.comparing(Subject::average).reversed()) + "\n" +
                "Hardest course: " + find(Comparator.comparing(Subject::average)) + "\n";
    }

    public String find(Comparator<Subject> comparator){
        subjects.sort(comparator);
        if (comparator.compare(subjects.get(0),subjects.get(subjects.size() -1)) == 0) return "n/a";
        StringBuilder result = new StringBuilder(subjects.get(0).getName());
        for (int i = 1; i<subjects.size(); i++){
            if(comparator.compare(subjects.get(i), subjects.get(0)) == 0){
                result.append(", ").append(subjects.get(i).getName());
            }
        }
        return result.toString();

    }



    public String courseDetails(String course){
        StringBuilder result = new StringBuilder(course + "\n" +
                "id\t" + "points\t" + "\tcompleted\n");
        List<Score> scoresList = new ArrayList<>(scores.values());
        scoresList.sort(Score.scoresComparator(course));
        for (Score score: scoresList){
            if (score.getPoints(course) == 0) break;
            result.append(score.printDetails(course));
        }

        return result.toString();
    }



    public String popular(){
        subjects.sort(Comparator.comparing(Subject::enrolledStudents).reversed());
        if (subjects.get(0).enrolledStudents() == 0) return "n/a";
        StringBuilder result = new StringBuilder(subjects.get(0).getName());
        int bestValue = subjects.get(0).enrolledStudents();
        for (int i = 1; i<subjects.size(); i++){
            if(subjects.get(i).enrolledStudents() == bestValue){
                result.append(", ").append(subjects.get(i).getName());
            }
        }
        return result.toString();
    }

    public String activityHighest(){
        subjects.sort(Comparator.comparing(Subject::activities).reversed());
        if (subjects.get(0).activities() == 0) return "n/a";
        StringBuilder result = new StringBuilder(subjects.get(0).getName());
        int bestValue = subjects.get(0).activities();
        for (int i = 1; i<subjects.size(); i++){
            if(subjects.get(i).activities() == bestValue){
                result.append(", ").append(subjects.get(i).getName());
            }
        }
        return result.toString();
    }

    public List<Integer> notifyForJava(){
        List<Integer> toNotify = new LinkedList<>();
        for (Score score: scores.values()){
            if (score.notifyForJava()){
                toNotify.add(score.studentID);
                score.isNotifiedJava = true;
            }
        }
        return toNotify;
    }

    public List<Integer> notifyForDSA(){
        List<Integer> toNotify = new LinkedList<>();
        for (Score score: scores.values()){
            if (score.notifyForDsa()){
                toNotify.add(score.studentID);
                score.isNotifiedDSA = true;
            }
        }
        return toNotify;
    }

    public List<Integer> notifyForDatabases(){
        List<Integer> toNotify = new LinkedList<>();
        for (Score score: scores.values()){
            if (score.notifyForDatabases()){
                toNotify.add(score.studentID);
                score.isNotifiedDatabases = true;
            }
        }
        return toNotify;
    }

    public List<Integer> notifyForSpring(){
        List<Integer> toNotify = new LinkedList<>();
        for (Score score: scores.values()){
            if (score.notifyForSpring()){
                toNotify.add(score.studentID);
                score.isNotifiedSpring = true;
            }
        }
        return toNotify;
    }

}
