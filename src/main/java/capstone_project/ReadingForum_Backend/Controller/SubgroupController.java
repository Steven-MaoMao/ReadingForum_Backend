package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.*;
import capstone_project.ReadingForum_Backend.Service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/subgroup")
public class SubgroupController {
    @Autowired
    private ISubgroupService subgroupService;
    @Autowired
    private ISubgroupMemberService subgroupMemberService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISubgroupModuleService subgroupModuleService;
    @Autowired
    private ISubgroupNoticeService subgroupNoticeService;
    @Autowired
    private IBookRecommendService bookRecommendService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ISubgroupVoteService subgroupVoteService;
    @Autowired
    private ISubgroupVoteMemberService subgroupVoteMemberService;
    @Autowired
    private ISubgroupFrameService subgroupFrameService;

    @Value("${web.uploadPath}")
    private String baseUploadPath;

    @GetMapping("/getSubgroup")
    public Result getSubgroup(@RequestHeader("token") String token, @RequestParam("groupId") int groupId) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            List<Subgroup> subgroupList = subgroupService.selectByGroupId(groupId);
            for (int i=0;i<subgroupList.size();i++) {
                List<User> userList = userService.selectSubgroupMember(subgroupList.get(i).getId());
                boolean isContained = false;
                for (int j=0;j< userList.size();j++) {
                    if (userList.get(j).getId() == id) {
                        isContained = true;
                        break;
                    }
                }
                if (isContained) {
                    subgroupList.get(i).setSubgroupMember(userService.selectSubgroupMember(subgroupList.get(i).getId()));
                    // subgroupList.get(i).setModuleList(subgroupModuleService.selectBySubgroupId(subgroupList.get(i).getId()));
                    subgroupList.get(i).setSubgroupFrameList(subgroupFrameService.selectBySubgroupId(subgroupList.get(i).getId()));
                } else {
                    subgroupList.remove(i);
                    i--;
                }
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取小组信息成功！");
            Map<String, Object> map = new HashMap<>();
            map.put("subgroupList", subgroupList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/getSubgroupNotice")
    public Result getSubgroupNotice(@RequestParam("id") int id) {
        try {
            List<SubgroupNotice> subgroupNoticeList = subgroupNoticeService.selectByName(id);
            for (int i=0;i<subgroupNoticeList.size();i++) {
                subgroupNoticeList.get(i).setUser(userService.selectById(subgroupNoticeList.get(i).getUserId()));
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取小组公告成功！");
            Map<String, Object> map = new HashMap<>();
            map.put("subgroupNoticeList", subgroupNoticeList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("getSubgroupVote")
    public Result getSubgroupVote(@RequestParam("subgroupModuleId") int subgroupModuleId) {
        try {
            List<SubgroupVote> subgroupVoteList = subgroupVoteService.selectBySubgroupModuleId(subgroupModuleId);
            for (int i=0;i<subgroupVoteList.size();i++) {
                subgroupVoteList.get(i).setUser(userService.selectById(subgroupVoteList.get(i).getUserId()));
                List<SubgroupVoteMember> subgroupVoteMemberList = subgroupVoteMemberService.selectBySubgroupVoteId(subgroupVoteList.get(i).getId());
                for (int j=0;j<subgroupVoteMemberList.size();j++) {
                    subgroupVoteMemberList.get(j).setUser(userService.selectById(subgroupVoteMemberList.get(j).getUserId()));
                }
                subgroupVoteList.get(i).setVoterList(subgroupVoteMemberList);
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取小组投票成功！");
            Map<String, Object> map = new HashMap<>();
            map.put("subgroupVoteList", subgroupVoteList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/getBookRecommend")
    public Result getBookRecommend(@RequestParam("id") int id) {
        try {
            List<BookRecommend> bookRecommendList = bookRecommendService.selectByName(id);
            for (int i=0;i<bookRecommendList.size();i++) {
                bookRecommendList.get(i).setUser(userService.selectById(bookRecommendList.get(i).getUserId()));
                Book book = bookService.selectById(bookRecommendList.get(i).getBookId());
                book.setTags(tagService.selectByBook(book.getId()));
                bookRecommendList.get(i).setBook(book);
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取书籍推荐成功！");
            Map<String, Object> map = new HashMap<>();
            map.put("bookRecommendList", bookRecommendList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @GetMapping("/getAllModule")
    public Result getAllModule() {
        try {
            List<Module> moduleList = subgroupModuleService.selectAllModule();
            Result result = new Result();
            result.setCode(1);
            result.setMessage("获取成功！");
            Map<String, Object> map = new HashMap<>();
            map.put("moduleList", moduleList);
            result.setData(map);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/uploadActivityRecommendPic")
    public Result uploadAvatar(@RequestHeader("token") String token, @RequestParam("file") MultipartFile newAvatar) {
        try {
            String username = JWT.parseToken(token);
            User user = userService.selectByUsername(username);
            Result result = new Result();
//            if (!user.isGroupManager()) {
//                result.setMessage("你没有修改权限！");
//                return result;
//            }
//            int groupId = user.getGroupId();
//            Group group = groupService.selectById(groupId);
            if (newAvatar.isEmpty()) {
                result.setMessage("文件为空，上传失败！");
                return result;
            }
            String[] splitFilename = newAvatar.getOriginalFilename().split("\\.");
            String ext = "." + splitFilename[splitFilename.length - 1];
            String uuid = UUID.randomUUID().toString();
            String newAvatarName = baseUploadPath + "ActivityRecommendPic/" + uuid + ext;
            newAvatar.transferTo(new File(newAvatarName));
//            if (group.getAvatar() != null) {
//                File oldAvatar = new File(baseUploadPath + group.getAvatar().substring(10));
//                if (oldAvatar.exists()) {
//                    oldAvatar.delete();
//                }
//            }
            String avatarPath = "/resources/ActivityRecommendPic/" + uuid + ext;
//            group.setAvatar(avatarPath);
//            groupService.update(group);
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

    @PostMapping("/createSubgroup")
    public Result createSubgroup(@RequestParam("name") String name, @RequestParam("groupId") int groupId, @RequestParam("frameId") int frameId, @RequestBody List<Integer> memberList) {
        try {
            subgroupService.insert(name, groupId, frameId);
            int id = subgroupService.selectByName(name).getId();
            for (int i=0;i<memberList.size();i++) {
                subgroupMemberService.insert(memberList.get(i), id);
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("创建小组成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createSubgroupWithText")
    public Result createSubgroupWithText(@RequestParam("name") String name, @RequestParam("groupId") int groupId, @RequestParam("frameId") int frameId, @RequestParam("text") String text, @RequestBody List<Integer> memberList) {
        try {
            subgroupService.insertWithText(name, groupId, frameId, text);
            int id = subgroupService.selectByName(name).getId();
            for (int i=0;i<memberList.size();i++) {
                subgroupMemberService.insert(memberList.get(i), id);
            }
            Result result = new Result();
            result.setCode(1);
            result.setMessage("创建小组成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createSubgroupFrameWithText")
    public Result createSubgroupFrameWithText(@RequestHeader("token") String token, @RequestParam("subgroupId") int subgroupId, @RequestParam("text") String text, @RequestParam("frameId") int frameId) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(frameId);
            subgroupFrame.setUserId(id);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrame.setText(text);
            subgroupFrameService.insertWithText(subgroupFrame);
            result.setCode(1);
            result.setMessage("创建小组成功！");
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createActivityRecommendWithVote")
    public Result createActivityWithVote(@RequestHeader("token") String token, @RequestParam("subgroupId") int subgroupId, @RequestParam("text") String text, @RequestParam("frameId") int frameId, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("yesWord") String yesWord, @RequestParam("noWord") String noWord, @RequestBody List<Integer> voterIdList) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(frameId);
            subgroupFrame.setUserId(userId);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrame.setText(text);
            subgroupFrameService.insertWithText(subgroupFrame);
            int subgroupFrameId = subgroupFrame.getId();
            if (subgroupVoteService.insert(name, description, subgroupFrameId, userId, yesWord, noWord)) {
                int id = subgroupVoteService.selectByName(name).getId();
                for (int i=0;i<voterIdList.size();i++) {
                    subgroupVoteMemberService.insert(voterIdList.get(i), id, "待投票");
                }
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createSubgroupNotice")
    public Result createSubgroupNotice(@RequestHeader("token") String token, @RequestParam("subgroupId") int subgroupId, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(1);
            subgroupFrame.setUserId(id);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrameService.insert(subgroupFrame);
            int subgroupFrameId = subgroupFrame.getId();
            if (subgroupNoticeService.insert(map.get("title"), map.get("text"), Integer.parseInt(map.get("userId")), subgroupFrameId)) {
                result.setCode(1);
                result.setMessage("创建成功！");
            } else {
                result.setMessage("创建失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/newComment")
    public Result newComment(@RequestHeader("token") String token, @RequestParam("comment") String comment, @RequestParam("subgroupFrameId") int subgroupFrameId) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            Result result = new Result();
            if (subgroupNoticeService.insert(comment, comment, id, subgroupFrameId)) {
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/addSubgroupModule")
    public Result addSubgroupModule(@RequestParam("subgroupId") int subgroupId, @RequestParam("moduleId") int moduleId, @RequestParam("name") String name) {
        try {
            Result result = new Result();
            if (subgroupModuleService.insert(subgroupId, moduleId, name)) {
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/addBookRecommend")
    public Result addBookRecommend(@RequestHeader("token") String token, @RequestParam("subgroupId") int subgroupId, @RequestParam("subgroupName") String name, @RequestBody Map<String, String> map) {
        try {
            String username = JWT.parseToken(token);
            int id = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(subgroupService.selectByName(name).getFrameId());
            subgroupFrame.setUserId(id);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrameService.insert(subgroupFrame);
            int subgroupFrameId = subgroupFrame.getId();
            if (bookRecommendService.insert(Integer.parseInt(map.get("bookId")), map.get("recommendReason"), Integer.parseInt(map.get("userId")), subgroupFrameId)) {
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createSubgroupVote")
    public Result createSubgroupVote(@RequestHeader("token") String token, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("subgroupId") int subgroupId, @RequestParam("subgroupName") String subgroupName, @RequestParam("yesWord") String yesWord, @RequestParam("noWord") String noWord, @RequestBody List<Integer> voterIdList) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(subgroupService.selectByName(subgroupName).getFrameId());
            subgroupFrame.setUserId(userId);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrameService.insert(subgroupFrame);
            int subgroupFrameId = subgroupFrame.getId();
            if (subgroupVoteService.insert(name, description, subgroupFrameId, userId, yesWord, noWord)) {
                int id = subgroupVoteService.selectByName(name).getId();
                for (int i=0;i<voterIdList.size();i++) {
                    subgroupVoteMemberService.insert(voterIdList.get(i), id, "待投票");
                }
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PostMapping("/createBookVote")
    public Result createBookVote(@RequestHeader("token") String token, @RequestParam("subgroupId") int subgroupId, @RequestParam("subgroupName") String subgroupName, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("yesWord") String yesWord, @RequestParam("noWord") String noWord, @RequestParam("bookId") int bookId, @RequestParam("recommendReason") String recommendReason, @RequestParam("userId") int user, @RequestParam("subgroupModuleId") int subgroupModuleId, @RequestBody List<Integer> voterIdList) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            SubgroupFrame subgroupFrame = new SubgroupFrame();
            subgroupFrame.setFrameId(subgroupService.selectByName(subgroupName).getFrameId());
            subgroupFrame.setUserId(userId);
            subgroupFrame.setSubgroupId(subgroupId);
            subgroupFrameService.insert(subgroupFrame);
            int subgroupFrameId = subgroupFrame.getId();
            if (subgroupVoteService.insert(name, description, subgroupFrameId, userId, yesWord, noWord) && bookRecommendService.insert(bookId, recommendReason, userId, subgroupFrameId)) {
                int id = subgroupVoteService.selectByName(name).getId();
                for (int i=0;i<voterIdList.size();i++) {
                    subgroupVoteMemberService.insert(voterIdList.get(i), id, "待投票");
                }
                result.setCode(1);
                result.setMessage("添加成功！");
            } else {
                result.setMessage("添加失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updateSubgroupName")
    public Result updateSubgroupName(@RequestParam("id") int id, @RequestParam("name") String name) {
        try {
            Result result = new Result();
            if (subgroupService.update(id, name)) {
                result.setCode(1);
                result.setMessage("修改成功！");
            } else {
                result.setMessage("修改失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updateSubgroupModuleName")
    public Result updateSubgroupModuleName(@RequestParam("name") String name, @RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupModuleService.update(name, id)) {
                result.setCode(1);
                result.setMessage("修改成功！");
            } else {
                result.setMessage("修改失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updateSubgroupNotice")
    public Result updateSubgroupNotice(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            if (subgroupNoticeService.update(Integer.parseInt(map.get("id")), map.get("title"), map.get("text"))) {
                result.setCode(1);
                result.setMessage("修改成功！");
            } else {
                result.setMessage("修改失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updateBookRecommendReason")
    public Result updateBookRecommendReason(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            if (bookRecommendService.update(Integer.parseInt(map.get("bookRecommendId")), map.get("recommendReason"))) {
                result.setCode(1);
                result.setMessage("修改成功！");
            } else {
                result.setMessage("修改失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/updateSubgroupVote")
    public Result updateSubgroupVote(@RequestBody SubgroupVote subgroupVote) {
        try {
            Result result = new Result();
            if (subgroupVoteService.update(subgroupVote)) {
                result.setCode(1);
                result.setMessage("修改成功！");
            } else {
                result.setMessage("修改失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/voteYes")
    public Result voteYes(@RequestHeader("token") String token, @RequestParam("id") int id) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            if (subgroupVoteMemberService.voteYes(userId, id)) {
                result.setCode(1);
                result.setMessage("投票成功！");
            } else {
                result.setMessage("投票失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @PutMapping("/voteNo")
    public Result voteNo(@RequestHeader("token") String token, @RequestParam("id") int id) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            if (subgroupVoteMemberService.voteNo(userId, id)) {
                result.setCode(1);
                result.setMessage("投票成功！");
            } else {
                result.setMessage("投票失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteSubgroup")
    public Result deleteSubgroup(@RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupService.delete(id)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteSubgroupNotice")
    public Result deleteSubgroupNotice(@RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupFrameService.delete(id)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteSubgroupModule")
    public Result deleteSubgroupModule(@RequestParam("moduleName") String moduleName) {
        try {
            Result result = new Result();
            if (subgroupModuleService.delete(moduleName)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteBookRecommend")
    public Result deleteBookRecommend(@RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupFrameService.delete(id)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteSubgroupVote")
    public Result deleteSubgroupVote(@RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupFrameService.delete(id)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }

    @DeleteMapping("/deleteSubgroupFrame")
    public Result deleteSubgroupFrame(@RequestParam("id") int id) {
        try {
            Result result = new Result();
            if (subgroupFrameService.delete(id)) {
                result.setCode(1);
                result.setMessage("删除成功！");
            } else {
                result.setMessage("删除失败！");
            }
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setMessage("程序异常，请重试！");
            return result;
        }
    }
}
