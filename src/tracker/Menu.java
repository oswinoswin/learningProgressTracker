package tracker;

import java.util.Scanner;
import java.util.Set;

public class Menu {
    Scanner scanner;
    int addedStudents;

    StudentDataController studentDataController;
    ScoresController scoresController;
    public static Set<String> availableCourses = Set.of("java", "dsa", "databases", "spring");

    public Menu() {
        this.scanner = new Scanner(System.in);
        addedStudents = 0;
        studentDataController = new StudentDataController();
        scoresController = new ScoresController();
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
            } else if ("add points".equals(input)) {
                addPoints();
            } else if ("find".equals(input)) {
                find();
            } else if ("list".equals(input)) {
                list();
            } else if ("statistics".equals(input)) {
                statistics();
            } else if (input.isBlank()){
                System.out.println("No input.");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    private void statistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println(scoresController.statistics());
        boolean back = false;
        while (!back){
            String input = scanner.nextLine();
            if ("back".equals(input)){
                back = true;
            } else {
                if (!availableCourses.contains(input.toLowerCase())){
                    System.out.println("Unknown course");
                } else {
                    System.out.println(scoresController.courseDetails(input));
                }
            }
        }

    }

    private void list() {
        if (addedStudents == 0){
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:  ");
        scoresController.list();
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return");
        boolean back = false;
        while (!back){
            String input = scanner.nextLine();
            if ("back".equals(input)){
                back = true;
            } else {
                int id;
                try {
                    id = Integer.parseInt(input);
                }catch (NumberFormatException e){
                    System.out.printf("No student is found for id=%s.%n", input);
                    continue;
                }
                if (!scoresController.containsID(id)){
                    System.out.printf("No student is found for id=%s.%n", input);
                    continue;
                }
                System.out.println(scoresController.findStudentScores(id));

            }
        }

    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        boolean back = false;
        while (!back){
            String input = scanner.nextLine();
            if ("back".equals(input)){
                back = true;
            } else {
                verifyPoints(input);
            }
        }
    }

    private void verifyPoints(String input) {
        int inputLength = input.split(" ").length;
        if(inputLength != 5){
            System.out.println("Incorrect points format.");
            return;
        }
        String[] splitted = input.split(" ");
        int studentID;
        try {
            studentID = Integer.valueOf(splitted[0]);
        }catch (NumberFormatException e){
            System.out.println("No student is found for id=%s".formatted(splitted[0]));
            return;
        }
        if (!scoresController.containsID(studentID)){
            System.out.println("No student is found for id=%d".formatted(studentID));
            return;
        }

        int[] ints = new int[4];
        for(int i = 0; i < 4; i++){
            try {
                int value = Integer.parseInt(splitted[i+1]);
                ints[i] = value;
            } catch (NumberFormatException e){
                System.out.println("Incorrect points format.");
                return;
            }
            if (ints[i] < 0){
                System.out.println("Incorrect points format.");
                return;
            }
        }

        scoresController.update(studentID, new Score(studentID, ints[0], ints[1], ints[2], ints[3]));
        System.out.println("Points updated.");

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

        if(!StudentDataController.firstNameIsCorrect(name)){
            System.out.println("Incorrect first name.");
            return;
        }
        if (!StudentDataController.lastNameIsCorrect(lastName)){
            System.out.println("Incorrect last name.");
            return;
        }
        if(!StudentDataController.emailIsCorrect(email)){
            System.out.println("Incorrect email.");
            return;
        }
        if (studentDataController.containsEmail(email)){
            System.out.println("This email is already taken.");
            return;
        }
        studentDataController.insert(new StudentData(name, lastName, email, addedStudents));
        scoresController.insertID(addedStudents);
        addedStudents++;

        System.out.println("The student has been added.");
    }






}

