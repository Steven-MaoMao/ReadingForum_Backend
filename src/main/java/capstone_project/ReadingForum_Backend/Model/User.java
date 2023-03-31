package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class User {
    private int id = 0;
    private String username = null;
    private String password = null;
    private String nickname = null;
    private String avatar = null;
    private String gender = null;
    private String birthday = null;
    private String phone = null;
    private String email = null;
    private String location = null;
    private String bio = null;
    private boolean ban = false;
    private boolean deleted = false;
}
