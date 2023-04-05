package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;
import java.util.List;

public interface IFavouriteService {
    List<Integer> selectFavourite(int userId) throws IOException;
    boolean insert(int userId, int bookId) throws IOException;
    boolean delete(int userId, int bookId) throws IOException;
}
