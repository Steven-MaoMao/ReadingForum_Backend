package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Group;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.IGroupService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
import javafx.collections.ObservableMap;
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
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserService userService;

    @Value("${web.uploadPath}")
    private String baseUploadPath;

    @GetMapping("/selectAllByPage")
    public Result selectAllByPage(@RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            List<Group> groupList = groupService.selectAllByPage(start);
            int totalGroup = groupService.selectAllNum();
            for (int i=0;i<groupList.size();i++) {
                User user = userService.selectById(groupList.get(i).getCreateUser());
                groupList.get(i).setUsername(user.getUsername());
                groupList.get(i).setNickname(user.getNickname());
                groupList.get(i).setUserAvatar(user.getAvatar());
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取社团列表成功！");
            Map map = new HashMap<String, Object>();
            map.put("groupList", groupList);
            map.put("totalGroup", totalGroup);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam("groupId") int groupId) {
        try {
            Group group = groupService.selectById(groupId);
            User user = userService.selectById(group.getCreateUser());
            group.setUsername(user.getUsername());
            group.setNickname(user.getNickname());
            group.setUserAvatar(user.getAvatar());
            Result result = new Result();
            result.setCode(1);
            result.setMessage("查找成功！");
            Map map = new HashMap<String, Object>();
            map.put("groupInfo", group);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/searchGroup")
    public Result searchGroup(@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
        try {
            int start = (page - 1) * 10;
            keyword = "%" + keyword + "%";
            List<Group> groupList = groupService.searchGroup(keyword, start);
            int totalGroup = groupService.searchGroupNum(keyword);
            for (int i=0;i<groupList.size();i++) {
                User user = userService.selectById(groupList.get(i).getCreateUser());
                groupList.get(i).setUsername(user.getUsername());
                groupList.get(i).setNickname(user.getNickname());
                groupList.get(i).setUserAvatar(user.getAvatar());
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("搜索成功！");
            Map map = new HashMap<String, Object>();
            map.put("groupList", groupList);
            map.put("totalGroup", totalGroup);
            result.setData(map);
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
            if (!user.isGroupManager()) {
                result.setMessage("你没有修改权限！");
                return result;
            }
            int groupId = user.getGroupId();
            Group group = groupService.selectById(groupId);
            if (newAvatar.isEmpty()) {
                result.setMessage("文件为空，上传失败！");
                return result;
            }
            String[] splitFilename = newAvatar.getOriginalFilename().split("\\.");
            String ext = "." + splitFilename[splitFilename.length - 1];
            String uuid = UUID.randomUUID().toString();
            String newAvatarName = baseUploadPath + "GroupAvatar/" + uuid + ext;
            newAvatar.transferTo(new File(newAvatarName));
            if (group.getAvatar() != null) {
                File oldAvatar = new File(baseUploadPath + group.getAvatar().substring(10));
                if (oldAvatar.exists()) {
                    oldAvatar.delete();
                }
            }
            String avatarPath = "/resources/GroupAvatar/" + uuid + ext;
            group.setAvatar(avatarPath);
            groupService.update(group);
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
}