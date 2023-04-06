package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ITagService {
    List<Map> selectAll() throws IOException;
    List<Map> selectByBook(int bookId) throws IOException;
    List<Map> selectTopFive() throws IOException;
    List<Map> orderTopTenFavouriteTagById(int userId) throws IOException;
}
