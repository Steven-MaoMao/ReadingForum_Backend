package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.SubgroupVote;

import java.io.IOException;
import java.util.List;

public interface ISubgroupVoteService {
    List<SubgroupVote> selectBySubgroupModuleId(int subgroupModuleId) throws IOException;
    SubgroupVote selectByName(String name) throws IOException;
    boolean insert(String name, String description, int subgroupModuleId, int userId) throws IOException;
    boolean update(SubgroupVote subgroupVote) throws IOException;
    boolean delete(int id) throws IOException;
}
