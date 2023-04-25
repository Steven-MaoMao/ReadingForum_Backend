package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
    User selectById(int id);
    User selectByUsername(String username);
    List<User> selectFollowingByPage(int followerId, int start);
    int selectFollowingNum(int followerId);
    List<User> selectFollowerByPage(int followingId, int start);
    int selectFollowerNum(int followingId);
    List<User> searchUser(String keyword, int start);
    int searchUserNum(String keyword);
    List<User> selectGroupMember(int groupId);
    void insert(String username, String password);
    void update(User user);
    void joinGroup(int groupId, int id);
    void setGroupManager(int id);
    void dismissGroupManager(int id);
    void quitGroup(int id);
}
