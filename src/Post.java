package src;

import java.util.HashMap;
import java.util.Map;

public class Post {
    private final String id;
    private final User user;
    private final String content;
    private final Map<String, React> reacts;
    private final Map<String, Comment> comments;

    public Post(String id, User user, String content) {
        this.user = user;
        this.content = content;
        this.reacts = new HashMap<>();
        this.comments = new HashMap<>();
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

    private void displayReactions() {
        System.out.println("Reactions in this post");
        reacts.values()
                .forEach(react -> System.out.println(react.getUser().getName() + " Reacted " + react.getReactType()));
    }

    public Comment addComment(Comment comment) {
        comments.put(comment.getId(), comment);
        System.out.println(comment.getUser().getName() + " commented " + comment.getContent());
        return comment;
    }

    public void removeComment(String commentID) {
        User commenter = comments.get(commentID).getUser();
        comments.remove(commentID);
        System.out.println(commenter.getName() + " removed comment");
    }

    private void displayComments() {
        System.out.println("Comments in this post");
        comments.values()
                .forEach(Comment::display);
    }

    public String getId() {
        return id;
    }

    public void display() {
        System.out.println(this.content);
        displayReactions();
        displayComments();
    }

    public void addReactOnComment(React react, String userID, Comment comment){
        comments.get(comment.getId()).addReact(react, userID);
    }
    public void removeReactOnComment(String userID, Comment comment){
        comments.get(comment.getId()).removeReact(userID);
    }
}
