package capstone_project.ReadingForum_Backend.Service;

import java.io.IOException;

public interface IBookRateService {
    int selectRate(int bookId, int userId) throws IOException;
    int selectFive(int bookId) throws IOException;
    int selectFour(int bookId) throws IOException;
    int selectThree(int bookId) throws IOException;
    int selectTwo(int bookId) throws IOException;
    int selectOne(int bookId) throws IOException;
    boolean insert(int bookId, int userId, int rate) throws IOException;
    boolean update(int bookId, int userId, int rate) throws IOException;
}
