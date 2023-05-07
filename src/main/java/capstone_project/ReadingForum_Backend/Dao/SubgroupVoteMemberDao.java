package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.SubgroupVoteMember;

import java.util.List;

public interface SubgroupVoteMemberDao {
    List<SubgroupVoteMember> selectBySubgroupVoteId(int subgroupVoteId);
    void insert(int userId, int subgroupVoteId, String state);
    void voteYes(int userId, int subgroupVoteId);
    void voteNo(int userId, int subgroupVoteId);
}
