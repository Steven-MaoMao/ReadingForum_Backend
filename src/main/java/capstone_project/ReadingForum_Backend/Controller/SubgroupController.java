package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.*;
import capstone_project.ReadingForum_Backend.Service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    subgroupList.get(i).setModuleList(subgroupModuleService.selectBySubgroupId(subgroupList.get(i).getId()));
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
    public Result getSubgroupNotice(@RequestParam("name") String name) {
        try {
            List<SubgroupNotice> subgroupNoticeList = subgroupNoticeService.selectByName(name);
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
    public Result getBookRecommend(@RequestParam("name") String name) {
        try {
            List<BookRecommend> bookRecommendList = bookRecommendService.selectByName(name);
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

    @PostMapping("/createSubgroup")
    public Result createSubgroup(@RequestParam("name") String name, @RequestParam("groupId") int groupId, @RequestBody List<Integer> memberList) {
        try {
            subgroupService.insert(name, groupId);
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

    @PostMapping("/createSubgroupNotice")
    public Result createSubgroupNotice(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            if (subgroupNoticeService.insert(map.get("title"), map.get("text"), Integer.parseInt(map.get("userId")), Integer.parseInt(map.get("subgroupModelId")))) {
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
    public Result addBookRecommend(@RequestBody Map<String, String> map) {
        try {
            Result result = new Result();
            if (bookRecommendService.insert(Integer.parseInt(map.get("bookId")), map.get("recommendReason"), Integer.parseInt(map.get("userId")), Integer.parseInt(map.get("subgroupModuleId")))) {
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
    public Result createSubgroupVote(@RequestHeader("token") String token, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("subgroupModuleId") int subgroupModuleId, @RequestBody List<Integer> voterIdList) {
        try {
            String username = JWT.parseToken(token);
            int userId = userService.selectByUsername(username).getId();
            Result result = new Result();
            if (subgroupVoteService.insert(name, description, subgroupModuleId, userId)) {
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
            if (subgroupNoticeService.delete(id)) {
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
            if (bookRecommendService.delete(id)) {
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
            if (subgroupVoteService.delete(id)) {
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
