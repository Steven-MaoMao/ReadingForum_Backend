package capstone_project.ReadingForum_Backend.Dao;

import java.util.List;

public interface FavouriteDao {
    List<Integer> selectFavourite(int userId);
    void insert(int userId, int bookId);
    void delete(int userId, int bookId);
}
