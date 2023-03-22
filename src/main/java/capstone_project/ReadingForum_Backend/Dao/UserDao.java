package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
    User selectById(int id);
    User selectByUsername(String username);
    void insert(String username, String password);
    void update(User user);
}
