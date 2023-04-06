package capstone_project.ReadingForum_Backend.Dao;

import java.util.List;
import java.util.Map;

public interface TagDao {
    List<Map> selectAll();
    List<Map> selectByBook(int bookId);
    List<Map> selectTopFive();
    List<Map> orderTopTenFavouriteTagById(int userId);
}
