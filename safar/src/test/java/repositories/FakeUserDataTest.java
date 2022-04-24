package repositories;

import nl.springboot.safar.models.User;
import nl.springboot.safar.repositories.FakeUserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = FakeUserData.class)
public class FakeUserDataTest {

    @Autowired
    private FakeUserData fakeUserData;

    @Test
    public void getUserById(){
        User actualUser = fakeUserData.getUserBy(1);
        User expectedUser = new User(1, "Kevin", "Breda", "Kevin@live.com");
        assertEquals(expectedUser.getId(),actualUser.getId());
    }
}
