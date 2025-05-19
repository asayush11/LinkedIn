package src;

import java.time.LocalDate;
import java.util.*;

public class User {
    private final String id;
    private final String name;
    private final Set<String> skills;
    private final List<Experience> experiences;
    private final Set<User> connections;
    private final Map<String, Post> posts;
    private final Map<String, List<Messsage>> messsageSent;
    private final Map<String, List<Messsage>> messsageReceived;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.skills = new HashSet<>();
        this.experiences = new ArrayList<>();
        this.connections = new HashSet<>();
        this.posts = new HashMap<>();
        this.messsageReceived = new HashMap<>();
        this.messsageSent = new HashMap<>();
    }

    public void addSkill(String skill) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        this.skills.add(skill);
        System.out.println(skill + " added for " + this.name);
    }

    public void removeSkill(String skill) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        this.skills.remove(skill);
        System.out.println(skill + " removed for " + this.name);
    }

    public void addExperience(Experience experience) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        experiences.add(experience);
        System.out.println(experience.getOrg() + " added for " + this.name);
    }

    public void removeExperience(Experience experience) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        experiences.remove(experience);
    }

    public void updateExperience(LocalDate date, String org) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        experiences.stream()
                .filter(experience -> experience.getOrg().equals(org))
                .forEach(experience -> experience.setEndDate(date));
        System.out.println(org + "'s  tenure updated for " + this.name);
    }

    public void addConnection(User connection) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(system.validateUser(connection) && system.validateUser(this)){
            this.connections.add(connection);
            connection.addConnection2(this);
            System.out.println(connection.getName() + " added for " + this.name);
        } else {
            System.out.println("You or connection is not on LinkedIn");
        }
    }

    private void addConnection2(User connection) {
        this.connections.add(connection);
        System.out.println(connection.getName() + " added for " + this.name);
    }

    public void removeConnection(User connection) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(system.validateUser(connection) && system.validateUser(this)){
            this.connections.remove(connection);
            connection.removeConnection2(this);
            System.out.println(connection.getName() + " removed for " + this.name);
        } else {
            this.connections.remove(connection);
            System.out.println("You or connection is not on LinkedIn");
        }
    }

    private void removeConnection2(User connection) {
        this.connections.remove(connection);
        System.out.println(connection.getName() + " removed for " + this.name);
    }

    public void removeAllConnection(){
        System.out.println("Removing all connections of " + this.name);
        connections.
                forEach(c -> c.removeConnection(this));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Messsage addMessage(User receiver, String content) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(system.validateUser(receiver) && system.validateUser(this)){
            String id = "MS" + UUID.randomUUID().toString().substring(0,8);
            Messsage messsage = new Messsage(id, this, receiver, content);
            if(!messsageSent.containsKey(receiver.getId())){
                messsageSent.put(receiver.getId(), new ArrayList<>());
            }
            messsageSent.get(receiver.getId()).add(messsage);
            receiver.addMessageToReceiverList(this, messsage);
            System.out.println(content + " added as message from " + this.getName() + " to " + receiver.getName());
            return messsage;
        } else {
            System.out.println("You or receiver is not on LinkedIn");
            return null;
        }
    }

    private void addMessageToReceiverList(User sender, Messsage messsage){
        if(!messsageReceived.containsKey(sender.getId())){
            messsageReceived.put(sender.getId(), new ArrayList<>());
        }
        messsageReceived.get(sender.getId()).add(messsage);
    }

    public Post addPost(String content) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return null;
        }
        String id = "PO" + UUID.randomUUID().toString().substring(0,8);
        System.out.println(this.name + " posted " + content);
        Post post = new Post(id, this, content);
        posts.put(id, post);
        return post;
    }

    public void deletePost(String id) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        System.out.println(this.name + " removed post");
        posts.remove(id);
    }

    public Comment addComment(Post post, String content) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return null;
        }
        String id = "CO" + UUID.randomUUID().toString().substring(0,8);
        return post.addComment(new Comment(id, this, content));
    }

    public void deleteComment(Post post, String commentID) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        post.removeComment(commentID);
    }

    public void addReactOnPost(ReactType reactType, Post post) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        post.addReact(new React(reactType,this), this.id);
    }

    public void removeReactOnPost(Post post) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        post.removeReact(this.id);
    }

    public void addReactOnComment(ReactType reactType, Comment comment, Post post) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        post.addReactOnComment(new React(reactType,this), this.id, comment);
    }

    public void removeReactOnComment(Comment comment, Post post) {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        post.removeReactOnComment(this.id, comment);
    }

    public void displayUser(){
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        System.out.println("Data of user " + this.getName());
        displayExperiences();
        displaySkills();
        displayConnections();
        displayPosts();
        displayMessagesReceived();
        displayMessagesSent();
    }

    private void displaySkills() {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        System.out.println("Skills of " + this.name);
        skills.forEach(System.out::println);
    }

    private void displayExperiences() {
        LinkedInSystem system = LinkedInSystem.getInstance();
        if(!system.validateUser(this)){
            System.out.println("Invalid user");
            return;
        }
        System.out.println("Experiences of " + this.name);
        experiences
                .forEach(experience -> System.out.println(experience.getOrg() + " starting from " + experience.getStartDate()));
    }

    private void displayConnections() {
        System.out.println("Connections of " + this.name);
        connections
                .forEach(connection -> System.out.println(connection.getName()));
    }

    private void displayPosts() {
        System.out.println("Posts of " + this.name);
        posts.values()
                .forEach(Post::display);
    }

    private void displayMessagesSent(){
        System.out.println("Messages sent by " + this.name);
        messsageSent.values()
                .stream().flatMap(Collection::stream)
                .forEach(m -> System.out.println("Message " + m.getContent() + " sent to " + m.getReceiver().getName()));
    }

    private void displayMessagesReceived(){
        System.out.println("Messages sent to " + this.name);
        messsageReceived.values()
                .stream().flatMap(Collection::stream)
                .forEach(m -> System.out.println("Message " + m.getContent() + " sent by " + m.getSender().getName()));
    }
}
