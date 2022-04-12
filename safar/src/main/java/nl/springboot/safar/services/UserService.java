package nl.springboot.safar.services;

import nl.springboot.safar.models.User;
import nl.springboot.safar.repositories.FakeUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final FakeUserData fakeUserData;

    public UserService(FakeUserData fakeUserData) {
        this.fakeUserData = fakeUserData;
    }

    public List<User> getAllUsers(){
        return fakeUserData.getUsers();
    }

    public User getUserBy(int id){
        return fakeUserData.getUserBy(id);
    }
}
