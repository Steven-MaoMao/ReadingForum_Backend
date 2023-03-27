package capstone_project.ReadingForum_Backend.ServiceTest;

import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void updateTest() throws IOException {
        User user = new User();
        user.setId(7);
        user.setUsername("user01");
        user.setPassword("a906449d5769fa7361d7ecc6aa3f6d28");
        user.setAvatar("上海大学");
        System.out.println(user);
        userService.update(user);
    }

    @Test
    public void insertTest() throws IOException {
        String username = "user06";
        String password = "123456";
        userService.insert(username, password);
    }
}
