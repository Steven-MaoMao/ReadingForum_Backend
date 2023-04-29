package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.Subgroup;

import java.util.List;

public interface SubgroupDao {
    Subgroup selectByName(String subgroupName);
    List<Subgroup> selectByGroupId(int groupId);
    void insert(String subgroupName);
    void update(int subgroupId, String subgroupName);
    void delete(int subgroupId);
}
