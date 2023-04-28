package capstone_project.ReadingForum_Backend.Dao;

import java.util.List;

public interface GroupFavouriteDao {
    List<Integer> selectGroupFavourite(int groupId);
    void insert(int groupId, int bookId);
    void delete(int groupId, int bookId);
}
