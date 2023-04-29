package capstone_project.ReadingForum_Backend.Dao;

public interface SubgroupMemberDao {
    void insert(int userId, int subgroupId);
    void delete(int subgroupMemberId);
}
