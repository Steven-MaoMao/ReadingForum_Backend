package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class SubgroupVoteMember {
    private int id = 0;
    private int userId = 0;
    private int subgroupVoteId = 0;
    private String state = null;
    private User user = null;
}
