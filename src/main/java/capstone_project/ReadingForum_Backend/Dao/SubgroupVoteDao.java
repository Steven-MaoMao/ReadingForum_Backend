package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.SubgroupVote;

import java.util.List;

public interface SubgroupVoteDao {
    List<SubgroupVote> selectBySubgroupModuleId(int subgroupModuleId);
    SubgroupVote selectByName(String name);
    void insert(String name, String description, int subgroupModuleId, int userId);
    void update(SubgroupVote subgroupVote);
    void delete(int id);
}
