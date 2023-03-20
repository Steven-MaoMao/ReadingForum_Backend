package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.User;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    List<User> selectAll() throws IOException;
    User selectById(int id) throws IOException;
    User selectByUsername(String username) throws IOException;
    boolean insert(String username, String password) throws IOException;
    boolean updateById(int id, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted) throws IOException;
    boolean updateByUsername(String username, String password, String nickname, String avatar, String gender, String birthday, String phone, String email, String location, String bio, String state, boolean deleted) throws IOException;
}
