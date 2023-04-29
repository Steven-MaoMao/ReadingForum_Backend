package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;

public interface ISubgroupMemberService {
    boolean insert(int userId, int subgroupId) throws IOException;
    boolean delete(int subgroupMemberId) throws IOException;
}
