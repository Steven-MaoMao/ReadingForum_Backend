package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class GroupMember {
    private int id = 0;
    private int groupId = 0;
    private int userId = 0;
    private boolean manager = false;
    private boolean state = false;
}
