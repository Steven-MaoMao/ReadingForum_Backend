package capstone_project.ReadingForum_Backend.Service;

import capstone_project.ReadingForum_Backend.Model.BookRecommend;

import java.io.IOException;
import java.util.List;

public interface IBookRecommendService {
     List<BookRecommend> selectBySubgroupId(int subgroupId) throws IOException;
     List<BookRecommend> selectByName(int id) throws IOException;
     boolean insert(int bookId, String recommendReason, int userId, int subgroupModelId) throws IOException;
     boolean update(int bookRecommendId, String recommendReason) throws IOException;
     boolean delete(int bookRecommendId) throws IOException;
}
