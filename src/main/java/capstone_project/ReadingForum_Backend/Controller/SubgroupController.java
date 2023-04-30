package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Subgroup;
import capstone_project.ReadingForum_Backend.Model.User;
import capstone_project.ReadingForum_Backend.Service.ISubgroupMemberService;
import capstone_project.ReadingForum_Backend.Service.ISubgroupService;
import capstone_project.ReadingForum_Backend.Service.IUserService;
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

    @PutMapping("updateSubgroupName")
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

    @DeleteMapping("deleteSubgroup")
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
}
