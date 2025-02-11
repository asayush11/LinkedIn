package src;

import java.util.HashMap;
import java.util.Map;

public class Comment {
    private final String id;
    private final User user;
    private final String content;
    private final Map<String, React> reacts;

    public Comment(String id, User user, String content) {
        this.user = user;
        this.content = content;
        this.reacts = new HashMap<>();
        this.id = id;
    }

    public void addReact(React react, String userID) {
        reacts.put(userID, react);
        System.out.println(react.getUser().getName() + " reacted " + react.getReactType());
    }

    public void removeReact(String userID) {
        reacts.remove(userID);
        System.out.println(userID + " removed reaction");
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public void display() {
        System.out.println(this.user.getName() + " commented " + this.content);
        displayReactions();
    }
    private void displayReactions() {
        System.out.println("Reactions in this comment");
        reacts.values()
                .forEach(react -> System.out.println(react.getUser().getName() + " Reacted " + react.getReactType()));
    }

    public String getId() {
        return id;
    }
}
