package tracker;

import java.util.LinkedHashMap;
import java.util.Map;

public class StudentDataController {
    Map<String, StudentData> data;

    public StudentDataController(){
        data = new LinkedHashMap<>();
    }

    public void  insert(StudentData studentData){
        data.put(studentData.email, studentData);
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
        return data.containsKey(email);
    }


}
