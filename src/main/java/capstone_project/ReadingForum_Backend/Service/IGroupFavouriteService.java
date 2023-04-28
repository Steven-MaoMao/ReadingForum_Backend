package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;
import java.util.List;

public interface IGroupFavouriteService {
    List<Integer> selectGroupFavourite(int groupId) throws IOException;
    boolean insert(int groupId, int bookId) throws IOException;
    boolean delete(int groupId, int bookId) throws IOException;
}
