package capstone_project.ReadingForum_Backend.Model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private String phone;
    private String email;
    private String location;
    private String bio;
    private String state;
    private boolean deleted;
}
