package tracker;

public class StudentData {
    String firstName;
    String lastName;
    String email;
    int id;

    public StudentData(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public String generateEmail(String course){
        return "To: " + this.email + "\n" +
                "Re: Your Learning Progress\n" +
                "Hello, " + firstName + " " + lastName+ "! You have accomplished our " + course + " course!";
    }

}
