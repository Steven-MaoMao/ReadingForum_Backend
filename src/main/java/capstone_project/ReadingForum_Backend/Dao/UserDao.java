package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
    User selectById(int id);
    User selectByUsername(String username);
    void insert(String username, String password);
    void updateById(int id, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted);
    void updateByUsername(String username, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted);
}
