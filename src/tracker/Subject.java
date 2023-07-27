package tracker;

import java.util.*;

public class Subject {
    String name;
    Set<Integer> studentIDs;
    List<Integer> points;


    public Subject(String name) {
        this.name = name;
        studentIDs = new HashSet<>();
        points = new LinkedList<>();
    }

    public void add(int studentID, int point){
        if(point > 0){
            studentIDs.add(studentID);
            points.add(point);
        }
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", enrolled=" + enrolledStudents() +
                ", activities=" + activities() +
                ", average=" + average() +
                '}';
    }

    public String getName(){
        return name;
    }

    public int enrolledStudents(){
        return studentIDs.size();
    }

    public int activities(){
        return points.size();
    }

    public double average(){
        if (points.size() == 0) return -1.0;
        return points.stream().reduce(Integer::sum).get().doubleValue() / (double) points.size();
    }

}
