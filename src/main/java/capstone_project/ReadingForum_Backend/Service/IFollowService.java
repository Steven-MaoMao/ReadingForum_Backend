package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;
import java.util.List;

public interface IFollowService {
    List<Integer> selectFollowing(int followerId) throws  IOException;
    boolean insert(int followerId, int followingId) throws IOException;
    boolean delete(int followerId, int followingId) throws IOException;
}
