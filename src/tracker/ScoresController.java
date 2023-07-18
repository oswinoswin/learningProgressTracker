package tracker;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoresController {
    Map<Integer, Scores> scores;

    public ScoresController() {
        scores = new LinkedHashMap<>();
    }

    public boolean containsID(int studentID){
        return scores.containsKey(studentID);
    }

    public void update(int id, Scores studentScores){
        Scores scoresToUpdate = scores.get(id);
        scoresToUpdate.update(studentScores.java, studentScores.dsa, studentScores.databases, studentScores.spring);
    }

    public void insertID(int id){
        scores.put(id, new Scores(id, 0,0,0,0));
    }

    public void list(){
        scores.keySet().forEach(System.out::println);
    }

    public Scores find(int studentID){
        return scores.get(studentID);
    }

}
