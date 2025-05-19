package src;

public class React {
    private ReactType reactType;
    private final User user;

    public React(ReactType reactType, User user) {
        this.reactType = reactType;
        this.user = user;
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
