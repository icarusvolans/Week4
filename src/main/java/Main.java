import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
//        addTestUsers();
//        addTestMessages();

        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    String userPassword = session.attribute("userPassword");

                    User user = users.get(userName);
 //                   Message message1 = null;
                    HashMap m = new HashMap();
                    ArrayList<Message> threads = new ArrayList<>();
                    for (Message message : messages) {
                        if (message.author.equals(userName)) {
                            threads.add(message);
                        }
                    }
                    System.out.println(messages.size());
                    m.put("user", user);
                    m.put("messages", threads);
                    m.put("sessionName", userName);
                    m.put("sessionPassword", userPassword);
                    m.put("error", session.attribute("error"));

//                    m.put("sessionMessage", message1);
                    System.out.println("In Spark.get");
                    if (user == null || userPassword == null || userPassword.equals("") || !userPassword.equals(User.password)) {
                        return new ModelAndView(m, "index.html");
                    }
                    else {
                        return new ModelAndView(m, "messages.html");
                    }

                }),

                new MustacheTemplateEngine()
        );


        Spark.post(
                "/login",
                ((request, response) -> {
                    String userName = request.queryParams("loginName");
                    String userPassword = request.queryParams("loginPassword");

                    System.out.println("In Spark.login");
                    String wrongLogin = "";
                    Session session = request.session();

                    System.out.println("User Name: " + userName);
                    if (userName == null || userName.equals("")) {
                        wrongLogin = "No User Name. Please re-enter name.";
                        System.out.println("user name error");
                    }else if (userPassword == null || userPassword.equals("")) {
                        wrongLogin = "Please enter your password.";
                    }else {
                        User user = users.get(userName);

                        if (user == null) {
                            user = new User(userName, userPassword);
                            users.put(userName, user);
                            users.put(userPassword, user);
                        }else if (userPassword == null || userPassword.equals("") || !userPassword.equals(User.password)) {
                            wrongLogin = "Your User Name and password do not match. Please sign in again.";

                        }

                        session.attribute("user", user);
                    }
                    session.attribute("userName", userName);
                    session.attribute("userPassword", userPassword);
                    session.attribute("error", wrongLogin);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/create-message",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    if (name == null) {
                        throw new Exception("Not logged in.");
                    }

                    String text = request.queryParams("messageText");
                    if (text == null) {
                        throw new Exception("Didn't get necessary query parameters.");
                    }
                    Message m = new Message((messages.size() + 1), name, text);
                    messages.add(m);
                    for (Message message : messages) {
                        System.out.println(message.author + ": " + message.text);

                    }
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/edit-message",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    if (name == null) {
                        throw new Exception("Not logged in.");
                    }
                    int count = Integer.valueOf(request.queryParams("editNum")) -1;

                    String edit = request.queryParams("messageEdit");
                    Message m = new Message((count + 1), name, edit);
                    messages.set(count, m);
                    for (Message message : messages) {
                    }
                        response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/delete-message",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    if (name == null) {
                        throw new Exception("Not logged in.");
                    }
                    int count = Integer.valueOf(request.queryParams("deleteNum")) -1;
                    messages.remove(count);
                    response.redirect("/");
                    return "";
                })
        );
    }
}