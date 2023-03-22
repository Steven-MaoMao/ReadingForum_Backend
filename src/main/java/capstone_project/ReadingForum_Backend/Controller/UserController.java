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

    @DeleteMapping
    public Result delete(@RequestHeader("token") String token) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            Result result = new Result();
            if (user.isDeleted()) {
                result.setMessage("已删除！");
            } else {
                user.setDeleted(true);
                userService.update(user);
                result.setCode(1);
                result.setMessage("删除成功！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            Result result = new Result();
            if (user.getPassword().equals(map.get("oldPassword"))) {
                user.setPassword(map.get("newPassword"));
                userService.update(user);
                result.setCode(1);
                result.setMessage("修改密码成功！");
            } else {
                result.setMessage("原密码错误，修改密码失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updatepersonalinfo")
    public Result updatePersonalInfo(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            user.setNickname(map.get("nickname"));
            user.setAvatar(map.get("avatar"));
            user.setGender(map.get("gender"));
            user.setBirthday(map.get("birthday"));
            user.setPhone(map.get("phone"));
            user.setEmail(map.get("email"));
            user.setLocation(map.get("location"));
            user.setBio(map.get("bio"));
            userService.update(user);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("修改个人信息成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
