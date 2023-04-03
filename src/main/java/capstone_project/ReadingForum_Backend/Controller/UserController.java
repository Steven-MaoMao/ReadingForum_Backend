package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IBookService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;

    @Value("${web.uploadPath}")
    private String baseUploadPath;

    @GetMapping("/userInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户信息成功！");
            user.setId(0);
            user.setPassword(null);
            user.setBan(false);
            user.setDeleted(false);
            Map data = new HashMap<String, Object>();
            data.put("userInfo", user);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户信息失败！");
            return result;
        }
    }

    @GetMapping("/favouriteByPage")
    public Result getFavouriteByPage(@RequestHeader("token") String token, @RequestParam int page) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            int start = (page - 1) * 16 + 1;
            List<Book> bookList = bookService.selectFavouriteByPage(id, start);
            int num = bookService.selectFavouriteNum(id);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户收藏成功！");
            Map data = new HashMap<String, Object>();
            data.put("bookList", bookList);
            data.put("num", num);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户收藏失败！");
            return result;
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            User user = userService.selectByUsername(map.get("username"));
            if (user != null && user.getPassword().equals(map.get("password")) && !user.isBan()) {
                result.setCode(1);
                result.setMessage("登录成功！");
                user.setId(0);
                user.setPassword(null);
                user.setBan(false);
                user.setDeleted(false);
                Map data = new HashMap<String, Object>();
                data.put("token", JWT.createToken(user.getUsername()));
                data.put("userInfo", user);
                result.setData(data);
            } else if (user.isBan()) {
                result.setMessage("账号状态异常，登录失败！");
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

    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(@RequestHeader("token") String token, @RequestParam("file") MultipartFile newAvatar) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            Result result = new Result();
            if (newAvatar.isEmpty()) {
                result.setMessage("文件为空，上传失败！");
                return result;
            }
            String[] splitFilename = newAvatar.getOriginalFilename().split("\\.");
            String ext = "." + splitFilename[splitFilename.length - 1];
            String uuid = UUID.randomUUID().toString();
            String newAvatarName = baseUploadPath + "avatar/" + uuid + ext;
            newAvatar.transferTo(new File(newAvatarName));
            if (user.getAvatar() != null) {
                File oldAvatar = new File(baseUploadPath + user.getAvatar().substring(10));
                if (oldAvatar.exists()) {
                    oldAvatar.delete();
                }
            }
            String avatarPath = "/resources/avatar/" + uuid + ext;
            user.setAvatar(avatarPath);
            userService.update(user);
            result.setCode(1);
            result.setMessage("上传成功！");
            Map map = new HashMap<String, String>();
            map.put("path", avatarPath);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("上传失败！");
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

    @PutMapping("/updatePersonalInfo")
    public Result updatePersonalInfo(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            user.setNickname(map.get("nickname"));
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
