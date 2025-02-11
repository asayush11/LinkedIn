package src;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkedInSystem {
    private final Map<String, User> users;
    private final Map<String, Messsage> messseges;
    private static LinkedInSystem instance;

    private LinkedInSystem() {
        this.users = new HashMap<>();
        this.messseges = new HashMap<>();
    }

    public static synchronized LinkedInSystem getInstance() {
        if(instance == null) {
            instance = new LinkedInSystem();
        }
        return instance;
    }

    public User addUser(String name) {
        String id = "US" + UUID.randomUUID().toString().substring(0,8);
        User user = new User(id, name);
        users.put(id, user);
        System.out.println(name + " registered");
        return user;
    }

    public void removeUser(String id) {
        users.remove(id);
        System.out.println(id + " deregistered");
    }

    public Messsage addMessage(User sender, User receiver, String content) {
        if(users.containsKey(sender.getId()) && users.containsKey(receiver.getId())) {
            String id = "MS" + UUID.randomUUID().toString().substring(0,8);
            Messsage messsage = new Messsage(id, sender, receiver, content);
            messseges.put(id, messsage);
            System.out.println(content + " added as message from " + sender.getName() + " to " + receiver.getName());
            return messsage;
        }
        System.out.println("Sender or receiver are not on LinkedIn");
        return null;
    }

    public void removeMessage(User user, String id) {
        if(users.containsKey(user.getId()) && messseges.containsKey(id) && messseges.get(id).getSender().getName().equals(user.getName())) {
            messseges.remove(id);
            System.out.println("Message removed");
            return;
        }
        System.out.println("Message can't be deleted");
    }

    public void displayMessages() {
        System.out.println("Messages in system:");
        messseges.values()
                .forEach(messsage -> System.out.println(messsage.getContent() + " from " + messsage.getSender().getName() + " to " + messsage.getReceiver().getName()));
    }
}
