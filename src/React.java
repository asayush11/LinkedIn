package src;

public class React {
    private final String id;
    private ReactType reactType;
    private final User user;

    public React(String id, ReactType reactType, User user) {
        this.id = id;
        this.reactType = reactType;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public ReactType getReactType() {
        return reactType;
    }

    public void setReactType(ReactType reactType) {
        this.reactType = reactType;
    }

    public User getUser() {
        return user;
    }
}
