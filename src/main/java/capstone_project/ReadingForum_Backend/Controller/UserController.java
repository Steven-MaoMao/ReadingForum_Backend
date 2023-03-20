package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> map) {
        try {
            User user = userService.selectByUsername(map.get("username"));
            if (user != null && user.getPassword().equals(map.get("password"))) {
                return JWT.createToken(user.getUsername());
            } else {
                return "ERROR";
            }
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
