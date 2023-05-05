package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Book;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.*;
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
    @Autowired
    private IFollowService followService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IGroupMemberService groupMemberService;

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

    @GetMapping("/userInfoById")
    public Result getUserInfoById(@RequestParam("userId") int userId) {
        try {
            User user = userService.selectById(userId);
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

    @GetMapping("/searchUser")
    public Result searchUser(@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            keyword = "%" + keyword + "%";
            List<User> userList = userService.searchUser(keyword, start);
            int totalUser = userService.searchUserNum(keyword);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("搜索成功！");
            Map map = new HashMap<String, Object>();
            map.put("userList", userList);
            map.put("totalUser", totalUser);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/num")
    public Result getNum(@RequestHeader("token") String token) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            int favouriteNum = bookService.selectFavouriteNum(userId);
            int followingNum = userService.selectFollowingNum(userId);
            int followerNum = userService.selectFollowerNum(userId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Integer>();
            map.put("favouriteNum", favouriteNum);
            map.put("followingNum", followingNum);
            map.put("followerNum", followerNum);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/favouriteByPage")
    public Result getFavouriteByPage(@RequestHeader("token") String token, @RequestParam("page") int page) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            int start = (page - 1) * 16;
            List<Book> bookList = bookService.selectFavouriteByPage(id, start);
            for (int i=0; i<bookList.size(); i++) {
                int bookId = bookList.get(i).getId();
                bookList.get(i).setTags(tagService.selectByBook(bookId));
            }
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

    @GetMapping("/favouriteByPageId")
    public Result getFavouriteByPageId(@RequestParam("id") int id, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 16;
            List<Book> bookList = bookService.selectFavouriteByPage(id, start);
            for (int i=0; i<bookList.size(); i++) {
                int bookId = bookList.get(i).getId();
                bookList.get(i).setTags(tagService.selectByBook(bookId));
            }
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

    @GetMapping("/followingByPage")
    public Result getFollowingByPage(@RequestHeader("token") String token, @RequestParam("page") int page) {
        try {
            String username = JWT.parseToken(token);
            int followerId = userService.selectByUsername(username).getId();
            int start = (page - 1) * 10;
            List<User> userList = userService.selectFollowingByPage(followerId, start);
            int num = userService.selectFollowingNum(followerId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户关注成功！");
            Map data = new HashMap<String, Object>();
            data.put("userList", userList);
            data.put("num", num);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户关注失败！");
            return result;
        }
    }

    @GetMapping("/followingByPageId")
    public Result getFollowingByPageId(@RequestParam("followerId") int followerId, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            List<User> userList = userService.selectFollowingByPage(followerId, start);
            int num = userService.selectFollowingNum(followerId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户关注成功！");
            Map data = new HashMap<String, Object>();
            data.put("userList", userList);
            data.put("num", num);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户关注失败！");
            return result;
        }
    }

    @GetMapping("/followerByPage")
    public Result getFollowerByPage(@RequestHeader("token") String token, @RequestParam("page") int page) {
        try {
            String username = JWT.parseToken(token);
            int followingId = userService.selectByUsername(username).getId();
            int start = (page - 1) * 10;
            List<User> userList = userService.selectFollowerByPage(followingId, start);
            int num = userService.selectFollowerNum(followingId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户关注成功！");
            Map data = new HashMap<String, Object>();
            data.put("userList", userList);
            data.put("num", num);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户粉丝失败！");
            return result;
        }
    }

    @GetMapping("/followerByPageId")
    public Result getFollowerByPageId(@RequestParam("followingId") int followingId, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            List<User> userList = userService.selectFollowerByPage(followingId, start);
            int num = userService.selectFollowerNum(followingId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取用户关注成功！");
            Map data = new HashMap<String, Object>();
            data.put("userList", userList);
            data.put("num", num);
            result.setData(data);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("获取用户粉丝失败！");
            return result;
        }
    }

    @GetMapping("/isFollowed")
    public Result isFollowed(@RequestHeader("token") String token, @RequestParam("followingId") int followingId) {
        try {
            String username = JWT.parseToken(token);
            int followerId = userService.selectByUsername(username).getId();
            List<Integer> followingList =  followService.selectFollowing(followerId);
            Result result = new Result();
            if (followingList.contains(followingId)) {
                result.setCode(1);
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/groupMember")
    public Result getGroupMember(@RequestHeader("token") String token, @RequestParam("groupId") int groupId) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            List<User> userList = userService.selectGroupMember(groupId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("userList", userList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/groupApplicant")
    public Result getGroupApplicant(@RequestHeader("token") String token, @RequestParam("groupId") int groupId) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            List<User> userList = userService.selectGroupApplicant(groupId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("成功！");
            Map map = new HashMap<String, Object>();
            map.put("userList", userList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
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
            String newAvatarName = baseUploadPath + "Avatar/" + uuid + ext;
            newAvatar.transferTo(new File(newAvatarName));
            if (user.getAvatar() != null) {
                File oldAvatar = new File(baseUploadPath + user.getAvatar().substring(10));
                if (oldAvatar.exists()) {
                    oldAvatar.delete();
                }
            }
            String avatarPath = "/resources/Avatar/" + uuid + ext;
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

    @PostMapping("/follow")
    public Result follow(@RequestHeader("token") String token, @RequestBody Map<String, Integer> map) {
        try {
            String username = JWT.parseToken(token);
            int followerId = userService.selectByUsername(username).getId();
            int followingId = map.get("followingId");
            followService.insert(followerId, followingId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("关注成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestHeader("token") String token) {
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

    @DeleteMapping("deleteFollowing")
    public Result deleteFollowing(@RequestHeader("token") String token, @RequestParam("followingId") int followingId) {
        try {
            String username = JWT.parseToken(token);
            int followerId = userService.selectByUsername(username).getId();
            followService.delete(followerId, followingId);
            Result result = new Result();
            result.setCode(1);
            result.setMessage("取消关注成功！");
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

    @PutMapping("/joinGroup")
    public Result joinGroup(@RequestHeader("token") String token, @RequestParam("groupId") int groupId) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            Result result = new Result();
            if (groupMemberService.joinGroup(groupId, id)) {
                result.setCode(1);
                result.setMessage("加入成功！");
            } else {
                result.setMessage("加入失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/joinGroupPermit")
    public Result joinGroupPermit(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId) {
        try {
            Result result = new Result();
            if (groupMemberService.joinGroupPermit(groupId, userId)) {
                result.setCode(1);
                result.setMessage("申请通过！");
            } else {
                result.setMessage("程序异常，请重试！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/joinGroupReject")
    public Result joinGroupReject(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId) {
        try {
            Result result = new Result();
            if (groupMemberService.quitGroup(groupId, userId)) {
                result.setCode(1);
                result.setMessage("申请未通过！");
            } else {
                result.setMessage("程序异常，请重试！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/setGroupManager")
    public Result setGroupManager(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId) {
        try {
            Result result = new Result();
            if (groupMemberService.setGroupManager(groupId, userId)) {
                result.setCode(1);
                result.setMessage("设置成功！");
            } else {
                result.setMessage("设置失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/dismissGroupManager")
    public Result dismissGroupManager(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId) {
        try {
            Result result = new Result();
            if (groupMemberService.dismissGroupManager(groupId, userId)) {
                result.setCode(1);
                result.setMessage("设置成功！");
            } else {
                result.setMessage("设置失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/quitGroup")
    public Result quitGroup(@RequestHeader("token") String token, @RequestParam("groupId") int groupId) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            int id = user.getId();
            Result result = new Result();
            if (groupMemberService.quitGroup(groupId, id)) {
                result.setCode(1);
                result.setMessage("退出成功！");
            } else {
                result.setMessage("退出失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
