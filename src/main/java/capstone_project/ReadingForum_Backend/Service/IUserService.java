package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.User;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    List<User> selectAll() throws IOException;
    User selectById(int id) throws IOException;
    User selectByUsername(String username) throws IOException;
    List<User> selectFollowingByPage(int followerId, int start) throws IOException;
    int selectFollowingNum(int followerId) throws IOException;
    List<User> selectFollowerByPage(int followingId, int start) throws IOException;
    int selectFollowerNum(int followingId) throws IOException;
    List<User> searchUser(String keyword, int start) throws IOException;
    int searchUserNum(String keyword) throws IOException;
    List<User> selectGroupMember(int groupId) throws IOException;
    List<User> selectSubgroupMember(int subgroupId) throws IOException;
    boolean insert(String username, String password) throws IOException;
    boolean update(User user) throws IOException;
    boolean joinGroup(int groupId, int id) throws IOException;
    boolean setGroupManager(int id) throws IOException;
    boolean dismissGroupManager(int id) throws IOException;
    boolean quitGroup(int id) throws IOException;
}
