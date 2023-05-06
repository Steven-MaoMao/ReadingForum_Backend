package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class Module {
    private int id = 0;
    private String name = null;
//    subgroup_module的id
    private int subgroupModuleId = 0;
}
