package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.Subgroup;

import java.io.IOException;
import java.util.List;

public interface ISubgroupService {
    Subgroup selectByName(String subgroupName) throws IOException;
    List<Subgroup> selectByGroupId(int groupId) throws IOException;
    boolean insert(String subgroupName, int groupId) throws IOException;
    boolean update(int subgroupId, String subgroupName) throws IOException;
    boolean delete(int subgroupId) throws IOException;
}
