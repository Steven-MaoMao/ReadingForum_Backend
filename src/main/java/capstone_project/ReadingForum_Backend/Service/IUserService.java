package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.User;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    List<User> selectAll() throws IOException;
    User selectById(int id) throws IOException;
    User selectByUsername(String username) throws IOException;
    boolean insert(String username, String password) throws IOException;
    boolean update(User user) throws IOException;
}
