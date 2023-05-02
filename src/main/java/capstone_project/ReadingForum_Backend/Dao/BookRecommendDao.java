package capstone_project.ReadingForum_Backend.Dao;

import capstone_project.ReadingForum_Backend.Model.BookRecommend;

import java.util.List;

public interface BookRecommendDao {
    List<BookRecommend> selectBySubgroupId(int subgroupId);
    void insert(int bookId, String recommendReason, int userId, int subgroupId);
    void update(int bookRecommendId, String recommendReason);
    void delete(int bookRecommendId);
}
