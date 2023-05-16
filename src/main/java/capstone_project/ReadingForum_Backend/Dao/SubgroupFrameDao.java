package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.SubgroupFrame;

import java.util.List;

public interface SubgroupFrameDao {
    List<SubgroupFrame> selectBySubgroupId(int subgroupId);
    int insert(SubgroupFrame subgroupFrame);
    int insertWithText(SubgroupFrame subgroupFrame);
    void delete(int subgroupFrameId);
}
