package capstone_project.ReadingForum_Backend.ServiceTest;

import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IGroupMemberService;
import capstone_project.ReadingForum_Backend.Service.ISubgroupService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISubgroupService subgroupService;
    @Autowired
    private IGroupMemberService groupMemberService;

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

    @Test
    public void selectFollowingByPage() throws IOException {
        int followerId = 7;
        int start = 0;
        System.out.println(userService.selectFollowingByPage(followerId, start));
    }

//    @Test
//    public void selectGroupMemberTest() throws IOException {
//        System.out.println(userService.selectGroupMember(3));
//    }

    @Test
    public void insertSubgroupTest() throws IOException {
        System.out.println(subgroupService.insertWithText("111", 16, 1, "111"));
    }

    @Test
    public void selectGroupMemberTest() throws IOException {
        System.out.println(groupMemberService.selectGroupMember(16, 7));
    }
}
