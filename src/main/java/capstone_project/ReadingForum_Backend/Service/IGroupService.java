package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.Group;

import java.io.IOException;
import java.util.List;

public interface IGroupService {
    List<Group> selectAll() throws IOException;
    List<Group> selectAllByPage(int start) throws IOException;
    int selectAllNum() throws IOException;
    Group selectById(int id) throws IOException;
    List<Group> searchGroup(String keyword, int start) throws IOException;
    int searchGroupNum(String keyword) throws IOException;
    boolean insert(String name, int createUser) throws IOException;
    boolean update(Group group) throws IOException;
}
