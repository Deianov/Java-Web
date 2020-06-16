package vehicles.events;

import vehicles.model.User;

public class UserCreationEvent {
    private final User user;

    public UserCreationEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
