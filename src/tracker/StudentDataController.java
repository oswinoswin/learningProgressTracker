package tracker;

import java.util.*;

public class StudentDataController {
    Map<Integer, StudentData> data;
    Set<String> emails;

    public StudentDataController(){
        data = new LinkedHashMap<>();
        emails = new HashSet<>();
    }

    public void  insert(StudentData studentData){
        data.put(studentData.id, studentData);
        emails.add(studentData.email);
    }

    static boolean firstNameIsCorrect(String name) {
        if (name.length() < 2) return false;
        String namePattern = "[A-Za-z]([a-zA-Z]*[-|']?[a-zA-Z])*";
        return name.matches(namePattern);
    }
    static boolean lastNameIsCorrect(String lastName){
        for (String namePart: lastName.split(" ")){
            if(namePart.isBlank())
                continue;
            if(!firstNameIsCorrect(namePart))
                return false;
        }
        return true;
    }
    static boolean emailIsCorrect(String email){
        String pattern = "^(\\w)+(\\.?\\w+)*@(\\w)+\\.(\\w)+$";
        return email.matches(pattern);
    }

    boolean containsEmail(String email){
        return emails.contains(email);
    }

    List<String> getNotifyMessages(List<Integer> studentsIDs, String course){
        List<String> messages = new ArrayList<>();
        for (int id: studentsIDs){
            String message = data.get(id).generateEmail(course);
            messages.add(message);
        }
        return messages;
    }

    public void list(){
        data.values().forEach(studentData -> System.out.println(studentData.id));
    }


}
