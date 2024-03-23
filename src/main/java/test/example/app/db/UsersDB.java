package test.example.app.db;

import org.springframework.stereotype.Repository;
import test.example.app.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UsersDB {
    private final static AtomicLong ID_COUNTER = new AtomicLong(1);
    private final Map<Long, User> userData = new ConcurrentHashMap<>();

    public boolean create(String name, String login, String pass) {
        User existingUser = userData.values().stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);

        if (existingUser == null) {
            Long newUserId = ID_COUNTER.addAndGet(1);
            User newUser = new User(newUserId, name, login, pass);
            userData.put(newUserId, newUser);
        }

        return existingUser == null;
    }

    public Iterable<User> findAll() {
        return userData.values();
    }

    public User findByLogin(String login) {
        return userData.values().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().orElse(null);
    }

    public User findByLoginAndPass(String login, String pass) {
        return userData.values().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPass().equals(pass))
                .findFirst().orElse(null);
    }
}
