package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class Group {
    private int id = 0;
    private String name = null;
    private String avatar = null;
    private int createUser = 0;
    private String createTime = null;
    private String introduction = null;
    private String notice = null;
    private String username = null;
    private String nickname = null;
    private String userAvatar = null;
}
