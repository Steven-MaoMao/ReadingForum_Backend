package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.SubgroupVoteMember;

import java.io.IOException;
import java.util.List;

public interface ISubgroupVoteMemberService {
    List<SubgroupVoteMember> selectBySubgroupVoteId(int subgroupVoteId) throws IOException;
    boolean insert(int userId, int subgroupVoteId, String state) throws IOException;
    boolean voteYes(int userId, int subgroupVoteId) throws IOException;
    boolean voteNo(int userId, int subgroupVoteId) throws IOException;
}
