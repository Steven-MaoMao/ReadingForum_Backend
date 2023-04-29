package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Model.Subgroup;
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
    public Result getSubgroup(@RequestParam("groupId") int groupId) {
        try {
            List<Subgroup> subgroupList = subgroupService.selectByGroupId(groupId);
            for (int i=0;i<subgroupList.size();i++) {
                subgroupList.get(i).setSubgroupMember(userService.selectSubgroupMember(subgroupList.get(i).getId()));
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
    public Result createSubgroup(@RequestParam("name") String name, @RequestBody List<Integer> memberList) {
        try {
            subgroupService.insert(name);
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
}
