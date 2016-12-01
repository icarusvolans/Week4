import java.util.ArrayList;

public class User {
    String name;
    static String password;
    ArrayList<Message> messages = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }


}