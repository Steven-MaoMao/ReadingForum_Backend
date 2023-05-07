package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

import java.util.List;

@Data
public class SubgroupVote {
    private int id = 0;
    private String name = null;
    private String description = null;
    private String time = null;
    private int subgroupModuleId = 0;
    private int userId = 0;
    private User user = null;
    private List<SubgroupVoteMember> voterList = null;
}
