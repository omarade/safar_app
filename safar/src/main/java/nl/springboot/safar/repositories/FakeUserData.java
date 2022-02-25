package nl.springboot.safar.repositories;

import nl.springboot.safar.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeUserData {
    private final List<User> users = new ArrayList<>();

    public FakeUserData() {
        User a = new User(1, "Kevin", "Breda", "Kevin@live.com");
        User b = new User(2, "Mark", "Amsterdam", "Mark@live.com");
        User c = new User(3, "Jack", "Rotterdam", "Jack@live.com");
        User d = new User(4, "Marie", "Den Haag", "Marie@live.com");
        User e = new User(5, "Chuck", "Eindhoven", "Chuck@live.com");

        Collections.addAll(users, a, b, c, d, e);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserBy(int id){
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
