package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            User user = userService.selectByUsername(map.get("username"));
            if (user != null && user.getPassword().equals(map.get("password"))) {
                result.setCode(1);
                result.setMessage("登录成功！");
                Map<String, String> data = new HashMap<String, String>();
                data.put("token", JWT.createToken(user.getUsername()));
                result.setData(data);
            } else {
                result.setMessage("账号或密码错误，登录失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            if (userService.selectByUsername(map.get("username")) != null) {
                result.setMessage("用户名已存在！");
            } else {
                userService.insert(map.get("username"), map.get("password"));
                result.setCode(1);
                result.setMessage("注册成功！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
