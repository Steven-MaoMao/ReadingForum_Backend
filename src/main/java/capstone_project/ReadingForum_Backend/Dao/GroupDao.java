package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Group;

import java.util.List;

public interface GroupDao {
    List<Group> selectAll();
    List<Group> selectAllByPage(int start);
    int selectAllNum();
    Group selectById(int id);
    Group selectByCreateUser(int userId);
    List<Group> searchGroup(String keyword, int start);
    int searchGroupNum(String keyword);
    List<Group> selectTopFiveGroup();
    void insert(String name, int createUser);
    void update(Group group);
    void delete(int groupId);
}
