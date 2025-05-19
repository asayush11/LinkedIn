package src;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkedInSystem {
    private final Map<String, User> users;
    private static volatile LinkedInSystem instance;

    private LinkedInSystem() {
        this.users = new HashMap<>();
    }

    public static synchronized LinkedInSystem getInstance() {
        if(instance == null) {
            synchronized (LinkedInSystem.class) {
                if(instance == null) {
                    instance = new LinkedInSystem();
                }
            }
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
        users.get(id).removeAllConnection();
        users.remove(id);
        System.out.println(id + " deregistered");
    }

    public boolean validateUser(User user){
        return users.containsKey(user.getId());
    }
}
