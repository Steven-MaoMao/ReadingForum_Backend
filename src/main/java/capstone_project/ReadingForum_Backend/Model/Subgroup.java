package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

import java.util.List;

@Data
public class Subgroup {
    private int id = 0;
    private String name = null;
    private String time = null;
    private int groupId = 0;
    private List<User> subgroupMember = null;
    private List<Module> moduleList = null;
}
