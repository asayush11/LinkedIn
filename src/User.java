package src;

import java.util.*;

public class User {
    private final String id;
    private final String name;
    private final Set<String> skills;
    private final List<Experience> experiences;
    private final Set<User> connections;
    private final Map<String, Post> posts;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.skills = new HashSet<>();
        this.experiences = new ArrayList<>();
        this.connections = new HashSet<>();
        this.posts = new HashMap<>();
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
        System.out.println(skill + " added for " + this.name);
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
        System.out.println(skill + " removed for " + this.name);
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
        System.out.println(experience.getOrg() + " added for " + this.name);
    }

    public void removeExperience(Experience experience) {
        experiences.remove(experience);
    }

    public void updateExperience(Date date, String org) {
        experiences.stream()
                .filter(experience -> experience.getOrg().equals(org))
                .forEach(experience -> experience.setEndDate(date));
        System.out.println(org + "'s  tenure updated for " + this.name);
    }

    public void addConnection(User connection) {
        this.connections.add(connection);
        connection.addConnection2(this);
        System.out.println(connection.getName() + " added for " + this.name);
    }

    private void addConnection2(User connection) {
        this.connections.add(connection);
        System.out.println(connection.getName() + " added for " + this.name);
    }

    public void removeConnection(User connection) {
        this.connections.remove(connection);
        connection.removeConnection2(this);
        System.out.println(connection.getName() + " removed for " + this.name);
    }

    private void removeConnection2(User connection) {
        this.connections.remove(connection);
        System.out.println(connection.getName() + " removed for " + this.name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void displaySkills() {
        System.out.println("Skills of " + this.name);
        skills.forEach(System.out::println);
    }

    public void displayExperiences() {
        System.out.println("Experiences of " + this.name);
        experiences
                .forEach(experience -> System.out.println(experience.getOrg() + " starting from " + experience.getStartDate()));
    }

    public void displayConnections() {
        System.out.println("Connections of " + this.name);
        connections
                .forEach(connection -> System.out.println(connection.getName()));
    }
    public Messsage addMessage(User receiver, String content) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        return system.addMessage(this, receiver, content);
    }
    public void removeMessage(String id) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        system.removeMessage(this, id);
    }

    public Post addPost(String content) {
        String id = "PO" + UUID.randomUUID().toString().substring(0,8);
        System.out.println(this.name + " posted " + content);
        Post post = new Post(id, this, content);
        posts.put(id, post);
        return post;
    }

    public void deletePost(String id) {
        System.out.println(this.name + " removed post");
        posts.remove(id);
    }

    public Comment addComment(Post post, String content) {
        String id = "CO" + UUID.randomUUID().toString().substring(0,8);
        return post.addComment(new Comment(id, this, content), this.id);
    }

    public void deleteComment(Post post) {
        post.removeComment(this.id);
    }

    public void addReactOnPost(ReactType reactType, Post post) {
        String id = "RE" + UUID.randomUUID().toString().substring(0,8);
        post.addReact(new React(id, reactType,this), this.id);
    }

    public void removeReactOnPost(Post post) {
        post.removeReact(this.id);
    }

    public void addReactOnComment(ReactType reactType, Comment comment) {
        String id = "RE" + UUID.randomUUID().toString().substring(0,8);
        comment.addReact(new React(id, reactType,this), this.id);
    }

    public void removeReactOnComment(Comment comment) {
        comment.removeReact(this.id);
    }

    public void displayPosts() {
        System.out.println("Posts of " + this.name);
        posts.values()
                .forEach(Post::display);
    }
}
