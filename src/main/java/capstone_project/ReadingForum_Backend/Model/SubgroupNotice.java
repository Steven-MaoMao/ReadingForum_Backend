package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class SubgroupNotice {
    private int id = 0;
    private String title = null;
    private String text = null;
    private int userId = 0;
    private String time = null;
    private int subgroupId = 0;
    private User user = null;
}
