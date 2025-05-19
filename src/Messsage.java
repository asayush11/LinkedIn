package src;

public class Messsage {
    private final String id;
    private final User sender;
    private final User receiver;
    private final String content;

    public Messsage(String id, User sender, User receiver, String content) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }
}
