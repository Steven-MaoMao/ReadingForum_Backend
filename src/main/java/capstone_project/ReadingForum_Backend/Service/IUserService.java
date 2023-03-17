package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.User;

import java.util.List;

public interface IUserService {
    List<User> selectAll();
    User selectById(int id);
    User selectByUsername(String username);
    boolean insert(String username, String password);
    boolean updateById(int id, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted);
    boolean updateByUsername(String username, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted);
}
