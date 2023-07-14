package tracker;

import java.util.Scanner;

public class Menu {
    Scanner scanner;
    int addedStudents;

    public Menu() {
        this.scanner = new Scanner(System.in);
        addedStudents = 0;
    }

    public void start(){
        System.out.println("Learning Progress Tracker");
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if ("exit".equals(input)){
                exit = true;
                System.out.println("Bye!");
            } else if ("add students".equals(input)) {
                addStudents();
            } else if ("back".equals(input)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if (input.isBlank()){
                System.out.println("No input.");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return");
        boolean back = false;
        while (!back){
            String input = scanner.nextLine();
            if ("back".equals(input)){
                back = true;
                System.out.printf("Total %d students have been added.\n", addedStudents);
            } else {
                verifyCredentials(input);
            }
        }
    }

    void verifyCredentials(String input){
        int inputLength = input.split(" ").length;
        if(inputLength < 3){
            System.out.println("Incorrect credentials.");
            return;
        }
        String name = input.split(" ")[0];

        String email = input.split(" ")[inputLength - 1];
        String lastName = input.replaceFirst(name + " ", "");
        lastName = lastName.replace(" " + email, "");
//        System.out.println("Verifying");
//        System.out.println("First Name: " + name);
//        System.out.println("Last Name: " + lastName);
//        System.out.println("email: " + email);

        if(!firstNameIsCorrect(name)){
            System.out.println("Incorrect first name.");
            return;
        }
        if (!lastNameIsCorrect(lastName)){
            System.out.println("Incorrect last name.");
            return;
        }
        if(!emailIsCorrect(email)){
            System.out.println("Incorrect email.");
            return;
        }
        addedStudents++;
        System.out.println("The student has been added.");
    }

    static boolean firstNameIsCorrect(String name) {
        if (name.length() < 2) return false;
        String namePattern = "[A-Za-z]([a-zA-Z]*[-|']?[a-zA-Z])*";
        return name.matches(namePattern);
    }

    static boolean lastNameIsCorrect(String lastName){
//        System.out.println(lastName.split(" "));
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
}

