package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.SubgroupNotice;

import java.util.List;

public interface SubgroupNoticeDao {
    List<SubgroupNotice> selectBySubgroupId(int subgroupId);
    List<SubgroupNotice> selectByName(int id);
    void insert(String title, String text, int userId, int subgroupModelId);
    void update(int id, String title, String text);
    void delete(int id);
}
