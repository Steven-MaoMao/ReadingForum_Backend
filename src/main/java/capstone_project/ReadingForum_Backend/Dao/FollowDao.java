package capstone_project.ReadingForum_Backend.Dao;

import java.util.List;

public interface FollowDao {
    List<Integer> selectFollowing(int followerId);
    void insert(int followerId, int followingId);
    void delete(int followerId, int followingId);
}
